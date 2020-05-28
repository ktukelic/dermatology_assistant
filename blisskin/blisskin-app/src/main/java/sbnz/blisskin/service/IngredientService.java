package sbnz.blisskin.service;

import org.springframework.stereotype.Service;
import sbnz.blisskin.model.Ingredient;
import sbnz.blisskin.repository.IngredientRepository;

import java.util.List;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public List<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }
}
