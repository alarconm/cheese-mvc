package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.data.CategoryDao;
import org.launchcode.cheesemvc.models.data.CheeseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
