package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialModalForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {

    private CredentialMapper credentialMapper;

    public CredentialService (CredentialMapper credentialMapper) {
        this.credentialMapper = credentialMapper;
    }

    public List<Credential> getAllUserCredentials(int userId) {
        return credentialMapper.getAllUserCredentials(userId);
    }

    public void createCredential(Credential credential) {
        credentialMapper.insertCredential(credential);
    }

    public void updateCredential(CredentialModalForm credentialModalForm) {
        Credential selectedCredential = new Credential(Integer.parseInt(credentialModalForm.getCredentialId()),
                credentialModalForm.getUrl(), credentialModalForm.getUsername(), credentialModalForm.get())
        credentialMapper.updateCredential(credential);
    }

    public void deleteCredential(int noteId) {
        credentialMapper.deleteCredentialById(noteId);
    }

}
