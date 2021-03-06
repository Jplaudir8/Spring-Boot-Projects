package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.modalForms.CredentialModalForm;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
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
@RequestMapping("/credential")
public class CredentialController {

    private Logger logger = LoggerFactory.getLogger(NoteController.class);

    private CredentialService credentialService;

    public CredentialController (CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @PostMapping
    public String createOrUpdateCredential(CredentialModalForm credentialModalForm, Authentication authentication, RedirectAttributes redirectAttributes) {

        String username = authentication.getName();

        if (!credentialModalForm.getCredentialId().equalsIgnoreCase("")) {
            try {
                this.credentialService.updateCredential(credentialModalForm, username);
                redirectAttributes.addFlashAttribute("successMessage", "Your credentials were updated successfully.");
                return "redirect:/result";
            } catch (Exception e) {
                logger.error("Cause: " + e.getCause() + ". Message: " + e.getMessage());
                redirectAttributes.addFlashAttribute("errorMessage", "Something went wrong with the credentials update. Please try again!");
                return "redirect:/result";
            }
        } else {
            try {
                this.credentialService.createCredential(credentialModalForm, username);
                redirectAttributes.addFlashAttribute("successMessage", "Your credentials were successfully created.");
                credentialModalForm.setUrl("");
                credentialModalForm.setUsername("");
                credentialModalForm.setPassword("");
                return "redirect:/result";
            } catch (Exception e) {
                logger.error("Cause: " + e.getCause() + ". Message: " + e.getMessage());
                redirectAttributes.addFlashAttribute("errorMessage", "Something went wrong with the credential creation... Please try again.");
                return "redirect:/result";
            }
        }
    }

    /**
     * Delete Credentials
     * @param credentialId - Credentials row to be deleted with the given id
     */
    @GetMapping("/delete/{credentialId}")
    public String deleteCredential(@PathVariable int credentialId, RedirectAttributes redirectAttributes) {
        try {
            credentialService.deleteCredential(credentialId);
            redirectAttributes.addFlashAttribute("successMessage", "Your credentials were successfully deleted");
            return "redirect:/result";
        } catch (Exception e) {
            logger.error("Cause: " + e.getCause() + ". Message: " + e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Something went wrong with the credentials deletion... Please try again.");
            return "redirect:/result";
        }
    }




}
