package api.endpoints;

import api.payloads.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints {

	public static Response createUser(User payload) {

		Response response = RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).when()
				.post(Routes.post_url);

		return response;

	}

	public static Response getUser(String userName) {

		Response response = RestAssured.given().pathParam("username", userName).when().get(Routes.get_url);

		return response;

	}

	public static Response updateUser(String userName, User payload) {

		Response response = RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.pathParam("username", userName).body(payload).when().put(Routes.update_url);

		return response;

	}

	public static Response deleteUser(String userName) {

		Response response = RestAssured.given().pathParam("username", userName).when().delete(Routes.delete_url);

		return response;

	}

}
