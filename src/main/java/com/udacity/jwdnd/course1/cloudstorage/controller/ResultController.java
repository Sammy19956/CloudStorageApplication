package com.udacity.jwdnd.course1.cloudstorage.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/result")
public class ResultController {

    @GetMapping
    public String showResultPage(@RequestParam("isOk") Boolean isOk,
                                 @RequestParam("message") String message,
                                 Model model) {

        model.addAttribute("success", isOk);
        model.addAttribute("message", message);
        return "result";
    }
}
