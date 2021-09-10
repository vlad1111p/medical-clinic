package services;

import dao.HibernateUtil;
import model.Patient;
import model.Recipe;

import java.util.List;

public class RecipeService {


    HibernateUtil<Recipe> accountGenericDao = new HibernateUtil<>();

    Recipe recipe = new Recipe();

    public void addRecipe(Recipe account) {
        accountGenericDao.add(account);
    }

    public List<Recipe> getAll(){
        return accountGenericDao.getAll(recipe);
    }

}
