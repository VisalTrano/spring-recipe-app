package guru.springframework.controllers;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.domian.Recipe;
import guru.springframework.exceptions.NotFoundException;
import guru.springframework.services.category.CategoryService;
import guru.springframework.services.recipes.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Set;

@Slf4j
@Controller
public class RecipeController {
    private final RecipeService recipeService;
    private final CategoryService categoryService;

    public RecipeController(RecipeService recipeService, CategoryService categoryService) {
        this.recipeService = recipeService;
        this.categoryService = categoryService;
    }

    @RequestMapping("/recipe/{id}/show")
    public String showBYId(@PathVariable String id, Model model) {
        model.addAttribute("recipe",  recipeService.findById(id).block());
        return "recipe/show";
    }

    @RequestMapping("/recipe/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());
        model.addAttribute("categories", categoryService.getCategoryCommands());

        return "recipe/recipeform";
    }

    @RequestMapping("/recipe/{id}/update")
    public String updateRecipe(Model model, @PathVariable String id) {
        model.addAttribute("recipe", recipeService.findCommandById(id));
        model.addAttribute("categories", categoryService.getCategoryCommands());
        return "recipe/recipeform";
    }

    @RequestMapping("/recipe/{id}/delete")
    public String deleteRecipe(@PathVariable String id) {
        recipeService.deleteById(id);
        return "redirect:/";
    }

    @PostMapping
    @RequestMapping("/recipe")
    public String saveOrUpdate(@Valid @ModelAttribute("recipe") RecipeCommand recipeCommand, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            return "recipe/recipeform";
        }
        if (recipeCommand.getId() != null) {
            Set<IngredientCommand> ingredientCommands = recipeService.findCommandById(recipeCommand.getId()).getIngredients();
            recipeCommand.setIngredients(ingredientCommands);
        }
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(recipeCommand).block();
        return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }


}
