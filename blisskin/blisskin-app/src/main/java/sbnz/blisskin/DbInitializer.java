package sbnz.blisskin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import sbnz.blisskin.model.Dermatologist;
import sbnz.blisskin.model.Ingredient;
import sbnz.blisskin.model.SkinIssue;
import sbnz.blisskin.repository.IngredientRepository;
import sbnz.blisskin.repository.SkinIssueRepository;
import sbnz.blisskin.repository.UserRepository;

/**
 * This component is started only when app.db-init property is set to true
 */

@Component
@ConditionalOnProperty(name = "app.db-init", havingValue = "true")
public class DbInitializer implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(DbInitializer.class);

    private SkinIssueRepository skinIssueRepository;
    private IngredientRepository ingredientRepository;
    private UserRepository userRepository;

    public DbInitializer(SkinIssueRepository skinIssueRepository,
                         IngredientRepository ingredientRepository,
                         UserRepository userRepository) {
        this.skinIssueRepository = skinIssueRepository;
        this.ingredientRepository = ingredientRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        this.skinIssueRepository.deleteAll();

        final Dermatologist dermatologist = userRepository.save(new Dermatologist("derm", "derm"));

        final SkinIssue skinIssue1 = skinIssueRepository.save(new SkinIssue("Acne"));
        final SkinIssue skinIssue2 = skinIssueRepository.save(new SkinIssue("Blackheads"));
        final SkinIssue skinIssue3 = skinIssueRepository.save(new SkinIssue("Whiteheads"));
        final SkinIssue skinIssue4 = skinIssueRepository.save(new SkinIssue("Inflammation"));
        final SkinIssue skinIssue5 = skinIssueRepository.save(new SkinIssue("Eczema"));     // issue sa prioritetom
        final SkinIssue skinIssue6 = skinIssueRepository.save(new SkinIssue("Psoriazis"));
        final SkinIssue skinIssue7 = skinIssueRepository.save(new SkinIssue("Hives"));
        final SkinIssue skinIssue8 = skinIssueRepository.save(new SkinIssue("Sun damage"));
        final SkinIssue skinIssue9 = skinIssueRepository.save(new SkinIssue("Rosacea"));
        final SkinIssue skinIssue10 = skinIssueRepository.save(new SkinIssue("Dryness"));
        final SkinIssue skinIssue11 = skinIssueRepository.save(new SkinIssue("Flakiness"));
        final SkinIssue skinIssue12 = skinIssueRepository.save(new SkinIssue("Wrinkles"));
        final SkinIssue skinIssue13 = skinIssueRepository.save(new SkinIssue("Fine lines"));
        final SkinIssue skinIssue14 = skinIssueRepository.save(new SkinIssue("Redness"));
        final SkinIssue skinIssue15 = skinIssueRepository.save(new SkinIssue("Skin texture"));
        final SkinIssue skinIssue16 = skinIssueRepository.save(new SkinIssue("Hyperpigmentation"));
        final SkinIssue skinIssue17 = skinIssueRepository.save(new SkinIssue("Enlarged pores"));
        final SkinIssue skinIssue18 = skinIssueRepository.save(new SkinIssue("Clogged pores"));
        final SkinIssue skinIssue19 = skinIssueRepository.save(new SkinIssue("Dark spots"));
        final SkinIssue skinIssue20 = skinIssueRepository.save(new SkinIssue("Dulness"));
        final SkinIssue skinIssue21 = skinIssueRepository.save(new SkinIssue("Scarring"));
        final SkinIssue skinIssue22 = skinIssueRepository.save(new SkinIssue("Age spots"));

        /*
         * Ingredients for recommendation
         */

        // Skin replenishing - hydratation
        final Ingredient ingredient1 = ingredientRepository.save(new Ingredient("Ceramides"));
        final Ingredient ingredient2 = ingredientRepository.save(new Ingredient("Hyaluronic Acid"));
        final Ingredient ingredient3 = ingredientRepository.save(new Ingredient("Glycerin"));

        // Skin replenishing - exfoliants
        final Ingredient ingredient4 = ingredientRepository.save(new Ingredient("Lactic acid"));
        final Ingredient ingredient5 = ingredientRepository.save(new Ingredient("Glycolic acid"));
        final Ingredient ingredient6 = ingredientRepository.save(new Ingredient("Salicylic acid"));
        final Ingredient ingredient7 = ingredientRepository.save(new Ingredient("Benzoyl Peroxide"));

        // Antioxidants (most effective when combined)
        final Ingredient ingredient8 = ingredientRepository.save(new Ingredient("Vitamin E"));
        final Ingredient ingredient9 = ingredientRepository.save(new Ingredient("Vitamin C"));
        final Ingredient ingredient10 = ingredientRepository.save(new Ingredient("Green tea"));

        // Skin restoring
        final Ingredient ingredient11 = ingredientRepository.save(new Ingredient("Hydroquinone "));
        final Ingredient ingredient12 = ingredientRepository.save(new Ingredient("Retinol"));
        final Ingredient ingredient13 = ingredientRepository.save(new Ingredient("Niacinamide"));

        LOGGER.info("Database has been initialized");
    }
}