package bestBuyPlayGroundAPI;

import org.testng.Assert;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class SerializartionAndDeseralizationCreateUser {
	@BeforeMethod
	public void setup() {
		baseURI = "https://reqres.in";// The base URI that's used by REST assured when making requests
	}

	@Test(enabled=false)
	public void createObjectCreateUserClass() throws JsonProcessingException {
		// obj1
		CreateUser user = new CreateUser();
		user.setFirstName("Hamneet");
		user.setLeader("Tester");

		// Serialization obj1
		ObjectMapper mapper = new ObjectMapper();
		String userJson = mapper.writeValueAsString(user);
		System.out.println(userJson);
		Response response = given()
		.contentType(ContentType.JSON)
		.body(userJson)
		.when()
		.post("api/users");

		// Assuming the response is a JSON with an "id" field
		String generatedId = response.jsonPath().getString("id");
		System.out.println("Generated ID: " + generatedId);
		System.out.println("Response Body: " + response.getBody().asString());

		// DeSeralization
		CreateUser user1 = mapper.readValue(userJson, CreateUser.class);
		System.out.println(user1.getFirstName());
		System.out.println(user1.getLeader());
	
		// Validate Status code
	 Assert.assertEquals(response.statusCode(), 201, "expecting 201");
	 Assert.assertEquals(user1.getFirstName(), "Hamneet", "expecting 201");
		assertThat(user1.getFirstName(), is(equalTo("Hamneet"))) ;
	}
	/*
	 * String json =
	 * "{ \"person\": { \"name\": \"John\", \"age\": 25, \"address\": { \"city\": \"New York\", \"zip\": \"10001\" } } }"
	 * ; / Extract values using JsonNode 
	 * String name =
	 * rootNode.path("person").path("name").asText(); 
	 * int age =
	 * rootNode.path("person").path("age").asInt();
	 *  String city =
	 * rootNode.path("person").path("address").path("city").asText();
	 *  String zip =
	 * rootNode.path("person").path("address").path("zip").asText();
	 *==========================================================================================
	 * common Hamcrest matchers used in Java testing include: equalTo: Tests for
	 * equality using the equals method. hasItem: Checks if a collection contains an
	 * element. instanceOf: Tests type. not: Tests that the wrapped matcher doesn't
	 * match. nullValue, notNullValue: Tests for null or non-null values.
	 * greaterThan, lessThan, greaterThanOrEqualTo, lessThanOrEqualTo: Tests for
	 * numeric comparisons.
	 */
}
