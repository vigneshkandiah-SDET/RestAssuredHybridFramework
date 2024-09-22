package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.Routes;
import api.endpoints.UserEndPoints;
import api.payloads.User;

import io.restassured.response.Response;

public class UserTest {
	public Faker faker;
	public User userPayload;

	public Logger logger;

	@BeforeClass
	public void setup() {

		faker = new Faker();
		userPayload = new User();

		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());

		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());

		logger = LogManager.getLogger(this.getClass());
		logger.debug("Debuging.........");
	}

	@Test(priority = 1)
	public void testPostUser() {

		logger.info("Create User Test Started........................ ");
		Response response = UserEndPoints.createUser(userPayload);
		
		System.out.println("Request URL: " + Routes.post_url);
		System.out.println("Response Code: " + response.getStatusCode());
		System.out.println("Response Body: " + response.getBody().asString());

		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		logger.info("User Created Successfully........................ ");
	}

	@Test(priority = 2)
	public void testGetUserByName() {
		
		logger.info("Get User Started........................ ");
		Response response = UserEndPoints.getUser(userPayload.getUsername());

		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		response.then().log().all();
		logger.info("Read User Successfully........................ ");
	}

	@Test(priority = 3)
	public void testUpdateUser() {
		logger.info("Update User Started........................ ");
		userPayload.setEmail(faker.internet().safeEmailAddress());

		Response response = UserEndPoints.updateUser(userPayload.getUsername(), userPayload);

		AssertJUnit.assertEquals(response.getStatusCode(), 200);

		Response getresponse = UserEndPoints.getUser(userPayload.getUsername());

		AssertJUnit.assertEquals(getresponse.jsonPath().getString("email"), userPayload.getEmail());
		response.then().log().all();
		logger.info("Updated User Successfully........................ ");

	}

	@Test(priority = 4)
	public void deleteUserbyName() {
		logger.info("Delete User Started........................ ");
		Response response = UserEndPoints.deleteUser(userPayload.getUsername());

		AssertJUnit.assertEquals(response.getStatusCode(), 200);

		response.then().log().all();
		logger.info("Deleted User Successfully........................ ");
	}

}
