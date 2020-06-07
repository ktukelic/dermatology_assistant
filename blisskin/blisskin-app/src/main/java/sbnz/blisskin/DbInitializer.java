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
        final Ingredient ingredient1 = new Ingredient("Ceramides", IngredientGroup.HYDRATION);
        ingredient1.setNotRecommendedSkinProperties(new SkinProperties());
        ingredient1.setTargetedSkinIssues(new HashSet<SkinIssue>() {{
            add(DRYNESS);
            add(REDNESS);
            add(ECZEMA);
            add(PSORIAZIS);
            add(FLAKINESS);
            add(FINE_LINES);
        }});
        ingredientRepository.save(ingredient1);

        final Ingredient ingredient2 = new Ingredient("Hyaluronic Acid", IngredientGroup.HYDRATION);
        ingredient2.setNotRecommendedSkinProperties(new SkinProperties());
        ingredient2.setTargetedSkinIssues(new HashSet<SkinIssue>() {{
            add(FINE_LINES);
            add(WRINKLES);
            add(DRYNESS);
            add(INFLAMMATION);
        }});
        ingredientRepository.save(ingredient2);

        final Ingredient ingredient3 = new Ingredient("Glycerin", IngredientGroup.HYDRATION);
        SkinProperties skinProperties1 = new SkinProperties();
        skinProperties1.setSebum(Assessment.HIGH);
        ingredient3.setNotRecommendedSkinProperties(skinProperties1);
        ingredient3.setTargetedSkinIssues(new HashSet<SkinIssue>() {{
            add(DRYNESS);
            add(FLAKINESS);
            add(INFLAMMATION);
        }});
        ingredient3.setIngredientDemands(new HashSet<IngredientDemand>() {{
            add(LOW_HUMIDITY);
        }});
        ingredientRepository.save(ingredient3);


        // Skin replenishing - exfoliants
        final Ingredient ingredient4 = new Ingredient("Lactic acid", IngredientGroup.EXFOLIANT);
        ingredient4.setNotRecommendedSkinProperties(new SkinProperties());
        ingredient4.setTargetedSkinIssues(new HashSet<SkinIssue>() {{
            add(FINE_LINES);
            add(HYPERPIGMENTATION);
            add(DARK_SPOTS);
            add(SKIN_TEXTURE);
        }});
        ingredientRepository.save(ingredient4);

        final Ingredient ingredient5 = new Ingredient("Glycolic acid", IngredientGroup.EXFOLIANT);
        ingredient5.setNotRecommendedSkinProperties(new SkinProperties());
        ingredient5.setTargetedSkinIssues(new HashSet<SkinIssue>() {{
            add(FINE_LINES);
            add(WRINKLES);
            add(HYPERPIGMENTATION);
            add(DARK_SPOTS);
            add(SKIN_TEXTURE);
            add(ENLARGED_PORES);
        }});
        ingredientRepository.save(ingredient5);

        final Ingredient ingredient6 = new Ingredient("Salicylic acid", IngredientGroup.EXFOLIANT);
        ingredient6.setNotRecommendedSkinProperties(new SkinProperties());
        ingredient6.setTargetedSkinIssues(new HashSet<SkinIssue>() {{
            add(BLACKHEADS);
            add(WHITEHEADS);
            add(SKIN_TEXTURE);
            add(ENLARGED_PORES);
            add(CLOGGED_PORES);
        }});
        ingredientRepository.save(ingredient6);

        final Ingredient ingredient7 = new Ingredient("Benzoyl Peroxide", IngredientGroup.EXFOLIANT);
        ingredient7.setNotRecommendedSkinProperties(new SkinProperties());
        ingredient7.setTargetedSkinIssues(new HashSet<SkinIssue>() {{
            add(ACNE);
            add(INFLAMMATION);
            add(ENLARGED_PORES);
        }});
        ingredient7.setIngredientDemands(new HashSet<IngredientDemand>() {{
            add(LOW_SUN_EXPOSURE);
        }});
        ingredientRepository.save(ingredient7);


        // Antioxidants (most effective when combined)
        final Ingredient ingredient8 = new Ingredient("Vitamin E", IngredientGroup.ANTIOXIDANT);
        ingredient8.setNotRecommendedSkinProperties(new SkinProperties());
        ingredient8.setTargetedSkinIssues(new HashSet<SkinIssue>() {{
            add(DRYNESS);
            add(INFLAMMATION);
            add(DARK_SPOTS);
        }});
        ingredientRepository.save(ingredient8);

        final Ingredient ingredient9 = new Ingredient("Vitamin C", IngredientGroup.ANTIOXIDANT);
        ingredient9.setNotRecommendedSkinProperties(new SkinProperties());
        ingredient9.setTargetedSkinIssues(new HashSet<SkinIssue>() {{
            add(FINE_LINES);
            add(SCARRING);
            add(WRINKLES);
            add(DULLNESS);
            add(HYPERPIGMENTATION);
            add(DARK_SPOTS);
            add(SUN_DAMAGE);
        }});
        ingredientRepository.save(ingredient9);

        final Ingredient ingredient10 = new Ingredient("Green tea", IngredientGroup.ANTIOXIDANT);
        ingredient10.setNotRecommendedSkinProperties(new SkinProperties());
        ingredient10.setTargetedSkinIssues(new HashSet<SkinIssue>() {{
            add(ROSACEA);
            add(INFLAMMATION);
            add(SUN_DAMAGE);
            add(REDNESS);
        }});
        ingredientRepository.save(ingredient10);

        // Skin restoring
        final Ingredient ingredient11 = new Ingredient("Hydroquinone", IngredientGroup.SKIN_RESTORING);
        ingredient11.setNotRecommendedSkinProperties(new SkinProperties());
        ingredient11.setTargetedSkinIssues(new HashSet<SkinIssue>() {{
            add(HYPERPIGMENTATION);
            add(DARK_SPOTS);
            add(AGE_SPOTS);
        }});
        ingredientRepository.save(ingredient11);

        final Ingredient ingredient12 = new Ingredient("Retinol", IngredientGroup.SKIN_RESTORING);
        ingredient12.setNotRecommendedSkinProperties(new SkinProperties());
        ingredient12.setTargetedSkinIssues(new HashSet<SkinIssue>() {{
            add(ACNE);
            add(FINE_LINES);
            add(WRINKLES);
            add(AGE_SPOTS);
            add(CLOGGED_PORES);
            add(SCARRING);
            add(SUN_DAMAGE);
        }});
        ingredientRepository.save(ingredient12);

        final Ingredient ingredient13 = new Ingredient("Niacinamide", IngredientGroup.SKIN_RESTORING);
        ingredient13.setNotRecommendedSkinProperties(new SkinProperties());
        ingredient13.setTargetedSkinIssues(new HashSet<SkinIssue>() {{
            add(ACNE);
            add(BLACKHEADS);
            add(WHITEHEADS);
            add(ROSACEA);
            add(INFLAMMATION);
            add(HYPERPIGMENTATION);
            add(SUN_DAMAGE);
        }});
        ingredientRepository.save(ingredient13);


        // patients
        final Patient patient = new Patient("Katarina", "Tukelic", "kaca", passwordEncoder.encode("kaca"), 23);  // kaca

        // treatment in last 2 months with eczema or psoriazis, no drugs prescribed
        Set<Ingredient> recommendedIngredients = new HashSet<Ingredient>() {{
            add(ingredient1);
            add(ingredient4);
            add(ingredient8);
            add(ingredient11);
        }};
        Set<SkinIssue> treatedSkinIssues = new HashSet<SkinIssue>() {{
            add(ECZEMA);
            add(PSORIAZIS);
        }};
        Treatment treatment = new Treatment(null, getPastDate(new Date(), 1), patient, Drug.NONE, recommendedIngredients, treatedSkinIssues);

        patient.getPreviousTreatments().add(treatment);
        userRepository.save(patient);
        LOGGER.info("Database has been initialized");
    }

    private Date getPastDate(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -days);
        return cal.getTime();
    }
}
