package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.LoginService;
import com.udacity.jwdnd.course1.cloudstorage.model.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller()
@RequestMapping("/signup")
public class SignupController {

    private final UsersService usersService;

    // Constructor
    public SignupController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping()
    public String signupView() {
        return "signup";
    }

    @PostMapping()
    public String signupUser(@ModelAttribute Users users,
                             LoginService loginService,
                             Model model) {

        String signupError = null;

        if (!usersService.isUsernameAvailable(users.getUserName())) {
            signupError = "The username already exists in the database";
        }

        if (signupError == null) {
            int rowsAdded = usersService.createUser(users);
            if (rowsAdded < 0) {
                signupError = "We could not sign you in. Please try again.";
            }
        }

        if (signupError == null) {
            model.addAttribute("signupSuccess", true);
            loginService.setSignupSuccess(true);
            return "redirect:/login";
        } else {
            model.addAttribute("signupError", signupError);
        }

        return "signup";
    }
}