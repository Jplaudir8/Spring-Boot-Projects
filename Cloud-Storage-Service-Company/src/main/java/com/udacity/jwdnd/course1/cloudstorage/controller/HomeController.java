package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.modalForms.CredentialModalForm;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.modalForms.NoteModalForm;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.service.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private NoteService noteService;
    private UserService userService;
    private CredentialService credentialService;
    private EncryptionService encryptionService;

    public HomeController(NoteService noteService, UserService userService, CredentialService credentialService, EncryptionService encryptionService) {
        this.noteService = noteService;
        this.userService = userService;
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
    }

    @GetMapping("/home")
    public String getHomePage(CredentialModalForm credentialModalForm, NoteModalForm noteModalForm, Authentication authentication, Model model) throws Exception {
        String username = authentication.getName();
        int userId = userService.getUser(username).getUserId();
        // Retrieving all user's files
        List<Note> notes = this.noteService.getAllUserNotes(userId);
        List<Credential> credentials = this.credentialService.getAllUserCredentials(userId);
        // Sending them to templates
        model.addAttribute("notes", notes);
        model.addAttribute("credentials", credentials);
        model.addAttribute("encryptionService", encryptionService);

        return "home";
    }

    /*@GetMapping("/result")
    public String getResultPage() {
        return "result";
    }*/

}
