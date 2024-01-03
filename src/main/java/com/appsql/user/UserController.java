package com.appsql.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService service;

    public UserController() {
    }

    @GetMapping({"/users"})
    public String showUserList(Model model) {
        List<User> listUsers = this.service.listAll();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }

    @GetMapping({"/users/new"})
    public String showNewForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Add New User");
        return "user_form";
    }

    @PostMapping({"/users/save"})
    public String saveUser(User user, RedirectAttributes ra) {

        this.service.save(user);
        ra.addFlashAttribute("message", "The user has been saved successfully.");
        return "redirect:/users";
    }

    @GetMapping({"/users/edit/{id}"})
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            User user = this.service.get(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit User (ID: " + id + ")");
            return "user_form";
        } catch (UserNotFoundException var5) {
            ra.addFlashAttribute("message", var5.getMessage());
            return "redirect:/users";
        }
    }

    @GetMapping({"/users/delete/{id}"})
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            this.service.delete(id);
            ra.addFlashAttribute("message", "The user ID " + id + " has been deleted.");
        } catch (UserNotFoundException var4) {
            ra.addFlashAttribute("message", var4.getMessage());
        }

        return "redirect:/users";
    }
}
