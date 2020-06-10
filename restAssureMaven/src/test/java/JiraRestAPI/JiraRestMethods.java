package JiraRestAPI;


import static io.restassured.RestAssured.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.parser.ParseException;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import util.Utils;
public class JiraRestMethods {

	public static String SessionID = null;
	
	public static String authenticateuser(String Region) throws IOException, ParseException {
		HashMap<String, String> envcreds  = Utils.getenvironmentdetails(Region);
		
			System.out.println(envcreds.get("password"));
			System.out.println(envcreds.get("username"));
			System.out.println(envcreds.get("URI"));
			
			
		RestAssured.get(envcreds.get("URI"));
		RestAssured.useRelaxedHTTPSValidation();
		
		String authresponse =	given().log().all().header("Content-Type", "application/json").body("{\r\n" + 
								"\"username\":\""+envcreds.get("username")+"\",\r\n" + 
								"\"password\":\""+envcreds.get("password")+"\"\r\n" + 
								"}")
							.when().post("/rest/auth/1/session")
							.then().log().all().assertThat().statusCode(200).extract().asString();
		
	JsonPath js = new JsonPath(authresponse);
	SessionID = js.getString("session.value");
	
	return SessionID;
	
	};
	
	
	
	////
	public static void GetUserInquiry(String Username) throws ParseException {
		
		//Auth Check
		given().log().all().header("cookie","JSESSIONID="+ SessionID).header("Content-Type", "application/json").queryParam("username", "anubhavsinghal")
		.when().get("/rest/api/latest/user")
		.then().log().all().assertThat().statusCode(200);
	
	
	
	};
	
	
	
	////
	public static String CreateAndgetIssueID(String summary, String assignee) {
		
	String issueidresp=	given().log().all().header("cookie","JSESSIONID="+ SessionID).header("Content-Type", "application/json").body("{\r\n" + 
				" \"fields\": {\r\n" + 
				"        \"project\": {\r\n" + 
				"            \"key\": \"ICBN\"\r\n" + 
				"        },\r\n" + 
				"        \"summary\": \""+summary+"\",\r\n" + 
				"        \"issuetype\": {\r\n" + 
				"            \"name\": \"Task\"\r\n" + 
				"        },\r\n" + 
				"        \"assignee\": {\r\n" + 
				"            \"name\": \""+assignee+"\"\r\n" + 
				"        },\r\n" + 
				"        \"priority\": {\r\n" + 
				"            \"id\": \"2\"\r\n" + 
				"        }\r\n" + 
				"}\r\n" + 
				"}")
		.when().post("/rest/api/2/issue")
		.then().log().all().assertThat().statusCode(201).extract().asString();
	
JsonPath js = new JsonPath(issueidresp);
	
	return js.getString("id");
	
	}
	
	
	
	////
	public static void AddcommentToIssue(String IssueID, String Comment) {
		
		given().log().all().header("cookie","JSESSIONID="+ SessionID).header("Content-Type", "application/json").pathParam("id", IssueID)
		.body("\r\n" + 
				"{\r\n" + 
				"    \"body\": \""+Comment+"\"\r\n" + 
				"   \r\n" + 
				"}")
			.when().post("/rest/api/2/issue/{id}/comment")
				.then().assertThat().statusCode(201).extract().asString();
	}
	
	
	
	////
	public static void AddAttachmentToIssue(String IssueID) {
		
		// Add Attachment to issue
		
				given()
				.log().all().header("X-Atlassian-Token", "no-check").header("cookie","JSESSIONID="+ SessionID).header("Content-Type", "multipart/form-data")
				.pathParam("id", IssueID)
				.multiPart("file", new File("jira.txt"))
				.when()
				.post("/rest/api/2/issue/{id}/attachments")
				.then()
				.assertThat().statusCode(200);
		}
	
	
}
