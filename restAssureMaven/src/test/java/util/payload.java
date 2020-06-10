package util;

public class payload {

	
	public static String AddPlace() {
		
		return "{" +
	            "\"location\":{" +
	            "\"lat\" : -38.383494," +
	            "\"lng\" : 33.427362" +
	            "}," +
	            "\"accuracy\":50," +
	            "\"name\":\"Frontline house\"," +
	            "\"phone_number\":\"(+91) 983 893 3937\"," +
	            "\"address\" : \"29, side layout, cohen 09\"," +
	            "\"types\": [\"shoe park\",\"shop\"]," +
	            "\"website\" : \"http://google.com\"," +
	            "\"language\" : \"French-IN\"" +
	            "}";
	}
	
	public static String getnestedjson() {
		
		
		return "{\r\n" + 
				"\"dashboard\":\r\n" + 
				"		{\r\n" + 
				"			\"purchaseamount\": 2000,\r\n" + 
				"			\"website\": \"rahul.com\"\r\n" + 
				"		},\r\n" + 
				"\"courses\": 	[	\r\n" + 
				"		{\r\n" + 
				"			\"tittle\": \"sel-pyt\",\r\n" + 
				"			\"price\": 20,\r\n" + 
				"			\"copies\": 60\r\n" + 
				"		},\r\n" + 
				"		{\r\n" + 
				"			\"tittle\": \"sel-Cypress\",\r\n" + 
				"			\"price\": 10,\r\n" + 
				"			\"copies\": 20\r\n" + 
				"		},\r\n" + 
				"		{\r\n" + 
				"			\"tittle\": \"sel-RPA\",\r\n" + 
				"			\"price\": 30,\r\n" + 
				"			\"copies\": 20\r\n" + 
				"		}\r\n" + 
				"	]\r\n" + 
				"}";
	}
	
	public static String AddBook(String isbn, String aisle) {
		 
		
		return "{\r\n" + 
				"\r\n" + 
				"\"name\":\"Learn Appium Automation with Java\",\r\n" + 
				"\"isbn\":\""+isbn+"\",\r\n" + 
				"\"aisle\":\""+aisle+"\",\r\n" + 
				"\"author\":\"John foe\"\r\n" + 
				"}\r\n" + 
				"";
	};
	
	
	
	
}







