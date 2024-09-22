package api.test;

import org.testng.annotations.Test;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import api.utilities.DataProviders;
import io.restassured.response.ValidatableResponse;

public class UserDDT {

	@Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void createUserTest(String userId,String usernName, String firstName, String lastName, String email, String password,
			String phone) {

		User payload = new User();

		payload.setId((Integer.parseInt(userId)));
		payload.setUsername(usernName);
		payload.setFirstname(firstName);
		payload.setLastname(lastName);
		payload.setEmail(email);
		payload.setPhone(phone);

		ValidatableResponse response = UserEndPoints.createUser(payload).then().statusCode(200);
		response.log().all();

	}
	@Test(priority = 2,dataProvider = "UserNames",dataProviderClass = DataProviders.class)
	public void deleteUserTest(String userName) {
		
		UserEndPoints.deleteUser(userName).then().statusCode(200);
			
		
		
		
	}

}
