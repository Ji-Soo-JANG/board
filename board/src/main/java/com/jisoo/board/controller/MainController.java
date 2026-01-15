package com.jisoo.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    String home(Model model) {
        System.out.println("home");
        return "views/home";
    }

    @GetMapping("/login")
    String login() {
        System.out.println("login");
        return "views/login";
    }
}
