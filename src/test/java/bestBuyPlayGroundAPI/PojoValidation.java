package bestBuyPlayGroundAPI;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.response.Response;;

public class PojoValidation {
	public void setup() {
		baseURI = "http://localhost:3030";//The base URI that's used by REST assured when making requests
	}

	@Test(enabled=false)
	public void sendJsonPayload() throws JsonProcessingException {
		PojoClass data = new PojoClass();
		data.setName("Hamneet");
		data.setType("");
		data.setPrice(15000);
		data.setShipping(1234);
		data.setUpc("");
		data.setDescription("this is post request");
		data.setManufacturer("");
		data.setModel("");
		data.setUrl("");

		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(data);
		System.out.println("jsonData ");
		Response response = (Response) given()
				.contentType("application/json")
		        .body(jsonData)
		        .when().post("/products/addProduct")
		        .then().log().all().statusCode(200);
		
		// Assuming the response is a JSON with an "id" field
				String generatedId = response.jsonPath().getString("id");
				System.out.println("Generated ID: " + generatedId);
}
	}
//addProduct