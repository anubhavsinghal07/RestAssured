package JiraRestAPI;


import java.io.IOException;
import java.net.URISyntaxException;
import javax.xml.parsers.ParserConfigurationException;
import org.json.simple.parser.ParseException;
import util.Utils;



public class JiraRest {

	
	public static void main(String[] args) throws ParserConfigurationException, IOException, ParseException, URISyntaxException {
		 
		
		//Authentic User //POST
		JiraRestMethods.authenticateuser("UAT");
	
		
		// Make user inquiry //GET
		JiraRestMethods.GetUserInquiry("mariosb");
		
		
		//Create an Issue
		String IssueID = JiraRestMethods.CreateAndgetIssueID(Utils.GetRandomText(), "mariosb");
		
		
		// Add comment 
		JiraRestMethods.AddcommentToIssue(IssueID, "Comment: "+Utils.GetRandomText()+"");
		
		
		// Add AddAttachment To Issue
		JiraRestMethods.AddAttachmentToIssue(IssueID);
		
		
		
		
		
		
		
		
		
		
		
	}
	
}
