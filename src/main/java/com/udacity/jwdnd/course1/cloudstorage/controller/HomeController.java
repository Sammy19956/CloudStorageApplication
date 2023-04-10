package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.request.CredentialsForm;
import com.udacity.jwdnd.course1.cloudstorage.request.NotesForm;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final UsersService usersService;
    private final FilesService filesService;
    private final NotesService notesService;
    private final CredentialsService credentialsService;

    // Constructor
    public HomeController(UsersService usersService,
                            FilesService filesService,
                            NotesService notesService,
                            CredentialsService credentialsService){

        this.usersService = usersService;
        this.filesService = filesService;
        this.notesService = notesService;
        this.credentialsService = credentialsService;

    }

    @GetMapping
    public String getHomePage(Authentication authentication,
                              @ModelAttribute("note") NotesForm notesForm,
                              @ModelAttribute("credential") CredentialsForm credentialsForm,
                              Model model) {

        Integer userId = usersService.getLoggedInUserId(authentication);
        model.addAttribute("files", filesService.getAllFiles((userId)));
        model.addAttribute("notes", notesService.getAllNotes(userId));
        model.addAttribute("credentials", credentialsService.getAllUserCredentials(userId));
        model.addAttribute("credentialsService", credentialsService);

        return "home";
    }
}