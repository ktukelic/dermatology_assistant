package sbnz.blisskin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sbnz.blisskin.model.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
