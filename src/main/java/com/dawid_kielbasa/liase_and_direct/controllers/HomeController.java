package com.dawid_kielbasa.liase_and_direct.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {

    @GetMapping()
    public String index() {
        return "index";
    }

//    @GetMapping
//    public ResponseEntity<String> loginPage() {
//        return ResponseEntity.ok("Connected succesfully");
//    }
}
