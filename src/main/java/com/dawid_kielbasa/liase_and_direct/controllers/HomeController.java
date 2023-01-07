package com.dawid_kielbasa.liase_and_direct.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * This controller class has no use, except I want to use app without frontend React part. In resources is index.html site which uses thymeleaf framework to make templates.
 */
@Controller
public class HomeController {

    @GetMapping()
    public String index() {
        return "index";
    }
}
