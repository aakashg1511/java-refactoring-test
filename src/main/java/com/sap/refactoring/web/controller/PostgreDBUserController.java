package com.sap.refactoring.web.controller;

import com.sap.refactoring.exception.UserException;
import com.sap.refactoring.helpers.UserHelper;
import com.sap.refactoring.users.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/postgreusers")
public class PostgreDBUserController {

    @Autowired
    private final UserHelper helper;

    public PostgreDBUserController(UserHelper helper) {
        this.helper = helper;
    }

    @GetMapping("save/")
    public ResponseEntity saveUser(@RequestParam("name") String name,
                                   @RequestParam("email") String email,
                                   @RequestParam("role") List<String> roles){
        UserEntity user = new UserEntity();
        try {
            user.setName(name);user.setEmail(email);user.setRoles(roles);
            helper.saveUser(user);
        } catch (UserException e) {
            return ResponseEntity.ok(e.getMessage());
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("update/")
    public ResponseEntity updateUser(@RequestParam("name") String name,
                                   @RequestParam("email") String email,
                                   @RequestParam("role") List<String> roles){
        UserEntity user = new UserEntity();
        try {
            user.setName(name);user.setEmail(email);user.setRoles(roles);
            helper.updateUser(user);
        } catch (UserException e) {
            return ResponseEntity.ok(e.getMessage());
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("delete/")
    public ResponseEntity deleteUser(@RequestParam("name") String name,
                                   @RequestParam("email") String email,
                                   @RequestParam("role") List<String> roles){
        UserEntity user = new UserEntity();
        try {
            user.setName(name);user.setEmail(email);user.setRoles(roles);
            helper.deleteUser(user);
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
        return ResponseEntity.ok("User has been deleted");
    }

    @GetMapping("getUsers/")
    public ResponseEntity getUsers(){
        List<UserEntity> users = new ArrayList<>();
        try {
            users = helper.getAllUsers();
        } catch (UserException e) {
            return ResponseEntity.ok(e.getMessage());
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("searchUsers/")
    public ResponseEntity searchUsers(@RequestParam("name") String name){
        List<UserEntity> users = new ArrayList<>();
        try {
           users= helper.searchUsers(name);
        } catch (UserException e) {
            return ResponseEntity.ok(e.getMessage());
        }
        return ResponseEntity.ok(users);
    }
}
