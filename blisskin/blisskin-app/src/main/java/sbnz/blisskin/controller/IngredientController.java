package sbnz.blisskin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
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
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('DERMATOLOGIST')")
    public ResponseEntity findAll() {
        return new ResponseEntity(ingredientService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('DERMATOLOGIST')")
    public ResponseEntity findIngredientInformation(@PathVariable("id") Long id) {
        return new ResponseEntity(reasoningService.findIngredientInformation(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        ingredientService.delete(id);
    }
}
