package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    NoteMapper noteMapper;
    UserMapper userMapper;

    public NoteService(NoteMapper noteMapper, UserMapper userMapper) {
        this.noteMapper = noteMapper;
        this.userMapper = userMapper;
    }

    public int addNewNote(Note note, Authentication authentication){
        User currentUser = userMapper.getUser(authentication.getName());
        Integer userId = currentUser.getUserId();
        String noteTitle = note.getNoteTitle();
        String noteDescription = note.getNoteDescription();
        return noteMapper.addNote(new Note(null, noteTitle, noteDescription, userId));
    }

    public List<Note> getAllNotes(Integer userId){
        return noteMapper.getAllNotes(userId);
    }

    public void updateNote(Note note){
        noteMapper.update(note);
    }

    public void deleteNote(Integer noteId){
        noteMapper.deleteNote(noteId);
    }
}
//        return userMapper.addUser(new User(null, uName, encodedSalt, hashedPassword, fName, lName));
