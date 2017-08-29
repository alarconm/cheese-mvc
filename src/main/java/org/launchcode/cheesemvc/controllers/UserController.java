package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping(value = "add")
    public String add(Model model) {

        return "user/add";
    }

    @RequestMapping( value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute User user, String verify) {

        if (user.getPassword().equals(verify)) {
            model.addAttribute("user", user);
            return "user/index";
        } else {
            model.addAttribute("username", user.getUsername());
            model.addAttribute("email", user.getEmail());
            model.addAttribute("message", "Sorry passwords didn't match");
            return "user/add";
        }

    }


}
