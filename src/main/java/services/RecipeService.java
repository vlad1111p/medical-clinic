package services;

import dao.GenericDao;
import model.Recipe;

import java.util.List;

public class RecipeService {

    GenericDao<Recipe> recipeGenericDao = new GenericDao<>();
    Recipe recipe = new Recipe();

    public void addRecipe(Recipe account) {
        recipeGenericDao.add(recipe);
    }

    public List<Recipe> getAll() {
        return recipeGenericDao.getAll(recipe);
    }
}
