package restAssure.restAssureMaven;
import io.restassured.*;
import io.restassured.path.json.JsonPath;
import util.Utils;
import util.payload;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;



public class basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.useRelaxedHTTPSValidation();
		
		//Rest Assure will understand given,when , then
		//given - all input detail, resource and type 
		//when: submit the API
		//Then: validate the response
		
		
		  
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		
		//// post to add address
	String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json").body(payload.AddPlace())
		.when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.18 (Ubuntu)")
		.extract().asString();
	
	JsonPath js = new JsonPath(response);
	String placeID = js.getString("place_id");
		
	String AddToUpdate = "Mumbai, India";
	
	
	//// put to update the address
	given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json").body("{\r\n" + 
			"\"place_id\": \""+placeID+"\",\r\n" + 
			"\"address\" : \""+AddToUpdate+"\",\r\n" + 
			"\"key\": \"qaclick123\"\r\n" + 
			"\r\n" + 
			"}")
	.when().put("maps/api/place/update/json")
	.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
	
	
	
	
	// get method to verify address update FOR GET method we dont send header and body
String respAdd =	
			given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeID)
			.when().get("/maps/api/place/get/json")
			.then().log().all().extract().asString();

	JsonPath jp = new JsonPath(respAdd);
	String newAdd = 	jp.getString("address");


	Utils.myAssert(AddToUpdate, newAdd);
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	}

}
