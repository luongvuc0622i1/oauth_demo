package com.example.oauth2client.controller;

import com.example.oauth2client.model.AccessToken;
import com.example.oauth2client.service.OAuth2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/oauth")
public class OAuth2Controller {

    private static final String CLIENT_ID = "mobile";
    private static final String CLIENT_SECRET = "pin";
    private static final String REDIRECT_URI = "http://localhost:8082/oauth/callback";

    private String accessToken;
    private String refreshToken;

    @Autowired
    private OAuth2Service oAuth2Service;

    @GetMapping("/callback")
    public ResponseEntity oauthCallback(@RequestParam Map<String, String> requestParam) {
        if (requestParam == null || requestParam.isEmpty()) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "invalid_response_type");
            response.put("error_description", "Server not supported for response_type = token");
            return ResponseEntity.ok(response);
        } else if (requestParam.containsKey("error")) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", requestParam.get("error"));
            error.put("error_description", requestParam.get("error_description"));
            return ResponseEntity.ok(error);
        }
        AccessToken bodyAccessToken = oAuth2Service.getAccessToken(CLIENT_ID, CLIENT_SECRET, REDIRECT_URI, requestParam.get("code"), requestParam.get("state"));
        accessToken = bodyAccessToken.getAccessToken();
        refreshToken = bodyAccessToken.getRefreshToken();

        return ResponseEntity.ok(bodyAccessToken);
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("redirect:http://localhost:8081/oauth/authorize?client_id=mobile&response_type=code&redirect_uri=http://localhost:8082/oauth/callback&scope=WRITE");
    }

    @GetMapping("/info")
    public ModelAndView info() {
        return new ModelAndView("redirect:http://localhost:8081/users/profile?access_token="+accessToken);
    }

    @GetMapping("/logout")
    public ModelAndView logout() {
        return new ModelAndView("redirect:http://localhost:8081/oauth/revoke_token?access_token="+accessToken+"&refresh_token="+refreshToken);
    }
}
