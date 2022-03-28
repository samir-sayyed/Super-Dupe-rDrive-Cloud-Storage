package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final HashService hashService;
    private final EncryptionService encryptionService;

    public UserService(UserMapper userMapper, HashService hashService, EncryptionService encryptionService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
        this.encryptionService = encryptionService;
    }

    public boolean checkUserNameAvailability(String username){
        return userMapper.getUser(username) == null;
    }

    public int addNewUser(User user) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
//        System.out.println(user.getFirstName());
        String fName = user.getFirstName();
//        System.out.println(fName);
        String lName = user.getLastName();
//        System.out.println(lName);
        String uName = user.getUsername();
//        System.out.println(uName);
//        System.out.println("eeeeeeeeeeeeeee");
        return userMapper.addUser(new User(null, uName, encodedSalt, hashedPassword, fName, lName));
    }

    public User getUser(String username){
        return userMapper.getUser(username);
    }

}
