package services;

import dao.HibernateUtil;
import model.Patient;
import model.Recipe;

public class RecipeService {


        HibernateUtil<Recipe> accountGenericDao = new HibernateUtil<>();

        public void addAccount(Recipe account) { accountGenericDao.add(account); }
}
