package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignUpController {

    private final UserService userService;

    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getSignUpPage(){
        return "signup";
    }

    @PostMapping()
    public String addUser(@ModelAttribute User user, Model model){
//        System.out.println(user);

        String signUpError = null;

        if (!userService.checkUserNameAvailability(user.getUsername())){
            signUpError = "Ohh! user name is already taken";
        }

        if (signUpError == null){
            int rowAdded = userService.addNewUser(user);
            if (rowAdded < 0){
                signUpError = "oops! error in sign up, Please try again";
            }
        }
        if (signUpError == null){
            model.addAttribute("signupSuccess", true);
            return "login";
        }else {
            model.addAttribute("signUpError", signUpError);
        }

        return "login";
    }

}
