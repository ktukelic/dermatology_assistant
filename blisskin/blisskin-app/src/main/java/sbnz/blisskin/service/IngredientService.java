package sbnz.blisskin.service;

import org.springframework.stereotype.Service;
import sbnz.blisskin.exceptions.NotFoundException;
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

    public Ingredient findById(Long id) {
        return ingredientRepository.findById(id).orElseThrow(() -> new NotFoundException("Ingredient with given id not found"));
    }

    public void delete(Long id) {
        ingredientRepository.delete(findById(id));
    }
}
