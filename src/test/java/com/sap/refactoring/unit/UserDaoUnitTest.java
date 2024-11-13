package com.sap.refactoring.unit;

import com.sap.refactoring.exception.UserException;
import com.sap.refactoring.users.User;
import com.sap.refactoring.users.UserDao;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class UserDaoUnitTest
{
	UserDao userDao;

	@Test
	public void saveUserTest() throws UserException {
		userDao = UserDao.getUserDao();
		User user = new User();
		user.setName("Fake Name");
		user.setEmail("fake@email.com");
		user.setRoles(Arrays.asList("admin", "master"));
		userDao.saveUser(user);
	}

	@Test
	public void deleteUserTest() throws UserException {
		userDao = UserDao.getUserDao();
		User user = new User();
		user.setName("Fake Name2");
		user.setEmail("fake2@email.com");
		user.setRoles(Arrays.asList("admin", "master"));
		userDao.saveUser(user);
		try {
			userDao.deleteUser(user);
		} catch (NullPointerException e) {
		}
	}

	@Test
	public void updateUserTest() throws UserException {
		userDao = UserDao.getUserDao();
		User user = new User();
		user.setName("Fake Name3");
		user.setEmail("fake3@email.com");
		user.setRoles(Arrays.asList("admin", "master"));
		userDao.saveUser(user);
		user.setName("Fake Name3");
		user.setEmail("fakeUpdate3@email.com");
		user.setRoles(Arrays.asList("admin", "master"));
		userDao.updateUser(user);;
	}

	@Test
	public void findUserTest() throws UserException {
		userDao = UserDao.getUserDao();
		User user = new User();
		user.setName("Fake Name4");
		user.setEmail("fakeUpdate4@email.com");
		user.setRoles(Arrays.asList("admin", "master"));
		userDao.saveUser(user);
		userDao.findUser("Fake Name4");
	}

	@Test
	public void getUserTest() throws UserException {
		userDao = UserDao.getUserDao();
		User user = new User();
		user.setName("Fake Name5");
		user.setEmail("fakeUpdate5@email.com");
		user.setRoles(Arrays.asList("admin", "master"));
		userDao.saveUser(user);
		userDao.getUsers();
	}

}
