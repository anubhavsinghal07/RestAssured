package util;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;


public class DynamicJson {
	String bookid = "";
	ArrayList<String> idslist = new ArrayList<String>();
	@BeforeTest
	public void relaxhttp() {
		
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = "http://216.10.245.166";
	}
	
	
	//@Test(priority =1, dataProvider="bookdata")
	public void addBook(String isbn, String bookAisleID) {
		
	
		//Add book
		String resp = 	
		 given().header("Content-Type", "application/json").body(payload.AddBook(isbn, bookAisleID))
		.when().post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().asString();
		
		JsonPath js = new JsonPath(resp);
		 bookid = js.getString("ID");
		 

		idslist.add(bookid);
		
	};

	
	
	@DataProvider(name="BookID")
	public Object[][] getDatabookid(){
			
		return new Object[][] { {idslist.get(0), "0"}, {idslist.get(1), "1"}, {idslist.get(2), "2"} };
			
		};
	
	@DataProvider(name="bookdata")
public Object[][] getData(){
		
		return new Object[][] { {"11", "22"}, {"22a", "2b2"}, {"33c", "cc3"}};
		
	};
	
	//@Test(dataProvider="BookID")
//	public void getid() {
//		// In get there is no body, provide Query Param
//		 String sss = given().log().all().header("Content-Type", "application/json").queryParam("ID", "uuh1123")
//				 .when().get("Library/GetBook.php")
//				 .then().log().all().assertThat().statusCode(200).extract().asString();
//		 
//		 	JsonPath js = new JsonPath(sss);
//			String bookname = js.getString("book_name")	;
//			
//			System.out.println(bookname);
//	};
 
	@Test(priority=2)
	public void deleteid() {
		String ID="33ccc3";
	String dd = 	given().log().all().header("Content-Type", "application/json").body("{\r\n" + 
				"\r\n" + 
				"\"ID\" : \""+ID+"\"\r\n" + 
				"\r\n" + 
				"} \r\n" + 
				"")
				.when().post("/Library/DeleteBook.php")
				.then().log().all().assertThat().statusCode(200).extract().asString();
		
				JsonPath js = new JsonPath(dd);
		
					String mdg = js.get("msg");
		
	};
	
	
}
