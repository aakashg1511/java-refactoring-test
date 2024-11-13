package com.sap.refactoring.users;

import com.sap.refactoring.exception.UserException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UserDao
{

	volatile public ArrayList<User> users;

	volatile public static UserDao userDao;

	public static UserDao getUserDao() {
		if (userDao == null) {
			userDao = new UserDao();
		}
		return userDao;
	}

	private List<String> getAllExistingEmails() {
		return users.stream().map(u -> u.getEmail()).toList();
	}

	@Transactional
	public void saveUser(User user) throws UserException {
		if (users == null) {
			users = new ArrayList<User>();
		}
		checkIfEmailExist(user);
        users.add(user);
	}

	public ArrayList<User> getUsers() {
		try {
			return users;
		} catch (Throwable e) {
			log.error("No user found");
			return null;
		}
	}

	public void deleteUser(User userToDelete) {
		try {
			for (User user : new ArrayList<>(users)) {
				if (user.getEmail().equals(userToDelete.getEmail())) {
					users.remove(user);
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	public void updateUser(User userToUpdate) throws UserException {
		try {
				for (User user : new ArrayList<>(users)) {
					if (user.getName().equals(userToUpdate.getName())) {
						ifUpdatingEmails(user, userToUpdate);
						user.setEmail(userToUpdate.getEmail());
						user.setRoles(userToUpdate.getRoles());
					}
				}
		} catch (RuntimeException e) {
			log.error(e.getMessage());
		}
	}

	private void ifUpdatingEmails(User existingUser, User updatedUser) throws UserException {
		if(!existingUser.getEmail().equals(updatedUser.getEmail())) {
			checkIfEmailExist(updatedUser);
		}
	}

	private void checkIfEmailExist(User updatedUser) throws UserException {
		if(getAllExistingEmails().contains(updatedUser.getEmail())) {
			throw new UserException("This email already registered.Please enter unique email");
		}
	}

	public User findUser(String name) {
		try {
			for (User user : users) {
				if (user.getName().equals(name)) {
					return user;
				}
			}
		} catch (NullPointerException e) {
			log.error(e.getMessage());
		}
		return null;
	}}
