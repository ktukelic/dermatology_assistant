package sbnz.blisskin.service;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.stereotype.Service;
import sbnz.blisskin.exceptions.NotFoundException;
import sbnz.blisskin.model.Ingredient;
import sbnz.blisskin.model.Patient;
import sbnz.blisskin.model.SkinIssue;
import sbnz.blisskin.model.dto.ReportResponse;
import sbnz.blisskin.model.dto.TreatmentRequest;
import sbnz.blisskin.model.dto.TreatmentResponse;
import sbnz.blisskin.model.enumerations.Drug;
import sbnz.blisskin.repository.IngredientRepository;
import sbnz.blisskin.repository.PatientRepository;
import sbnz.blisskin.repository.UserRepository;

import java.util.*;

import static java.util.stream.Collectors.toMap;

@Service
public class ReasoningService {

    private final KieSession kieSession;
    private final IngredientRepository ingredientRepository;
    private final UserRepository userRepository;
    private final PatientRepository patientRepository;

    public ReasoningService(KieSession kieSession, IngredientRepository ingredientRepository, UserRepository userRepository, PatientRepository patientRepository) {
        this.kieSession = kieSession;
        this.ingredientRepository = ingredientRepository;
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
    }

    public void initializeSession() {
//        kieSession.addEventListener(new DebugAgendaEventListener());

        List<Ingredient> ingredients = ingredientRepository.findAll();
        ingredients.stream().forEach(kieSession::insert);
    }

    public TreatmentResponse findBestTreatment(TreatmentRequest treatmentRequest) {
        initializeSession();

        kieSession.insert(treatmentRequest);

        // insert patient treatments
        Patient patient = (Patient) userRepository.findByUsername(treatmentRequest.getUsername())
                .orElseThrow(() -> new NotFoundException("User not found"));
        patient.getPreviousTreatments().stream().forEach(kieSession::insert);

        kieSession.fireAllRules();

        // get treatment recommendation
        TreatmentResponse response = null;
        QueryResults results = kieSession.getQueryResults("getTreatmentResponse");
        for (QueryResultsRow queryResult : results) {
            response = (TreatmentResponse) queryResult.get("$result");
        }

        // get drug recommendation
        Drug prescriptionDrug = null;
        results = kieSession.getQueryResults("getDrugRecommendation");
        for (QueryResultsRow queryResult : results) {
            prescriptionDrug = (Drug) queryResult.get("$drug");
        }
        response.setDrug(prescriptionDrug);
        return response;
    }

    public Map<String, Float> findIngredientsForSkinIssues(List<SkinIssue> skinIssues) {
        initializeSession();
        skinIssues.stream().forEach(kieSession::insert);

        // <ingredient, number of skin issues matched>
        Map<String, Float> resultMap = new HashMap();
        QueryResults results = kieSession.getQueryResults("Get ingredients for given skin issues");
        for (QueryResultsRow queryResult : results) {
            resultMap.put(((Ingredient) queryResult.get("$ingredient")).getName(), ((Long) queryResult.get("$numberOfMatches")).floatValue());
        }

        resultMap = resultMap
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                        LinkedHashMap::new));

        return resultMap;
    }

    public Ingredient findIngredientInformation(Long ingredientId) {
        initializeSession();

        Ingredient foundIngredient = null;
        QueryResults results = kieSession.getQueryResults("Get ingredient information", ingredientId);
        if (results == null) {
            throw new NotFoundException("Ingredient not found");
        }
        for (QueryResultsRow queryResult : results) foundIngredient = (Ingredient) queryResult.get("$ingredient");

        return foundIngredient;
    }

    public ReportResponse getReports(Drug drugType) {
        List<Patient> l = patientRepository.findAll();
        patientRepository.findAll().stream().forEach(kieSession::insert);

        final ReportResponse reportResponse = new ReportResponse();
        reportResponse.setPatientsWithSeriousSkinIssues(getSeriousPatientsReport(drugType));
        reportResponse.setPatientsWithPossibleTSW(getPossibleSteroidAddicts());
        return reportResponse;
    }

    private List<Patient> getSeriousPatientsReport(Drug drugType) {
        List<Patient> patientsWithSeriousSkinIssues = new ArrayList<>();
        QueryResults results = kieSession.getQueryResults("Get patients with more serious skin issues", drugType);
        for (QueryResultsRow queryResult : results) {
            patientsWithSeriousSkinIssues.add((Patient) queryResult.get("$patient"));
        }
        return patientsWithSeriousSkinIssues;
    }

    private List<Patient> getPossibleSteroidAddicts() {
        List<Patient> patientsWithPossibleTSW = new ArrayList<>();
        QueryResults results = kieSession.getQueryResults("Get patients with possible TSW");
        for (QueryResultsRow queryResult : results) {
            patientsWithPossibleTSW.add((Patient) queryResult.get("$patient"));
        }
        return patientsWithPossibleTSW;
    }

}
