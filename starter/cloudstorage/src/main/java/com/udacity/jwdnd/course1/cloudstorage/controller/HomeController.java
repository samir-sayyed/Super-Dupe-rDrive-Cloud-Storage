package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    private NoteService noteService;
    private UserService userService;
    private CredentialService credentialService;
    private EncryptionService encryptionService;
    private FileService fileService;

    public HomeController(NoteService noteService, UserService userService, CredentialService credentialService, EncryptionService encryptionService, FileService fileService) {
        this.noteService = noteService;
        this.userService = userService;
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
        this.fileService = fileService;
    }

    @GetMapping()
    public String getHome(@ModelAttribute Note note, @ModelAttribute Credential credential, Model model, Authentication authentication){
//        System.out.println(credential);
        User currentUser = userService.getUser(authentication.getName());
        Integer userId = currentUser.getUserId();
//        System.out.println(userId);
        System.out.println(noteService.getAllNotes(userId));
        model.addAttribute("notes", noteService.getAllNotes(userId));
        model.addAttribute("credentials", credentialService.getAllCredentials(userId));
        model.addAttribute("credential", credential);
        model.addAttribute("encryptionService", encryptionService);
//        System.out.println(credentialService.getAllCredentials(userId));
//        System.out.println(fileService.getAllFiles(userId));
        model.addAttribute("files", fileService.getAllFiles(userId));
        return "home";
    }


}
