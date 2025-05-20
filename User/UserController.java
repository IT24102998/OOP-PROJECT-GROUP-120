package com.eventphotography.controller;

import com.eventphotography.model.User;
import com.eventphotography.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }



    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "userList";
    }

    @PostMapping("/add")
    public String addUsers(@ModelAttribute User user) throws IOException {
        userService.addUser(user);
        return "redirect:/users";
    }

    @PostMapping("/update")
    public String updateUsers(@ModelAttribute User user) throws IOException {
        userService.updateUser(user);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUsers(@PathVariable int id) throws IOException {
        userService.deleteUser(id);
        return "redirect:/users";
    }
    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable int id, Model model) {
        User user = userService.getUserById(id); // retrieve user from service
        model.addAttribute("user", user);
        return "editUser"; // JSP page to edit user
    }
}
