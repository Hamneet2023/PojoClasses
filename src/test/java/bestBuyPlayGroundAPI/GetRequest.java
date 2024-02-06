package bestBuyPlayGroundAPI;


import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

public class GetRequest {
@Test
public void  getRequest01() {
	//if you do no t want to use the resrtassured back to back then write static in the front of restassured 
	//Response response =RestAssured.get("https://reqres.in/api/users?page=2");
	Response response = get("https://reqres.in/api/users?page=2");
	System.out.println("response body:  "+ response.getBody());
	System.out.println("response content: "+response.getContentType());
	System.out.println("response statuscode: "+response.getStatusCode());
	System.out.println(" response gettime:" +response.getTime());
	System.out.println("get header:" + response.getHeader("Content-Type"));//pass the key
	
	//validate the status code
	//compare actual and expected results
	Assert.assertEquals(200,response.getStatusCode());
	}

@Test
public void getRequest02() {
	//RestAssured.baseURI="https://reqres.in/api/users?page=2";
    given().
	queryParam("page", 2).
	when().get("https://reqres.in/api/users?page=2")
	.then()
	.log().all().statusCode(200);// log all helps to  log the responses
}

public void gerRequest03() {
	
}
}
