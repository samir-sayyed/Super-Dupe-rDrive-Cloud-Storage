package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {

    CredentialMapper credentialMapper;
    EncryptionService encryptionService;
    UserMapper userMapper;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService, UserMapper userMapper) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
        this.userMapper = userMapper;
    }

    public int addCredential(Credential credential, Authentication authentication){
//        System.out.println(credential);
        SecureRandom secureRandom = new SecureRandom();

        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
//        System.out.println("service");
//        System.out.println(credential);
        String key = Base64.getEncoder().encodeToString(salt);
//        System.out.println(key + "  key ");
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), key);
//        System.out.println(encryptedPassword + "  password ");

        String decryptPassword = encryptionService.decryptValue(encryptedPassword, key);

        System.out.println("decrypted " + decryptPassword);

        User currentUser = userMapper.getUser(authentication.getName());
        Integer userId = currentUser.getUserId();
        String url = credential.getUrl();
        String userName = credential.getUsername();
        return credentialMapper.addCredential(new Credential(null, url, userName,  key, encryptedPassword, userId));

    }


    public List<Credential> getAllCredentials(Integer credentialId){
        return credentialMapper.getAllCredentials(credentialId);
    }


    public void updateCredential(Credential credential) {
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), credential.getKey());
        credential.setPassword(encryptedPassword);
        credentialMapper.update(credential);
    }


    public void deleteCredential(Integer credentialId) {
        credentialMapper.deleteCredential(credentialId);
    }

    public Credential getCredentialById(Integer id) {
        return credentialMapper.getCredential(id);
    }

}

