package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialModalForm;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.service.EncryptionService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/credential")
public class CredentialController {

    private EncryptionService encryptionService;
    private CredentialService credentialService;

    public CredentialController (EncryptionService encryptionService, CredentialService credentialService) {
        this.encryptionService = encryptionService;
        this.credentialService = credentialService;
    }

    @PostMapping
    public String createOrUpdateCredential(CredentialModalForm credentialModalForm, Authentication authentication, Model model) {

        String randomKey = encryptionService.generateKey();
        String encryptedPassword = encryptionService.encryptValue(credentialModalForm.getPassword(), randomKey);

        if (!credentialModalForm.getCredentialId().equalsIgnoreCase("")) {
            credentialService.updateCredential(credentialModalForm);
        } else {

        }


        return "home";
    }

}
