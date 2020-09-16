package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteModalForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/note")
public class NoteController {

    private Logger logger = LoggerFactory.getLogger(NoteController.class);

    private NoteService noteService;

    public NoteController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
    }

    /**
     * Create or Update a new note
     * @param noteModalForm - Note to create or update
     * @param authentication - Authenticated user
     */
    @PostMapping
    public String createOrUpdateNote(NoteModalForm noteModalForm, Authentication authentication, RedirectAttributes redirectAttributes) {
        String username = authentication.getName();

        if (!noteModalForm.getNoteId().equalsIgnoreCase("")) {
            try {
                this.noteService.updateNote(noteModalForm, username);
                redirectAttributes.addFlashAttribute("successMessage", "Your note was updated successfully.");
                return "redirect:/result";
            } catch (Exception e) {
                logger.error("Cause: " + e.getCause() + ". Message: " + e.getMessage());
                redirectAttributes.addFlashAttribute("errorMessage", "Something went wrong with the note update. Please try again!");
                return "redirect:/result";
            }
        } else {
            try {
                this.noteService.createNote(noteModalForm, username);
                redirectAttributes.addFlashAttribute("successMessage", "Your note was successfully created.");
                noteModalForm.setNoteTitle("");
                noteModalForm.setNoteDescription("");
                return "redirect:/result";
            } catch (Exception e) {
                logger.error("Cause: " + e.getCause() + ". Message: " + e.getMessage());
                redirectAttributes.addFlashAttribute("errorMessage", "Something went wrong with the note creation... Please try again.");
                return "redirect:/result";
            }
        }

    }

    /**
     * Delete note
     * @param noteId - Note to be deleted with the given id
     */
    @GetMapping("/delete/{noteId}")
    public String deleteNote(@PathVariable int noteId, RedirectAttributes redirectAttributes) {
        try {
            noteService.deleteNote(noteId);
            redirectAttributes.addFlashAttribute("successMessage", "Your note was successfully deleted");
            return "redirect:/result";
        } catch (Exception e) {
            logger.error("Cause: " + e.getCause() + ". Message: " + e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Something went wrong with the note deletion... Please try again.");
            return "redirect:/result";
        }
    }

}