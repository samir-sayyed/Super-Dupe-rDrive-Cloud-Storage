package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;

@Controller
@RequestMapping("/note")
public class NoteController {

    NoteService noteService;
    UserService userService;

    public NoteController(UserService userService, NoteService noteService) {
        this.userService = userService;
        this.noteService = noteService;
    }


    @PostMapping("/addNote")
    public String addNote(@ModelAttribute Note note, Model m, Authentication authentication){

//        User currentUser = userService.getUser(authentication)
//        User user = userService.getUser(authentication.getName());
//        Integer userId = user.getUserId();
//        note.setUserId(userId);

//        System.out.println(authentication);

        String error = null;
        String success = null;
//        if note already present then we will get its id not null, so we will update this note else we will create new one
        if (note.getNoteId() != null){
            try {
                noteService.updateNote(note);
                success = "Note updated successfully";
            }catch (Exception e){
                error = "Opps! something went wrong during updation ";
            }

        }else {
            try {
                int rowAdded = noteService.addNewNote(note, authentication);
                if (rowAdded < 0){
                    error = "Error in adding note";
//                System.out.println("Error in adding note");
                }else {
                    success = "Note added successfully!";
                }

            }catch (Exception e){
                error = "Error in adding note";
                System.out.println(e.getMessage());
            }


        }
//        System.out.println(note);
        if (error == null){
            m.addAttribute("success", success);
        }else {
            m.addAttribute("error", error);
        }
        return "result";
    }

    @GetMapping("/delete/{noteId}")
    public String deleteNote(@PathVariable Integer noteId, Model model){
        try {
            noteService.deleteNote(noteId);
            model.addAttribute("success", "Note deleted !");
        }catch (Exception e){
            model.addAttribute("error", "Error in deleting note!");
        }

        return "result";
    }

//    @GetMapping("/note")
//    public String getAllNotes(Model m){
//
//        List<Note> allNotes = noteService.getAllNotes();
//
//        m.addAttribute("notes", "allNotes");
//
//        return "/home";
//    }


}
