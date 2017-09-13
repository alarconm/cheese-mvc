package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.Category;
import org.launchcode.cheesemvc.models.data.CategoryDao;
import org.launchcode.cheesemvc.models.data.CheeseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CheeseDao cheeseDao;

    @Autowired
    CategoryDao categorydao;

    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("categories", categorydao.findAll());
        model.addAttribute("title", "Categories");
        return "category/index";
    }

    @RequestMapping(value = "add")
    public String addForm(Model model) {

        Category category = new Category();
        model.addAttribute("title", "Add Category");
        model.addAttribute("category", category);
        return "category/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Category category, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Category");
            model.addAttribute("category", category);
            return "category/add";
        }

        categorydao.save(category);
        return "redirect:";
    }
}
