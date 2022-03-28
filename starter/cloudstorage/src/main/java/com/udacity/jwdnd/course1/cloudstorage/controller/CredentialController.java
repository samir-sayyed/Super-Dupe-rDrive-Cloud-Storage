package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/credential")
public class CredentialController {

    private final CredentialService credentialService;

    public CredentialController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @PostMapping("/add")
    public String addCredential(@ModelAttribute Credential credential, Authentication authentication, Model model){

        String error = null;
        String success = null;
//        System.out.println(credential);
//        System.out.println(authentication);
//        System.out.println(credential.getCredentialId());
        if (credential.getCredentialId() != null){
            try{
                System.out.println(credential.getCredentialId());
                credentialService.updateCredential(credential);
                success = "Credentials updated successfully";
            }catch (Exception e){
                e.printStackTrace();
                System.out.println(e.getMessage());
                error = "Error in updating credentials";
            }
        }else {
            int rowAdded = credentialService.addCredential(credential, authentication);
//            System.out.println("row added" + rowAdded);
            if (rowAdded < 0){
                error = "Error in adding credential";
            }else {
                success = "Credentials added successfully";
            }
        }

        if (error == null){
            model.addAttribute("success", success);
        }else {
            model.addAttribute("error", error);
        }

//        System.out.println(credential);
        return  "result";
    }

    @GetMapping("/delete/{credentialId}")
    public String deleteCredential(@PathVariable Integer credentialId, Model model){
        try{
            credentialService.deleteCredential(credentialId);
            model.addAttribute("success", "Credentials deleted !");
        }catch (Exception e){
            model.addAttribute("error", "error");
        }
        return "result";
    }

}
