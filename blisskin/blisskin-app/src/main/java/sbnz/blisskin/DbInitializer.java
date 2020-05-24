package sbnz.blisskin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import sbnz.blisskin.model.*;
import sbnz.blisskin.model.enumerations.IngredientGroup;
import sbnz.blisskin.repository.IngredientDemandRepository;
import sbnz.blisskin.repository.IngredientRepository;
import sbnz.blisskin.repository.SkinIssueRepository;
import sbnz.blisskin.repository.UserRepository;

import java.util.HashSet;

@Component
@ConditionalOnProperty(name = "app.db-init", havingValue = "true")
public class DbInitializer implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(DbInitializer.class);

    private SkinIssueRepository skinIssueRepository;
    private IngredientDemandRepository ingredientDemandRepository;
    private IngredientRepository ingredientRepository;
    private UserRepository userRepository;

    public DbInitializer(SkinIssueRepository skinIssueRepository,
                         IngredientRepository ingredientRepository,
                         UserRepository userRepository,
                         IngredientDemandRepository ingredientDemandRepository) {
        this.skinIssueRepository = skinIssueRepository;
        this.ingredientRepository = ingredientRepository;
        this.userRepository = userRepository;
        this.ingredientDemandRepository = ingredientDemandRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        this.skinIssueRepository.deleteAll();

        final Dermatologist dermatologist = userRepository.save(new Dermatologist("derm", "derm"));

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

        final IngredientDemand LOW_HUMIDITY = ingredientDemandRepository.save(new IngredientDemand("Low humidity"));
        final IngredientDemand HIGH_HUMIDITY = ingredientDemandRepository.save(new IngredientDemand("High humidity"));
        final IngredientDemand LOW_SUN_EXPOSURE = ingredientDemandRepository.save(new IngredientDemand("Low sun exposure"));
        final IngredientDemand HIGH_SUN_EXPOSURE = ingredientDemandRepository.save(new IngredientDemand("High sun exposure"));

        // age restrictions
        final IngredientDemand YOUNG = ingredientDemandRepository.save(new IngredientDemand("Patient age 10 to 20"));
        final IngredientDemand ADULT = ingredientDemandRepository.save(new IngredientDemand("Patient age 20 to 40"));
        final IngredientDemand MATURE = ingredientDemandRepository.save(new IngredientDemand("Patient age 40+"));

        /*
         * Ingredients for recommendation
         */

        // Skin replenishing - hydration
        final Ingredient ingredient1 = new Ingredient("Ceramides", IngredientGroup.HYDRATION);
        ingredient1.setTargetedSkinProperties(new SkinProperties());
        ingredient1.setTargetedSkinIssues(new HashSet<SkinIssue>(){{
            add(DRYNESS); add(REDNESS); add(ECZEMA); add(PSORIAZIS); add(FLAKINESS); add(FINE_LINES);
        }});
        ingredientRepository.save(ingredient1);

        final Ingredient ingredient2 = new Ingredient("Hyaluronic Acid", IngredientGroup.HYDRATION);
        ingredient2.setTargetedSkinIssues(new HashSet<SkinIssue>(){{
            add(FINE_LINES); add(WRINKLES); add(DRYNESS); add(INFLAMMATION);
        }});
        ingredientRepository.save(ingredient2);

        final Ingredient ingredient3 = new Ingredient("Glycerin", IngredientGroup.HYDRATION);
        ingredient3.setTargetedSkinIssues(new HashSet<SkinIssue>(){{
            add(DRYNESS); add(FLAKINESS); add(INFLAMMATION);
        }});
        ingredient3.setIngredientDemands(new HashSet<IngredientDemand>(){{
            add(LOW_HUMIDITY);
        }});
        ingredientRepository.save(ingredient3);


        // Skin replenishing - exfoliants
        final Ingredient ingredient4 = new Ingredient("Lactic acid", IngredientGroup.EXFOLIANT);
        ingredient4.setTargetedSkinIssues(new HashSet<SkinIssue>(){{
            add(FINE_LINES); add(HYPERPIGMENTATION); add(DARK_SPOTS); add(SKIN_TEXTURE);
        }});
        ingredientRepository.save(ingredient4);

        final Ingredient ingredient5 = new Ingredient("Glycolic acid", IngredientGroup.EXFOLIANT);
        ingredient5.setTargetedSkinIssues(new HashSet<SkinIssue>(){{
            add(FINE_LINES); add(WRINKLES); add(HYPERPIGMENTATION); add(DARK_SPOTS);
            add(SKIN_TEXTURE); add(ENLARGED_PORES);
        }});
        ingredientRepository.save(ingredient5);

        final Ingredient ingredient6 = new Ingredient("Salicylic acid", IngredientGroup.EXFOLIANT);
        ingredient6.setTargetedSkinIssues(new HashSet<SkinIssue>(){{
            add(BLACKHEADS); add(WHITEHEADS); add(SKIN_TEXTURE); add(ENLARGED_PORES); add(CLOGGED_PORES);
        }});
        ingredientRepository.save(ingredient6);

        final Ingredient ingredient7 = new Ingredient("Benzoyl Peroxide", IngredientGroup.EXFOLIANT);
        ingredient7.setTargetedSkinIssues(new HashSet<SkinIssue>(){{
            add(ACNE); add(INFLAMMATION); add(ENLARGED_PORES);
        }});
        ingredient7.setIngredientDemands(new HashSet<IngredientDemand>(){{
            add(LOW_SUN_EXPOSURE);
        }});
        ingredientRepository.save(ingredient7);


        // Antioxidants (most effective when combined)
        final Ingredient ingredient8 = new Ingredient("Vitamin E", IngredientGroup.ANTIOXIDANT);
        ingredient8.setTargetedSkinIssues(new HashSet<SkinIssue>(){{
            add(DRYNESS); add(INFLAMMATION); add(DARK_SPOTS);
        }});
        ingredientRepository.save(ingredient8);

        final Ingredient ingredient9 = new Ingredient("Vitamin C", IngredientGroup.ANTIOXIDANT);
        ingredient9.setTargetedSkinIssues(new HashSet<SkinIssue>(){{
            add(FINE_LINES); add(SCARRING); add(WRINKLES); add(DULLNESS);
            add(HYPERPIGMENTATION); add(DARK_SPOTS); add(SUN_DAMAGE);
        }});
        ingredientRepository.save(ingredient9);

        final Ingredient ingredient10 = new Ingredient("Green tea", IngredientGroup.ANTIOXIDANT);
        ingredient10.setTargetedSkinIssues(new HashSet<SkinIssue>(){{
            add(ROSACEA); add(INFLAMMATION); add(SUN_DAMAGE); add(REDNESS);
        }});
        ingredientRepository.save(ingredient10);

        // Skin restoring
        final Ingredient ingredient11 = new Ingredient("Hydroquinone", IngredientGroup.SKIN_RESTORING);
        ingredient11.setTargetedSkinIssues(new HashSet<SkinIssue>(){{
            add(HYPERPIGMENTATION); add(DARK_SPOTS); add(AGE_SPOTS);
        }});
        ingredientRepository.save(ingredient11);

        final Ingredient ingredient12 = new Ingredient("Retinol", IngredientGroup.SKIN_RESTORING);
        ingredient12.setTargetedSkinIssues(new HashSet<SkinIssue>(){{
            add(ACNE); add(FINE_LINES); add(WRINKLES); add(AGE_SPOTS);
            add(CLOGGED_PORES); add(SCARRING); add(SUN_DAMAGE);
        }});
        ingredientRepository.save(ingredient12);

        final Ingredient ingredient13 = new Ingredient("Niacinamide", IngredientGroup.SKIN_RESTORING);
        ingredient13.setTargetedSkinIssues(new HashSet<SkinIssue>(){{
            add(ACNE); add(BLACKHEADS); add(WHITEHEADS); add(ROSACEA);
            add(INFLAMMATION); add(HYPERPIGMENTATION); add(SUN_DAMAGE);
        }});
        ingredientRepository.save(ingredient13);

        LOGGER.info("Database has been initialized");
    }
}