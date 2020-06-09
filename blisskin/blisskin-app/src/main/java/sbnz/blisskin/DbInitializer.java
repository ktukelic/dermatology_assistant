package sbnz.blisskin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import sbnz.blisskin.model.*;
import sbnz.blisskin.model.enumerations.Assessment;
import sbnz.blisskin.model.enumerations.Drug;
import sbnz.blisskin.model.enumerations.IngredientGroup;
import sbnz.blisskin.model.enumerations.Role;
import sbnz.blisskin.repository.*;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
@ConditionalOnProperty(name = "app.db-init", havingValue = "true")
public class DbInitializer implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(DbInitializer.class);

    private SkinIssueRepository skinIssueRepository;
    private IngredientDemandRepository ingredientDemandRepository;
    private IngredientRepository ingredientRepository;
    private UserRepository userRepository;
    private TreatmentRepository treatmentRepository;
    private PasswordEncoder passwordEncoder;

    public DbInitializer(SkinIssueRepository skinIssueRepository,
                         IngredientRepository ingredientRepository,
                         UserRepository userRepository,
                         IngredientDemandRepository ingredientDemandRepository,
                         TreatmentRepository treatmentRepository,
                         PasswordEncoder passwordEncoder) {
        this.skinIssueRepository = skinIssueRepository;
        this.ingredientRepository = ingredientRepository;
        this.userRepository = userRepository;
        this.ingredientDemandRepository = ingredientDemandRepository;
        this.treatmentRepository = treatmentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... strings) throws Exception {
        this.skinIssueRepository.deleteAll();

        final Dermatologist dermatologist = userRepository.save(new Dermatologist("derm", passwordEncoder.encode("derm")));
        final Admin admin = userRepository.save(new Admin("admin", passwordEncoder.encode("admin")));

        final SkinIssue ACNE = skinIssueRepository.save(new SkinIssue("Acne"));
        final SkinIssue BLACKHEADS = skinIssueRepository.save(new SkinIssue("Blackheads"));
        final SkinIssue WHITEHEADS = skinIssueRepository.save(new SkinIssue("Whiteheads"));
        final SkinIssue INFLAMMATION = skinIssueRepository.save(new SkinIssue("Inflammation"));
        final SkinIssue ECZEMA = skinIssueRepository.save(new SkinIssue("Eczema"));     // issue sa prioritetom
        final SkinIssue PSORIAZIS = skinIssueRepository.save(new SkinIssue("Psoriasis"));
        final SkinIssue HIVES = skinIssueRepository.save(new SkinIssue("Hives"));
        final SkinIssue SUN_DAMAGE = skinIssueRepository.save(new SkinIssue("Sun damage"));
        final SkinIssue ROSACEA = skinIssueRepository.save(new SkinIssue("Rosacea"));
        final SkinIssue DRYNESS = skinIssueRepository.save(new SkinIssue("Dryness"));
        final SkinIssue FLAKINESS = skinIssueRepository.save(new SkinIssue("Flakiness"));
        final SkinIssue WRINKLES = skinIssueRepository.save(new SkinIssue("Wrinkles"));
        final SkinIssue FINE_LINES = skinIssueRepository.save(new SkinIssue("Fine lines"));
        final SkinIssue REDNESS = skinIssueRepository.save(new SkinIssue("Redness"));
        final SkinIssue SKIN_TEXTURE = skinIssueRepository.save(new SkinIssue("Skin texture"));
        final SkinIssue HYPERPIGMENTATION = skinIssueRepository.save(new SkinIssue("Hyperpigmentation"));
        final SkinIssue ENLARGED_PORES = skinIssueRepository.save(new SkinIssue("Enlarged pores"));
        final SkinIssue CLOGGED_PORES = skinIssueRepository.save(new SkinIssue("Clogged pores"));
        final SkinIssue DARK_SPOTS = skinIssueRepository.save(new SkinIssue("Dark spots"));
        final SkinIssue DULLNESS = skinIssueRepository.save(new SkinIssue("Dullness"));
        final SkinIssue SCARRING = skinIssueRepository.save(new SkinIssue("Scarring"));
        final SkinIssue AGE_SPOTS = skinIssueRepository.save(new SkinIssue("Age spots"));

        final IngredientDemand LOW_HUMIDITY = ingredientDemandRepository.save(new IngredientDemand("Environmental impact - low humidity"));
        final IngredientDemand HIGH_HUMIDITY = ingredientDemandRepository.save(new IngredientDemand("Environmental impact - high humidity"));
        final IngredientDemand LOW_SUN_EXPOSURE = ingredientDemandRepository.save(new IngredientDemand("Environmental impact - low sun exposure"));
        final IngredientDemand HIGH_SUN_EXPOSURE = ingredientDemandRepository.save(new IngredientDemand("Environmental impact - high sun exposure"));

        // age restrictions
        final IngredientDemand YOUNG = ingredientDemandRepository.save(new IngredientDemand("Patient age 10 to 20"));
        final IngredientDemand ADULT = ingredientDemandRepository.save(new IngredientDemand("Patient age 20 to 40"));
        final IngredientDemand MATURE = ingredientDemandRepository.save(new IngredientDemand("Patient age 40+"));

        /*
         * Ingredients for recommendation
         */

        // Skin replenishing - hydration
        final Ingredient ceramides = new Ingredient("Ceramides", IngredientGroup.HYDRATION);
        ceramides.setNotRecommendedSkinProperties(new SkinProperties());
        ceramides.setTargetedSkinIssues(new HashSet<SkinIssue>() {{
            add(DRYNESS);
            add(REDNESS);
            add(ECZEMA);
            add(PSORIAZIS);
            add(FLAKINESS);
            add(FINE_LINES);
        }});
        ingredientRepository.save(ceramides);

        final Ingredient hyaluronicAcid = new Ingredient("Hyaluronic Acid", IngredientGroup.HYDRATION);
        hyaluronicAcid.setNotRecommendedSkinProperties(new SkinProperties());
        hyaluronicAcid.setTargetedSkinIssues(new HashSet<SkinIssue>() {{
            add(FINE_LINES);
            add(WRINKLES);
            add(DRYNESS);
            add(INFLAMMATION);
        }});
        ingredientRepository.save(hyaluronicAcid);

        final Ingredient glycerin = new Ingredient("Glycerin", IngredientGroup.HYDRATION);
        SkinProperties skinProperties1 = new SkinProperties();
        skinProperties1.setSebum(Assessment.HIGH);
        glycerin.setNotRecommendedSkinProperties(skinProperties1);
        glycerin.setTargetedSkinIssues(new HashSet<SkinIssue>() {{
            add(DRYNESS);
            add(FLAKINESS);
            add(INFLAMMATION);
        }});
        glycerin.setIngredientDemands(new HashSet<IngredientDemand>() {{
            add(LOW_HUMIDITY);
        }});
        ingredientRepository.save(glycerin);


        // Skin replenishing - exfoliants
        final Ingredient lacticAcid = new Ingredient("Lactic acid", IngredientGroup.EXFOLIANT);
        lacticAcid.setNotRecommendedSkinProperties(new SkinProperties());
        lacticAcid.setTargetedSkinIssues(new HashSet<SkinIssue>() {{
            add(FINE_LINES);
            add(HYPERPIGMENTATION);
            add(DARK_SPOTS);
            add(SKIN_TEXTURE);
        }});
        ingredientRepository.save(lacticAcid);

        final Ingredient glycolicAcid = new Ingredient("Glycolic acid", IngredientGroup.EXFOLIANT);
        glycolicAcid.setNotRecommendedSkinProperties(new SkinProperties());
        glycolicAcid.setTargetedSkinIssues(new HashSet<SkinIssue>() {{
            add(FINE_LINES);
            add(WRINKLES);
            add(HYPERPIGMENTATION);
            add(DARK_SPOTS);
            add(SKIN_TEXTURE);
            add(ENLARGED_PORES);
        }});
        ingredientRepository.save(glycolicAcid);

        final Ingredient salicylicAcid = new Ingredient("Salicylic acid", IngredientGroup.EXFOLIANT);
        salicylicAcid.setNotRecommendedSkinProperties(new SkinProperties());
        salicylicAcid.setTargetedSkinIssues(new HashSet<SkinIssue>() {{
            add(BLACKHEADS);
            add(WHITEHEADS);
            add(SKIN_TEXTURE);
            add(ENLARGED_PORES);
            add(CLOGGED_PORES);
        }});
        ingredientRepository.save(salicylicAcid);

        final Ingredient benzoylPeroxide = new Ingredient("Benzoyl Peroxide", IngredientGroup.EXFOLIANT);
        benzoylPeroxide.setNotRecommendedSkinProperties(new SkinProperties());
        benzoylPeroxide.setTargetedSkinIssues(new HashSet<SkinIssue>() {{
            add(ACNE);
            add(INFLAMMATION);
            add(ENLARGED_PORES);
        }});
        benzoylPeroxide.setIngredientDemands(new HashSet<IngredientDemand>() {{
            add(LOW_SUN_EXPOSURE);
        }});
        ingredientRepository.save(benzoylPeroxide);


        // Antioxidants (most effective when combined)
        final Ingredient vitaminE = new Ingredient("Vitamin E", IngredientGroup.ANTIOXIDANT);
        vitaminE.setNotRecommendedSkinProperties(new SkinProperties());
        vitaminE.setTargetedSkinIssues(new HashSet<SkinIssue>() {{
            add(DRYNESS);
            add(INFLAMMATION);
            add(DARK_SPOTS);
        }});
        ingredientRepository.save(vitaminE);

        final Ingredient vitaminC = new Ingredient("Vitamin C", IngredientGroup.ANTIOXIDANT);
        vitaminC.setNotRecommendedSkinProperties(new SkinProperties());
        vitaminC.setTargetedSkinIssues(new HashSet<SkinIssue>() {{
            add(FINE_LINES);
            add(SCARRING);
            add(WRINKLES);
            add(DULLNESS);
            add(HYPERPIGMENTATION);
            add(DARK_SPOTS);
            add(SUN_DAMAGE);
        }});
        ingredientRepository.save(vitaminC);

        final Ingredient greenTea = new Ingredient("Green tea", IngredientGroup.ANTIOXIDANT);
        greenTea.setNotRecommendedSkinProperties(new SkinProperties());
        greenTea.setTargetedSkinIssues(new HashSet<SkinIssue>() {{
            add(ROSACEA);
            add(INFLAMMATION);
            add(SUN_DAMAGE);
            add(REDNESS);
        }});
        ingredientRepository.save(greenTea);

        // Skin restoring
        final Ingredient hydroquinone = new Ingredient("Hydroquinone", IngredientGroup.SKIN_RESTORING);
        hydroquinone.setNotRecommendedSkinProperties(new SkinProperties());
        hydroquinone.setTargetedSkinIssues(new HashSet<SkinIssue>() {{
            add(HYPERPIGMENTATION);
            add(DARK_SPOTS);
            add(AGE_SPOTS);
        }});
        ingredientRepository.save(hydroquinone);

        final Ingredient retinol = new Ingredient("Retinol", IngredientGroup.SKIN_RESTORING);
        retinol.setNotRecommendedSkinProperties(new SkinProperties());
        retinol.setTargetedSkinIssues(new HashSet<SkinIssue>() {{
            add(ACNE);
            add(FINE_LINES);
            add(WRINKLES);
            add(AGE_SPOTS);
            add(CLOGGED_PORES);
            add(SCARRING);
            add(SUN_DAMAGE);
        }});
        ingredientRepository.save(retinol);

        final Ingredient niacinamide = new Ingredient("Niacinamide", IngredientGroup.SKIN_RESTORING);
        niacinamide.setNotRecommendedSkinProperties(new SkinProperties());
        niacinamide.setTargetedSkinIssues(new HashSet<SkinIssue>() {{
            add(ACNE);
            add(BLACKHEADS);
            add(WHITEHEADS);
            add(ROSACEA);
            add(INFLAMMATION);
            add(HYPERPIGMENTATION);
            add(SUN_DAMAGE);
        }});
        ingredientRepository.save(niacinamide);


        // patients
        final Patient patient = new Patient("Katarina", "Tukelic", "kaca", passwordEncoder.encode("kaca"), 23);  // kaca
        final Patient patient2 = new Patient("Teodora", "Tukelic", "doja", passwordEncoder.encode("doja"), 22);  // kaca

        // treatment in last 2 months with eczema or psoriazis, no drugs prescribed --------------------------------------------------------
        Set<Ingredient> recommendedIngredients = new HashSet<Ingredient>() {{
            add(ceramides);
            add(lacticAcid);
            add(vitaminE);
            add(hydroquinone);
        }};
        Set<SkinIssue> treatedSkinIssues = new HashSet<SkinIssue>() {{
            add(ECZEMA);
            add(PSORIAZIS);
        }};
        Treatment treatment = new Treatment(null, getPastDate(new Date(), 1), patient, Drug.NONE, recommendedIngredients, treatedSkinIssues);

        patient.getPreviousTreatments().add(treatment);
        userRepository.save(patient);
        // ----------------------------------------------------------------------------------------------------------------------------------

        recommendedIngredients = new HashSet<Ingredient>() {{
            add(hyaluronicAcid);
            add(glycolicAcid);
            add(greenTea);
            add(niacinamide);
        }};
        treatedSkinIssues = new HashSet<SkinIssue>() {{
            add(ACNE);
            add(BLACKHEADS);
            add(REDNESS);
        }};
        treatment = new Treatment(null, getPastDate(new Date(), 1), patient2, Drug.NONE, recommendedIngredients, treatedSkinIssues);  // most recent treatment

        Treatment treatment2 = new Treatment(null, getPastDate(new Date(), 2), patient2, Drug.CORTICOSTEROID, new HashSet<Ingredient>(), new HashSet<SkinIssue>());
        Treatment treatment3 = new Treatment(null, getPastDate(new Date(), 3), patient2, Drug.CORTICOSTEROID, new HashSet<Ingredient>(), new HashSet<SkinIssue>());
        Treatment treatment4 = new Treatment(null, getPastDate(new Date(), 4), patient2, Drug.CORTICOSTEROID, new HashSet<Ingredient>(), new HashSet<SkinIssue>());

        patient2.getPreviousTreatments().add(treatment);
        patient2.getPreviousTreatments().add(treatment2);
        patient2.getPreviousTreatments().add(treatment3);
        patient2.getPreviousTreatments().add(treatment4);
        userRepository.save(patient2);

        LOGGER.info("Database has been initialized");
    }

    private Date getPastDate(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -days);
        return cal.getTime();
    }
}
