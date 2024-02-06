package bestBuyPlayGroundAPI;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.response.Response;
public class ProductTest {
	@BeforeMethod
public void setup() {
	baseURI = "http://localhost:3030";//The base URI that's used by REST assured when making requests
}
	@Test
public void getAppProducts(ITestContext context) {
//	  given()
//	.when().get("/products")
//	 .then().log().all();//log everything in   response
//	  
      given().queryParam("$limit", "5")
      .when().get("/products")
 	  .then().log().all();//log everything in   response
	  Response response = given().queryParam("$limit", "5")
			  .when().log().all().get("/products");
	  int ProductID = response.body().jsonPath().getInt("data[0].id");
	  System.out.println("ProductID: "+ProductID);
	  context.setAttribute("ProductID",ProductID);
	  
	}
	@Test
	public void getProductId(ITestContext context) {
		 given()
         .pathParam("id", context.getAttribute("ProductID"))
     .when()
         .get("/products/{id}")
     .then()
         .log().all();


		}
}
//given :->for specified precondition header,path ,query,conditions that are necessary for your API call(what to do with request)

//when  :-> defining the action,API request you're making, such as a GET, POST, PUT, etc. 
//This is where you set the HTTP method and the endpoint

//then :->Here you check the response status code, headers, and body to ensure they match your expectations