package com.petstore.tests.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.petstore.ResourceHelper.ResourceHelper;
import com.petstore.java.OrderPOJO;
import com.petstore.java.PetCategoryPOJO;
import com.petstore.java.PetRequestPOJO;
import com.petstore.java.TagPOJO;
import com.petstore.java.UsersPOJO;
import com.petstore.resources.ConfigReader;
import com.petstore.tests.Pet.TestBase;

import io.restassured.response.Response;

public class UserOperations extends TestBase {

	private static UsersPOJO users;
	private static int userId = Integer.parseInt(configReader.getUserId());
	private static String userName = configReader.getUserUsername();
	private static String userPassword = configReader.getUserPassword();
	private static String newUser = configReader.getNewUser();
	private static int newUserID = Integer.parseInt(configReader.getNewUserID());
	private static int invalidUserId = Integer.parseInt(configReader.getInvalidUserId());

	public UserOperations() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		users = new UsersPOJO();
	}

	public String getUserLogin() {

		Response response = resourceHelper.getLoginUser(userName, userPassword);

		Assert.assertEquals(response.getStatusCode(), 200);

		System.out.println("Response Body: " + response.getBody().asString());

		System.out.println("Response headers: " + response.getHeaders());

		response.prettyPrint();

		return userName;
	}

	@Test(priority = 1)
	public void createUser() {

		String username = getUserLogin();

		users = createRandomUserRequest(newUserID);

		Response response = ResourceHelper.getCreateUser(username, userPassword, users);

		Assert.assertEquals(response.getStatusCode(), 200);

		System.out.println("Response Body: " + response.getBody().asString());

		response.prettyPrint();

	}

	private UsersPOJO createRandomUserRequest(int userId) {
		users.setEmail(faker.internet().emailAddress());
		users.setFirstName(faker.name().firstName());
		users.setId(newUserID);
		users.setLastName(faker.name().lastName());
		users.setPassword(userPassword);
		users.setPhone(faker.number().digit());
		users.setUsername(newUser);
		users.setUserStatus(faker.random().nextInt(2));

		return users;
	}

	@Test(priority = 2)
	public Response getUserByUsername(String searchedUser) {

		UsersPOJO username = createRandomUserRequest(userId);
		searchedUser = username.getUsername();

		Response response = resourceHelper.getUserByUsername(searchedUser);

		Assert.assertEquals(response.getStatusCode(), 200);

		System.out.println("Response Body: " + response.getBody().asString());

		System.out.println("Response headers: " + response.getHeaders());

		response.prettyPrint();

		return response;
	}
	
	@Test(priority = 3)
	public Response getUserByUsernameWithInvalidUserId(String searchedUser) {

		UsersPOJO username = createRandomUserRequest(userId);
		searchedUser = username.getUsername();

		Response response = resourceHelper.getUserByUsername(searchedUser);

		Assert.assertEquals(response.getStatusCode(), 200);

		System.out.println("Response Body: " + response.getBody().asString());

		System.out.println("Response headers: " + response.getHeaders());

		response.prettyPrint();

		return response;
	}

	@Test(priority = 4)
	public void updateUserTest() {

		users = createRandomUserRequest(userId);
		users.setLastName(faker.name().lastName());

		UsersPOJO username = createRandomUserRequest(userId);
		String searchedUser = username.getUsername();

		Response response = resourceHelper.getUpdateUser(users, searchedUser);

		Assert.assertEquals(response.getStatusCode(), 200);

		System.out.println("Response Body: " + response.getBody().asString());

		System.out.println("Response headers: " + response.getHeaders());

		response.prettyPrint();
	}

	@Test(priority = 5)
	public void getUsersWithList() {

		List<UsersPOJO> userList = new ArrayList<>();

		for (int i = 1; i <= 3; i++) { // Example: Create 2 users
			users.setEmail(faker.internet().emailAddress());
			users.setFirstName(faker.name().firstName());
			users.setId(userId + i);
			users.setLastName(faker.name().lastName());
			users.setPassword(userPassword + i);
			users.setPhone(faker.number().digit());
			users.setUsername(userName + i);
			users.setUserStatus(faker.random().nextInt(2));

			userList.add(users);
			System.out.println(userList);
		}
		Response response = ResourceHelper.getCreateUsersWithList(userList);
		System.out.println("Response Body: " + response.getBody().asString());
		Assert.assertEquals(response.getStatusCode(), 200);

		response.prettyPrint();
	}

	@Test(priority = 6)
	public void getUpdateUser() {

		UsersPOJO users1 = createRandomUserRequest(userId);
		users1.setLastName(configReader.getUpdateUserLastName());
		users1.setFirstName(configReader.getUpdateUserFirstName());

		String username = getUserLogin();

		Response response = resourceHelper.getUpdateUser(users1, username);
		Assert.assertEquals(response.getStatusCode(), 200);

		System.out.println("Response Body: " + response.asString());
		System.out.println("Response headers: " + response.getHeaders());
		response.prettyPrint();

		System.out.println(getUserByUsername(username).asString());
	}

	@Test(priority = 7)
	public void getDeleteUser() {
		String username = getUserLogin();

		Response response = resourceHelper.getDeleteUser(username, userPassword, newUser);

		Assert.assertEquals(response.getStatusCode(), 200);

		System.out.println("Response Body: " + response.getBody().asString());

		System.out.println("Response headers: " + response.getHeaders());

		response.prettyPrint();
	}

	@Test(priority = 8)
	public void getLogoutUser() {
		String username = getUserLogin();

		Response response = resourceHelper.getLogoutUser(username, userPassword);

		Assert.assertEquals(response.getStatusCode(), 200);

		System.out.println("Response Body: " + response.getBody().asString());

		System.out.println("Response headers: " + response.getHeaders());

		response.prettyPrint();
	}
}
