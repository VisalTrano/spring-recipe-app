package guru.springframework.controllers;

import guru.springframework.commands.IngredientCommand;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.services.ingredient.IngredientService;
import guru.springframework.services.recipes.RecipeService;
import guru.springframework.services.uom.UnitOfMeasureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class IngredientController {
    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping("/recipe/{recipe_id}/ingredients")
    public String listIngredients(Model model, @PathVariable String recipe_id) {
        model.addAttribute("recipe", recipeService.findCommandById(new Long(recipe_id)));
        return "recipe/ingredient/list";
    }

    @GetMapping(value = {"/ingredient/{id}/show"})
    public String showById(Model model, @PathVariable("id") String id) {
//        IngredientCommand ingredient= ingredientService.findByRecipeIdAndIngredientId(new Long(recipe_id),new Long(id));
        IngredientCommand ingredient = ingredientService.findCommandById(new Long(id));
        model.addAttribute("ingredient", ingredient);
        return "recipe/ingredient/show";
    }

    @GetMapping(value = {"/recipe/{recipe_id}/ingredient/{id}/show"})
    public String showByIdInRecipe(Model model, @PathVariable("recipe_id") String recipe_id, @PathVariable("id") String id) {
        return this.showById(model, id);
    }

    @GetMapping(value = {"/ingredient/{id}/update"})
    public String update(Model model, @PathVariable String id) {
        model.addAttribute("ingredient", ingredientService.findCommandById(new Long(id)));
        model.addAttribute("uomList", unitOfMeasureService.getUnitOfMeasures());
        return "recipe/ingredient/ingredientform";
    }

    @GetMapping(value = {"/recipe/{recipe_id}/ingredient/new"})
    public String newIngredient(Model model, @PathVariable String recipe_id) {
        IngredientCommand ingredientCommand = new IngredientCommand().builder().recipeId(new Long(recipe_id)).unitOfMeasure(new UnitOfMeasureCommand()).build();
        model.addAttribute("uomList", unitOfMeasureService.getUnitOfMeasures());
        model.addAttribute("ingredient", ingredientCommand);

        return "recipe/ingredient/ingredientform";
    }

    @GetMapping(value = {"/recipe/{recipe_id}/ingredient/{id}/update"})
    public String updateInRecipe(Model model, @PathVariable String recipe_id, @PathVariable String id) {
        return this.update(model, id);
    }

    @PostMapping("/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand ingredientCommand) {
        IngredientCommand saveCommand = ingredientService.saveIngredientCommand2(ingredientCommand);
        return "redirect:/recipe/" + ingredientCommand.getRecipeId() + "/ingredient/" + saveCommand.getId() + "/show";
//        return "redirect:/ingredient/5/show";
    }

    @PostMapping("/recipe/{recipe_id}/ingredient")
    public String saveOrUpdate(@PathVariable String recipe_id, @ModelAttribute IngredientCommand ingredientCommand) {
        return this.saveOrUpdate(ingredientCommand);
    }

    @GetMapping(value = {"/recipe/{recipe_id}/ingredient/{id}/delete"})
    public String delete(@PathVariable String recipe_id, @PathVariable String id) {
        ingredientService.deletedById(new Long(id));
        return "redirect:/recipe/" + recipe_id + "/ingredients";

    }
}
