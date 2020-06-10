package restAssure.restAssureMaven;

import io.restassured.path.json.JsonPath;
import util.*;

public class ComplexJsonparse{
	
	JsonPath js = new JsonPath(payload.getnestedjson());
	
	
	public int coursesize() {
		
		int count = js.getInt("courses.size()");
		return count;
	};
	
	
	public int getpurchaseamt() {
		
		int count = js.getInt("dashboard.purchaseamount");
		return count;
		
	};
	
	 
	public String gettitlefirstcorse() {
		
	return	js.get("courses[0].tittle");
		
	};
	
	public String getalltitlesPrices() {
		
		String tittle = "";
		int price = 0;
		String list = "";
		for(int i=0;i<coursesize();i++)
		{
			tittle = js.get("courses["+i+"].tittle");
			price = js.get("courses["+i+"].price");
			
			list +=  i + ": " + tittle + " " + price + "\n";
		}
				return list;
	};
	
	
public int gettotalcopies() {
		
		int copies = 0;
		int totcopies = 0;
		for(int i=0;i<coursesize();i++)
		{
			copies = js.get("courses["+i+"].copies");
			
			totcopies +=  copies ;
		}
				return totcopies;
	};
	
	
public Boolean verifypurchaseamt() {
		
		int copies = 0;
		int totcopiesamt = 0;
		int prices = 0;
		
		for(int i=0;i<coursesize();i++)
		{
			copies = js.get("courses["+i+"].copies");
			prices = js.get("courses["+i+"].price");
			
			totcopiesamt +=  copies*prices ;
		}
		Utils.myAssert(totcopiesamt, getpurchaseamt());
		
		return (totcopiesamt==getpurchaseamt()) ? true : false;
		
	};
	
	
}
