package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.NoteModalForm;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/note")
public class NoteController {

    NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    /*@GetMapping
    public String getHomePage(NoteModalForm noteModalForm, Model model) {
        model.addAttribute("notes", this.noteService.getAllNotes());
        return "home";
    }*/

    @PostMapping
    public String postNewNote(Authentication authentication, NoteModalForm noteModalForm, Model model) {
        System.out.println("Captured: " + noteModalForm.getNoteTitle() + noteModalForm.getNoteDescription());
        String username = authentication.getName();
        this.noteService.addNote(noteModalForm, username);
        noteModalForm.setNoteTitle("");
        noteModalForm.setNoteDescription("");
        model.addAttribute("notes", this.noteService.getAllNotes());
        return "home";
    }

}