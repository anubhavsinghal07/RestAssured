package callpack;


import restAssure.restAssureMaven.ComplexJsonparse;

public class MyCallClass extends ComplexJsonparse {
	

	
	public static void main(String[] args) {
		

		ComplexJsonparse cc =  new ComplexJsonparse();
		
		
		System.out.println(cc.coursesize());
		System.out.println(cc.getpurchaseamt());
		System.out.println(cc.gettitlefirstcorse());
		System.out.println(cc.getalltitlesPrices());
		System.out.println(cc.gettotalcopies());
		System.out.println(cc.verifypurchaseamt());
	}



}
