package com.appsql.auth;

import com.appsql.user.User;
import com.appsql.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AuthController {
    @Autowired
    private UserService service;

    public AuthController() {
    }

    @GetMapping({"/register"})
    public String registerForm(Model model) {
        model.addAttribute("user", new User());

        return "register";
    }


    @PostMapping({"/auth/register_submit"})
    public String registerSubmit(User user, RedirectAttributes ra) {

        this.service.save(user);
        ra.addFlashAttribute("message", "The user has been saved successfully.");
        return "redirect:/users";
    }

    @GetMapping({"login"})
    public String showLoginForm(Model model) {
        //model.addAttribute("user", new User());
        return "login";
    }

}