package com.example.demooauth2.controller.server.oauth2;

import com.example.demooauth2.model.Role;
import com.example.demooauth2.model.User;
import com.example.demooauth2.repository.RoleRepository;
import com.example.demooauth2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/account")
    public String login() {
        return "account";
    }

    @PostMapping("/account")
    public ModelAndView login(HttpServletRequest request){
        return new ModelAndView("redirect:http://localhost:8084/home");
    }

    @PostMapping("/register")
    public String demo(HttpServletRequest request, HttpSession session) {
        String username = request.getParameter("newUsername");
        String email = request.getParameter("email");
        String password = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        if (password.equals(confirmPassword) && userService.notExistEmail(email)) {
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(new BCryptPasswordEncoder(10).encode(password));
            user.setEnabled(true);
            user.setAccountNonLocked(true);
            user.setAccountNonExpired(true);
            user.setCredentialsNonExpired(true);
            Role role = roleRepository.findById(2).get();
            List<Role> roles = new ArrayList<>();
            roles.add(role);
            user.setRoles(roles);
            userService.save(user);

            session.setAttribute("client_id", "mobile");
            session.setAttribute("response_type", "code");
            session.setAttribute("redirect_uri", "http://localhost:8082/oauth/callback");
            session.setAttribute("scope", "WRITE");
            return "account";
        }
        return "error";
    }

    @GetMapping("/forgot")
    public ModelAndView forgot() {
        return new ModelAndView("forgot-password");
    }

    @GetMapping("/ChangePassword")
    public ModelAndView changePassword(){
        return new ModelAndView("ChangePassword");
    }

    @PostMapping("/ChangePassword")
    public ModelAndView changePassword(HttpServletRequest request, HttpSession session){
        String password = request.getParameter("password");
        String cofimPassword = request.getParameter("re_password");
        String email = request.getParameter("email");
        User u = userService.findByEmail(email);
        if((password.equals(cofimPassword)) && (u != null)){
            userService.changePassword(u,password);
            session.setAttribute("client_id", "mobile");
            session.setAttribute("response_type", "code");
            session.setAttribute("redirect_uri", "http://localhost:8082/oauth/callback");
            session.setAttribute("scope", "WRITE");
            return new ModelAndView("account");
        }
        return  new ModelAndView("error");
    }
}
