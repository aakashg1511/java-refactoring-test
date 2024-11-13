package com.sap.refactoring.web.controller;

import java.util.List;

import com.sap.refactoring.exception.UserException;
import com.sap.refactoring.helpers.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sap.refactoring.users.User;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController
{
	//public UserDao userDao;
	@Autowired
	private final UserHelper helper;

	public UserController(UserHelper helper) {
		this.helper = helper;
	}

	@GetMapping("add/")
	public ResponseEntity addUser(@RequestParam("name") String name,
	                              @RequestParam("email") String email,
	                              @RequestParam("role") List<String> roles){
        User user = null;
        try {
            user = helper.addUser(name,email,roles);
        } catch (UserException e) {
            return ResponseEntity.ok(e.getMessage());
        }
        return ResponseEntity.ok(user);
	}

	@GetMapping("update/")
	public ResponseEntity updateUser(@RequestParam("name") String name,
	                           @RequestParam("email") String email,
	                           @RequestParam("role") List<String> roles)  {
        User user = null;
        try {
            user = helper.updateUser(name,email,roles);
        } catch (UserException e) {
			return ResponseEntity.ok(e.getMessage());
        }
        return ResponseEntity.ok(user);
	}

	@GetMapping("delete/")
	public ResponseEntity deleteUser(@RequestParam("name") String name,
	                           @RequestParam("email") String email,
	                           @RequestParam("role") List<String> roles) {
		User user = helper.deleteUser(name,email,roles);
		return ResponseEntity.ok(user);
	}

	@GetMapping("find/")
	public ResponseEntity getUsers() {
		List<User> users = helper.getUsers();
		return ResponseEntity.status(200).body(users);
	}

	@GetMapping("search/")
	public ResponseEntity findUser(@RequestParam("name") String name) {
		User user = helper.searchUser(name);
		return ResponseEntity.ok(user);
	}
}
