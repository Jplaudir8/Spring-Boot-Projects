package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteModalForm;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {

    private NoteService noteService;
    private UserService userService;

    public HomeController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @GetMapping("/home")
    public String getHomePage(NoteModalForm noteModalForm, Authentication authentication, Model model) throws Exception {
        String username = authentication.getName();
        int userId = userService.getUser(username).getUserId();
        // Retrieving all user's files
        List<Note> notes = this.noteService.getAllUserNotes(userId);
        // Sending them to templates
        model.addAttribute("notes", notes);

        return "home";
    }

    /*@GetMapping("/result")
    public String getResultPage() {
        return "result";
    }*/

}
