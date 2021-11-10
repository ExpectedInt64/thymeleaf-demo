package com.example.thymeleafdemo.controller;

import com.example.thymeleafdemo.DTO.UserDTO;
import com.example.thymeleafdemo.UUIDGenerator;
import com.example.thymeleafdemo.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;

@Controller
public class GreetingsController {
    @GetMapping("/")
    public String greeting(HttpServletResponse response, HttpServletRequest request) {
        if (request.getCookies() == null) {
            return "notindex";
        }
        if (!Arrays.stream(request.getCookies()).anyMatch(pepega -> pepega.getName().equals("loggedin"))) {
            Cookie uiColorCookie = new Cookie("loggedin", "false");
            response.addCookie(uiColorCookie);
        }
        System.out.println(UUIDGenerator.generateType1UUID());
        return "notindex";
    }

    @ModelAttribute("getUsers")
    public String users() {
        return UserRepository.getInstance().getUsers().toString();
    }

    @ModelAttribute("loginStatus")
    public String loggedin(@CookieValue(value = "loggedin", defaultValue = "false") String loggedin) {
        return loggedin.equals("true") ? "logout" : "login";
    }

    @GetMapping("/login")
    public String login(HttpServletResponse response) {
        Cookie loginCookie = new Cookie("loggedin", "true");
        loginCookie.setMaxAge(60);
        response.addCookie(loginCookie);
        System.out.println("Logging in");
        return "login";
    }

    @ModelAttribute("login")
    public void validateLogin(HttpServletResponse response, String username, String password) {
        if (username == null || password == null)
            return;
        UserRepository userRepository = UserRepository.getInstance();
        for (UserDTO u : userRepository.getUsers()) {
            if (username.equals(u.getUsername()))
                if (password.equals(u.getPassword())) {
                    u.setSession(UUIDGenerator.generateType1UUID());
                    Cookie loginCookie = new Cookie("session", u.getSession().toString());
                    loginCookie.setMaxAge(60);
                    response.addCookie(loginCookie);
                    System.out.println("New session: " + u.getSession().toString());
                }
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {
        Cookie loginCookie = new Cookie("loggedin", "");
        loginCookie.setMaxAge(0);
        response.addCookie(loginCookie);
        System.out.println("Logging out");
        return "logout";
    }

    @ModelAttribute("testcookie")
    public String readCookie(@CookieValue(value = "loggedin", defaultValue = "false") String loggedin) {
        String temp = "not logged in";
        if (loggedin.equals("true")) {
            temp = "logged in";
        }
        return "Hey! I am: " + temp;
    }
}
