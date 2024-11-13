package com.sap.refactoring.integration;

import com.sap.refactoring.exception.UserException;
import com.sap.refactoring.repository.SapUserRepository;
import com.sap.refactoring.helpers.UserHelper;
import com.sap.refactoring.users.User;
import com.sap.refactoring.users.UserDao;
import com.sap.refactoring.web.controller.UserController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class UserIntegrationTest
{
	UserDao userDao;
	UserController userController;
	UserHelper userHelper;
	@BeforeEach
	public void setup(){
		userDao = UserDao.getUserDao();
		userHelper = new UserHelper(Mockito.mock(SapUserRepository.class));
		userController = new UserController( userHelper);
	}

	@Test
	public void createUserTest() throws UserException {
		User integration = new User();
		integration.setName("integration");
		integration.setEmail("initial@integration.com");
		integration.setRoles(Arrays.asList("admin", "master"));
		ResponseEntity<User> response = userController.addUser(integration.getName(), integration.getEmail(), integration.getRoles());
		assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
	}

	@Test
	public void updateUserTest() throws UserException {
		createUserTest();
		User updated = new User();
		updated.setName("integration");
		updated.setEmail("updated@integration.com");
		updated.setRoles(Arrays.asList("admin", "master"));
		ResponseEntity response = userController.updateUser(updated.getName(), updated.getEmail(), updated.getRoles());
		assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
	}

	@Test
	public void deleteUserTest() throws UserException {
		createUserTest();
		User updated = new User();
		updated.setName("integration");
		updated.setEmail("updated@integration.com");
		updated.setRoles(Arrays.asList("admin", "master"));
		ResponseEntity response = userController.deleteUser(updated.getName(), updated.getEmail(), updated.getRoles());
		assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
	}

	@Test
	public void getUsersTest() throws UserException {
		createUserTest();
		User updated = new User();
		updated.setName("integration");
		updated.setEmail("updated@integration.com");
		updated.setRoles(Arrays.asList("admin", "master"));
		ResponseEntity response = userController.getUsers();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
	}

	@Test
	public void findUsersTest() throws UserException {
		createUserTest();
		User updated = new User();
		updated.setName("integration");
		updated.setEmail("updated@integration.com");
		updated.setRoles(Arrays.asList("admin", "master"));
		ResponseEntity response = userController.findUser("integration");
		assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
	}
}
