package com.appsql.auth;

import com.appsql.user.User;
import com.appsql.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.core.Authentication;

import java.util.List;

@Controller
public class AuthController {
    @Autowired
    private UserService service;

    public AuthController() {
    }

    @GetMapping({"register"})
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

    @RequestMapping({"login"})
    public String showLoginForm(Authentication authentication, Model model) {

        String str = this.service.getCurrentUsername();
System.out.println(str);
        if (authentication != null && authentication.isAuthenticated()) {
            model.addAttribute("username", authentication.getName());
        }
        //model.addAttribute("user", new User());
        return "login";
    }

    // Login form with error
    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

}