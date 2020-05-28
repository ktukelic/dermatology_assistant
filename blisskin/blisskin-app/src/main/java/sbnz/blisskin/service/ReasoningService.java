package sbnz.blisskin.service;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.stereotype.Service;
import sbnz.blisskin.model.Ingredient;
import sbnz.blisskin.model.dto.TreatmentRequest;
import sbnz.blisskin.model.dto.TreatmentResponse;
import sbnz.blisskin.repository.IngredientRepository;
import sbnz.blisskin.util.DebugAgendaEventListener;

import java.util.List;

@Service
public class ReasoningService {

    private final KieSession kieSession;
    private final IngredientRepository ingredientRepository;

    public ReasoningService(KieSession kieSession, IngredientRepository ingredientRepository) {
        this.kieSession = kieSession;
        this.ingredientRepository = ingredientRepository;
    }

    public void initializeSession() {
//        kieSession.addEventListener(new DebugAgendaEventListener());

        // insert initial facts
        List<Ingredient> ingredients = ingredientRepository.findAll();
        ingredients.stream().forEach(kieSession::insert);
    }

    public TreatmentResponse findBestTreatment(TreatmentRequest treatmentRequest) {
        initializeSession();

        // insert patient facts
        kieSession.insert(treatmentRequest);

        kieSession.fireAllRules();

        // get treatment recommendation
        TreatmentResponse response = null;
        QueryResults results = kieSession.getQueryResults("getTreatmentResponse");
        for (QueryResultsRow queryResult : results) {
            response = (TreatmentResponse) queryResult.get("$result");
        }
        return response;
    }
}
