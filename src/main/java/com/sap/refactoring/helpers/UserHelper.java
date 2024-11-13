package com.sap.refactoring.helpers;

import com.sap.refactoring.exception.UserException;
import com.sap.refactoring.repository.SapUserRepository;
import com.sap.refactoring.users.User;
import com.sap.refactoring.users.UserDao;
import com.sap.refactoring.users.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserHelper {
    public UserDao userDao;

    private final SapUserRepository sapUserRepository;

    public UserHelper(SapUserRepository sapUserRepository) {
        this.sapUserRepository = sapUserRepository;
        this.userDao = UserDao.getUserDao();
    }

    @Transactional
    public void saveUser(UserEntity user) throws UserException{
        if(!user.getRoles().isEmpty())
            sapUserRepository.save(user);
        else
            throw new UserException("User should have at least one role");
    }

    @Transactional
    public UserEntity updateUser(UserEntity user) throws UserException{
        UserEntity userEntity = sapUserRepository.findbyEmail(user.getName());
        if(userEntity!=null){
            userEntity.setRoles(user.getRoles());
            userEntity.setEmail(user.getEmail());
            return sapUserRepository.save(userEntity);
        }
         else if (user.getRoles().isEmpty())
             throw new UserException("User should have at least one role");
        else
            throw new UserException("User not found");
    }

    @Transactional
    public void deleteUser(UserEntity user) throws UserException{
        sapUserRepository.deleteByEmail(user.getEmail());
    }

    public List<UserEntity> getAllUsers() throws UserException{
        return sapUserRepository.getUsers();
    }

    public List<UserEntity> searchUsers(String name) throws UserException{
        List<UserEntity> users = sapUserRepository.searchUsers(name);
        if(users.isEmpty()) {
            throw new UserException("No User found for the given name : "+name);
        } else {
            return users;
        }
    }

    public User addUser(String name, String email, List<String> roles) throws UserException {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        if(roles.isEmpty())
            throw new UserException("User should have at least one role");
        else
             user.setRoles(roles);
        if (userDao == null) {
            userDao = UserDao.getUserDao();
        }
        userDao.saveUser(user);
        return user;
    }

    public User updateUser(String name, String email, List<String> roles) throws UserException {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        if(roles.isEmpty())
            throw new UserException("User should have at least one role");
        else
            user.setRoles(roles);
        if (userDao == null) {
            userDao = UserDao.getUserDao();
        }
        userDao.updateUser(user);
        return user;
    }

    public User deleteUser(String name, String email, List<String> roles){
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setRoles(roles);
        if (userDao == null) {
            userDao = UserDao.getUserDao();
        }
        userDao.deleteUser(user);
        return user;
    }

    public List<User> getUsers(){
        userDao = UserDao.getUserDao();
        List<User> users = userDao.getUsers();
        if (users == null) {
            users = new ArrayList<>();
        }
        return users;
    }

    public User searchUser(String name){
        if (userDao == null) {
            userDao = UserDao.getUserDao();
        }
        return userDao.findUser(name);
    }
}
