package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.Cheese;
import org.launchcode.cheesemvc.models.Category;
import org.launchcode.cheesemvc.models.Menu;
import org.launchcode.cheesemvc.models.data.CategoryDao;
import org.launchcode.cheesemvc.models.data.CheeseDao;
import org.launchcode.cheesemvc.models.data.MenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("cheese")
public class CheeseController {

    @Autowired
    private CheeseDao cheeseDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private MenuDao menuDao;

    // Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("cheeses", cheeseDao.findAll());
        model.addAttribute("categories", categoryDao.findAll());
        model.addAttribute("title", "My Cheeses");
        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        model.addAttribute(new Cheese()); //Default attributes makes it all lower case of class IE: "cheese"
        model.addAttribute("categories", categoryDao.findAll());
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@ModelAttribute @Valid Cheese newCheese, Errors errors,
                                       @RequestParam int categoryId, Model model) {

        /*
        * What model binding is doing for us!
        * Cheese newCheese = new Cheese();
        * newCheese.setName(Request.getParameter("name"));
        * newCheese.setDescription(Request.getParameter("description"));
        *
        */

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Cheese");
            model.addAttribute("categories", categoryDao.findAll());
            return "cheese/add";
        }

        Category cat = categoryDao.findOne(categoryId);
        newCheese.setCategory(cat);
        cheeseDao.save(newCheese);
        // Redirect to /cheese
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheese(Model model) {
        model.addAttribute("title", "Remove Cheese");
        model.addAttribute("cheeses", cheeseDao.findAll());

        return "cheese/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam int[] ids) {

        for (int cheeseId : ids) {
            Cheese cheese = cheeseDao.findOne(cheeseId);
            List<Menu> menus = cheese.getMenus();

            for (Menu menu : menus) {
                menu.removeItem(cheese);
            }
        }

        for (int id : ids) {

            cheeseDao.delete(id);
        }

        return "redirect:";
    }

    @RequestMapping(value = "category/{categoryId}", method = RequestMethod.GET)
    public String category(Model model, @PathVariable int categoryId) {

        Category cat = categoryDao.findOne(categoryId);
        List<Cheese> cheeses = cat.getCheeses();
        model.addAttribute("cheeses", cheeses);
        model.addAttribute("title", "Cheeses in Category: " + cat.getName());
        model.addAttribute("category", cat);
        return "cheese/index";

    }

    @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int cheeseId) {
        model.addAttribute("cheese", cheeseDao.findOne(cheeseId));
        model.addAttribute("categories", categoryDao.findAll());

        return "cheese/edit";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String processEditForm( Model model, @ModelAttribute @Valid Cheese newCheese,
                                   @RequestParam int categoryId, Errors errors) {


        if (errors.hasErrors()) {
            model.addAttribute("title", "Edit Cheese");
            model.addAttribute("cheese", newCheese);
            model.addAttribute("categories", categoryDao.findAll());
            return "cheese/edit";
        }

        cheeseDao.findOne(newCheese.getId()).setName(newCheese.getName());
        cheeseDao.findOne(newCheese.getId()).setDescription(newCheese.getDescription());
        cheeseDao.findOne(newCheese.getId()).setCategory(newCheese.getCategory());
        cheeseDao.findOne(newCheese.getId()).setRating(newCheese.getRating());
        cheeseDao.save(cheeseDao.findOne(newCheese.getId()));

        return "redirect:";
    }
}
