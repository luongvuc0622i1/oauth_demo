package com.example.demooauth2.controller.server;

import com.example.demooauth2.model.Email;
import com.example.demooauth2.service.MailSender;
import com.example.demooauth2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class MailController {

    @Autowired
    private  MailSender mailSender;
    @Autowired
    private UserService userService;

    @GetMapping("/inputEmail")
    public ModelAndView sendMail(){
        return new ModelAndView("inputEmail");
    }

    @PostMapping("/inputEmail")
    public ModelAndView sendMail(@RequestParam String email, HttpServletRequest request){
        email = request.getParameter("email");
        if(!(userService.notExistEmail(email))) {
            return new ModelAndView("redirect:http://localhost:8084/verifyCode?email="+email);
        }
        else{
            return new ModelAndView("error");
        }
    }
}