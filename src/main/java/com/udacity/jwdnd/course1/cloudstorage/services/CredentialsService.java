package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialsService {

    private CredentialsMapper credentialsMapper;
    private EncryptionService encryptionService;


    // Constructor
    public CredentialsService(EncryptionService encryptionService,
                              CredentialsMapper credentialsMapper) {
        this.encryptionService = encryptionService;
        this.credentialsMapper = credentialsMapper;
    }

    public List<Credentials> getAllUserCredentials(Integer userId) {
        return credentialsMapper.getAllCredentials(userId);
    }

    public Integer addCredential(Integer credentialId, String url,
                             String userName, String password,
                             Integer userId) {
        Integer credentialIdReturn;

        // Create a key and encrypt the password
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];

        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(password, encodedKey);

        if (credentialId == null) {
            // Insert if current credentialId is empty
            Credentials credentials = new Credentials(0, url, userName, encodedKey, encryptedPassword, userId);
            credentialIdReturn = credentialsMapper.saveCredential(credentials);
        } else {
            // Update if current credentialId has value
            Credentials credentials = new Credentials(credentialId, url, userName, encodedKey, encryptedPassword, userId);
            credentialIdReturn = credentialsMapper.updateCredential(credentials);
        }
        return credentialIdReturn;
    }

    public void deleteCredential(Integer credentialsId) {
        credentialsMapper.deleteCredential(credentialsId);
    }

    public String decryptPassword(String password, String key) {
        return encryptionService.decryptValue(password, key);
    }

}