package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.modalForms.CredentialModalForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {

    private CredentialMapper credentialMapper;
    private UserMapper userMapper;

    private EncryptionService encryptionService;

    public CredentialService (CredentialMapper credentialMapper, UserMapper userMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.userMapper = userMapper;
        this.encryptionService = encryptionService;
    }

    public List<Credential> getAllUserCredentials(int userId) {
        return credentialMapper.getAllUserCredentials(userId);
    }

    public void createCredential(CredentialModalForm credentialModalForm, String username) {

        String randomKey = encryptionService.generateKey(); // Generating encryption Key
        String encryptedPassword = encryptionService.encryptValue(credentialModalForm.getPassword(), randomKey); // Encrypting password.
        // Preparing credential POJO
        Credential newCredential = new Credential(
                null,
                credentialModalForm.getUrl(),
                credentialModalForm.getUsername(),
                randomKey,
                encryptedPassword,
                userMapper.getUserIdByUsername(username)
        );

        credentialMapper.insertCredential(newCredential);
    }

    public void updateCredential(CredentialModalForm credentialModalForm, String username) {

        String randomKey = encryptionService.generateKey(); // Generating new encryption Key
        String encryptedPassword = encryptionService.encryptValue(credentialModalForm.getPassword(), randomKey); // Encrypting new password.
        // Preparing credential POJO
        Credential selectedCredential = new Credential(
                Integer.parseInt(credentialModalForm.getCredentialId()),
                credentialModalForm.getUrl(),
                credentialModalForm.getUsername(),
                randomKey,
                encryptedPassword,
                userMapper.getUserIdByUsername(username)
        );

        credentialMapper.updateCredential(selectedCredential);
    }

    public void deleteCredential(int credentialId) {
        credentialMapper.deleteCredentialById(credentialId);
    }

}
