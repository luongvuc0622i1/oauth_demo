package com.example.oauth2client.controller;

import com.example.oauth2client.service.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@CrossOrigin("*")
public class MailController {

    @Autowired
    private MailSender mailSender;

    @GetMapping("/verifyCode")
    public ModelAndView verifyCode() {
        return new ModelAndView("redirect:http://localhost:8081/inputEmail");
    }

}
