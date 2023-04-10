package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.request.CredentialsForm;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.UsersService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/credential")
public class CredentialController {

    private CredentialsService credentialsService;
    private UsersService usersService;

    // Constructor
    public CredentialController(UsersService usersService, CredentialsService credentialsService) {
        this.usersService = usersService;
        this.credentialsService = credentialsService;
    }

    @PostMapping("/save")
    public String createCredential(Authentication auth,
                                   @ModelAttribute("credential") CredentialsForm credForm,
                                   Model model) {

        Boolean isOk;
        String message = null;

        if (credentialsService.addCredential(credForm.getCredentialId(),
                                             credForm.getUrl(),
                                             credForm.getUserName(),
                                             credForm.getPassword(),
                                             usersService.getLoggedInUserId(auth)) < 1) {
            isOk = false;
            message = "Credentials not saved successfully";
        }
        else{
            isOk = true;
        }

        return "redirect:/result?isOk=" + isOk+"&message="+message;
    }

    @GetMapping("/delete")
    public String deleteCredential(@RequestParam("credentialId") Integer credentialId) {

        String message = null;
        Boolean isOk = credentialId > 0;

        if (isOk) {
            credentialsService.deleteCredential(credentialId);
        }
        else{
            message = "Credentials not deleted successfully";
        }

        return "redirect:/result?isOk=" + isOk+"&message="+message;
    }
}
