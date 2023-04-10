package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UsersMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Users;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UsersService {

    private final UsersMapper usersMapper;
    private final HashService hashService;

    // Constructor
    public UsersService(UsersMapper usersMapper, HashService hashService) {
        this.usersMapper = usersMapper;
        this.hashService = hashService;
    }

    public Boolean isUsernameAvailable(String userName) {
        return usersMapper.selectUser(userName) == null;
    }

    public Integer createUser(Users users) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(users.getPassword(), encodedSalt);
        return usersMapper.insertUser(new Users(null, users.getUserName(),
                                        encodedSalt, hashedPassword,
                                        users.getFirstName(), users.getLastName()));
    }

    public Users getUser(String userName) {
        return usersMapper.selectUser(userName);
    }

    public Integer getLoggedInUserId(Authentication authentication) {
        return getUser(authentication.getName()).getUserId();
    }
}