package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LogInController {

    @GetMapping
    public String getLogInPage(HttpServletRequest request) throws ServletException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && (!(authentication instanceof AnonymousAuthenticationToken))) {
            request.logout();
            return "login";
        }
        return "login";
    }
}
