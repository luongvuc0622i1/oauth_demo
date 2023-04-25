package com.example.demooauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class DemoOauth2Application {

    public static void main(String[] args) {
        SpringApplication.run(DemoOauth2Application.class, args);
        show();
    }

    @Autowired
    PasswordEncoder encoder;

    public static void show(){
        System.out.println(new BCryptPasswordEncoder(10).encode("123456"));
    }
}
