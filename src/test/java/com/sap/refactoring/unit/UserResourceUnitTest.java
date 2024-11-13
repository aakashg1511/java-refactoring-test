package com.sap.refactoring.unit;

import com.sap.refactoring.exception.UserException;
import com.sap.refactoring.repository.SapUserRepository;
import com.sap.refactoring.helpers.UserHelper;
import com.sap.refactoring.users.User;
import com.sap.refactoring.users.UserDao;
import com.sap.refactoring.web.controller.UserController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserResourceUnitTest
{
	UserController userController;
	UserDao userDao = UserDao.getUserDao();
	UserHelper helper = new UserHelper(Mockito.mock(SapUserRepository.class));;

	@Test
	public void getUsersTest() throws UserException {
		userController = new UserController(helper);
		User user = new User();
		user.setName("fake user");
		user.setEmail("fake@user.com");
		user.setRoles(Arrays.asList("admin"));
		userDao.saveUser(user);

		ResponseEntity<List<User>> response = userController.getUsers();
		assertThat(response.getStatusCode().value()).isEqualTo(200);
	}

	@Test
	public void addUserTest() throws UserException {
		userController = new UserController(helper);
		//userDao = UserDao.getUserDao();
		ArrayList<String> roles= new ArrayList<>();
		roles.add("fake role");
		ResponseEntity<User> response = userController.addUser("fake name1","fake1@user.com",roles);
		assertThat(response.getStatusCode().value()).isEqualTo(200);
	}

	@Test
	public void updateUserTest() throws UserException {
		userController = new UserController(helper);
		//userDao = UserDao.getUserDao();
		User user = new User();
		user.setName("fake name2");
		user.setEmail("fake2@user.com");
		ArrayList<String> roles= new ArrayList<>();
		roles.add("fake role");
		user.setRoles(roles);
		userDao.saveUser(user);
		ResponseEntity<User> response = userController.updateUser("fake name2","fakeUpdate2@user.com",roles);
		assertThat(response.getStatusCode().value()).isEqualTo(200);
	}

	@Test
	public void deleteTest() throws UserException {
		userController = new UserController(helper);
		//userDao = UserDao.getUserDao();
		User user = new User();
		user.setName("fake name3");
		user.setEmail("fake3@user.com");
		ArrayList<String> roles= new ArrayList<>();
		roles.add("fake role");
		user.setRoles(roles);
		userDao.saveUser(user);
		ResponseEntity<User> response = userController.deleteUser("fake name3","fake3@user.com",roles);
		assertThat(response.getStatusCode().value()).isEqualTo(200);
	}

	@Test
	public void findUserTest() throws UserException {
		userController = new UserController(helper);
		//userDao = UserDao.getUserDao();
		User user = new User();
		user.setName("fake name4");
		user.setEmail("fake4@user.com");
		ArrayList<String> roles= new ArrayList<>();
		roles.add("fake role");
		user.setRoles(roles);
		userDao.saveUser(user);
		ResponseEntity<User> response = userController.findUser("fake name4");
		assertThat(response.getStatusCode().value()).isEqualTo(200);
	}
}
