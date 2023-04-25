package com.example.demooauth2.controller.server;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@CrossOrigin("*")
public class HomeController {

    @GetMapping("/home")
    public ModelAndView homePage(){
        return new ModelAndView("redirect:http://localhost:8084/home");
    }
}
