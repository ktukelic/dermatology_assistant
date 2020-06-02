package sbnz.blisskin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sbnz.blisskin.service.IngredientService;
import sbnz.blisskin.service.ReasoningService;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {

    private final IngredientService ingredientService;
    private final ReasoningService reasoningService;

    public IngredientController(IngredientService ingredientService, ReasoningService reasoningService) {
        this.ingredientService = ingredientService;
        this.reasoningService = reasoningService;
    }

    @GetMapping
    public ResponseEntity findAll() {
        return new ResponseEntity(ingredientService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity findIngredientInformation(@PathVariable("id") Long id) {
        return new ResponseEntity(reasoningService.findIngredientInformation(id), HttpStatus.OK);
    }
}
