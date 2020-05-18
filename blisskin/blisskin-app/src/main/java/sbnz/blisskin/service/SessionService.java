package sbnz.blisskin.service;

import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbnz.blisskin.model.Ingredient;
import sbnz.blisskin.repository.IngredientRepository;

import java.util.List;

@Service
public class SessionService {

    private final KieBase kieBase;
    private final IngredientRepository ingredientRepository;

    @Autowired
    public SessionService(KieBase kieBase, IngredientRepository ingredientRepository) {
        this.kieBase = kieBase;
        this.ingredientRepository = ingredientRepository;
    }

    public KieSession initializeSession() {
        return kieBase.newKieSession();
    }

    public void insertInitialFacts(KieSession kieSession) {
        List<Ingredient> ingredients = ingredientRepository.findAll();
        ingredients.stream().forEach(kieSession::insert);
    }
}
