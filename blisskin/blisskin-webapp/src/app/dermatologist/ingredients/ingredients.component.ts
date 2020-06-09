import {Component, OnInit} from '@angular/core';
import {IngredientsService} from '../../services/ingredients.service';
import {Ingredient} from '../../model/ingredient';

@Component({
  selector: 'app-ingredients',
  templateUrl: './ingredients.component.html',
  styleUrls: ['./ingredients.component.scss']
})
export class IngredientsComponent implements OnInit {
  ingredients: any[] = [];
  selectedIngredient: Ingredient = null;

  constructor(private ingredientsService: IngredientsService) {
  }

  ngOnInit(): void {
    this.ingredientsService.findAll().subscribe(response => {
      this.ingredients = response;
    });
  }

  onGetIngredientInfo(ingredientId) {
    this.ingredientsService.findOneById(ingredientId).subscribe(response => {
      this.selectedIngredient = response;
      console.log(this.selectedIngredient)
    });
  }

}
