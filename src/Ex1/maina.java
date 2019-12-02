package Ex1;

import java.util.Arrays;

public class maina {

	public static void main(String[] args) {
		String si="mul(5x^2+3x,4)"; // =>>>>> 5x^2+3x,4
		String s="mul(mul(mul(5x^2+3x,4),5),mul(mul(3x,5),5))"; //=>>>>>mul(mul(5x^2+3x,4),5),mul(mul(3x,5))==>>>f1=mul(mul(5x^2+3x,4),5)
		//if((s.contains("(") || s.contains(")")))  {                                                     ==>>>f2=mul(mul(3x,5))
		System.out.println(Math.PI);
		function f1 = initFromString(si);
		System.out.println(f1);
		System.out.println(f1.f(2));
	}
	public static function initFromString(String s)
	{ 
		try {
		if((s.contains("(") && s.contains(")")))  
		{
			int indexof=s.indexOf('(');
			String oper=s.substring(0,indexof);
			String afterpeel=s.substring(indexof+1, s.length()-1);	
			int openPare=0;
			int closePare=0;
			int indexOfComma=0;

			for (int i = 0; i < afterpeel.length(); i++)
			{
				if(afterpeel.charAt(i)=='(') {
					openPare++;
				}
				else if (afterpeel.charAt(i)==')') {
					closePare++;
				}
				if(openPare==closePare && afterpeel.charAt(i)==',') {  //&&i+1<afterpeel.length()
					indexOfComma=i;
					i=afterpeel.length();	
				}
			}
//			System.out.println(indexOfComma);
//			System.out.println(afterpeel.charAt( indexOfComma));
//			System.out.println(afterpeel);
//			System.out.println(oper);
//	

			function f1=initFromString(afterpeel.substring(0, indexOfComma));
			function f2=initFromString(afterpeel.substring(indexOfComma+1,afterpeel.length()));

			ComplexFunction cf=new ComplexFunction(oper,f1,f2);
		//	System.out.println(f1);
			
			return cf;
	

		}
		
		
		function f= new Polynom(s);
		
		return f;
		} catch (Exception e) {
			throw new RuntimeException("Invalid input");
		}
	}
	

}
