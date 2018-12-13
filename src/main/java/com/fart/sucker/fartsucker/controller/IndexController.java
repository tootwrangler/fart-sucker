package com.fart.sucker.fartsucker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String fartSuckerUI() {
        return "index.html";
    }
}
