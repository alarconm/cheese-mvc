package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.Cheese;
import org.launchcode.cheesemvc.models.CheeseData;
import org.launchcode.cheesemvc.models.CheeseTypes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("cheese")
public class CheeseController {

    // Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("cheeses", CheeseData.getAll());
        model.addAttribute("title", "My Cheeses");
        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        model.addAttribute(new Cheese()); //Default attributes makes it all lower case of class IE: "cheese"
        model.addAttribute("cheeseTypes", CheeseTypes.values());
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@ModelAttribute @Valid Cheese newCheese, Errors errors, Model model) {

        /*
        * What model binding is doing for us!
        * Cheese newCheese = new Cheese();
        * newCheese.setName(Request.getParameter("name"));
        * newCheese.setDescription(Request.getParameter("description"));
        *
        */

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Cheese");
            model.addAttribute("cheese", newCheese);
            model.addAttribute("cheeseTypes", CheeseTypes.values());
            return "cheese/add";
        }

        CheeseData.add(newCheese);
        // Redirect to /cheese
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheese(Model model) {
        model.addAttribute("title", "Remove Cheese");
        model.addAttribute("cheeses", CheeseData.getAll());

        return "cheese/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam int[] cheeseIds) {

        for (int cheeseId : cheeseIds) {
            CheeseData.remove(cheeseId);
        }

        return "redirect:";
    }

    @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int cheeseId) {
        model.addAttribute("cheese", CheeseData.getById(cheeseId));
        model.addAttribute("cheeseTypes", CheeseTypes.values());

        return "cheese/edit";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String processEditForm( CheeseTypes type, @ModelAttribute @Valid Cheese newCheese, Errors errors, Model model) {


        if (errors.hasErrors()) {
            model.addAttribute("title", "Edit Cheese");
            model.addAttribute("cheese", newCheese);
            model.addAttribute("cheeseTypes", CheeseTypes.values());
            return "cheese/edit";
        }

        CheeseData.getById(newCheese.getCheeseId()).setName(newCheese.getName());
        CheeseData.getById(newCheese.getCheeseId()).setDescription(newCheese.getDescription());
        CheeseData.getById(newCheese.getCheeseId()).setType(newCheese.getType());
        CheeseData.getById(newCheese.getCheeseId()).setRating(newCheese.getRating());

        return "redirect:";
    }
}
