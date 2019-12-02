package Ex1;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * This class represents a simple (naive) tester for the Monom class, 
 * Note: <br>
 * (i) The class is NOT a JUNIT - (i.e., educational reasons) - should be changed to a proper JUnit in Ex1. <br>
 * (ii) This tester should be extend in order to test ALL the methods and functionality of the Monom class.  <br>
 * (iii) Expected output:  <br>
 * *****  Test1:  *****  <br>
0) 2.0    	isZero: false	 f(0) = 2.0  <br>
1) -1.0x    	isZero: false	 f(1) = -1.0  <br>
2) -3.2x^2    	isZero: false	 f(2) = -12.8  <br>
3) 0    	isZero: true	 f(3) = 0.0  <br>
 *****  Test2:  *****  <br>
0) 0    	isZero: true  	eq: true  <br>
1) -1.0    	isZero: false  	eq: true  <br>
2) -1.3x    	isZero: false  	eq: true  <br>
3) -2.2x^2    	isZero: false  	eq: true  <br>
 */
public class MonomTest1 {
	public static void main(String [] args) {
		test3();
	}

	private static void test1() {
		System.out.println("*****  Test1:  *****");
		String[] monoms = {"2", "-x","-3.2x^2","0"};
		for(int i=0;i<monoms.length;i++) {
			Monom m = new Monom(monoms[i]);
			String s = m.toString();
			m = new Monom(s);
			double fx = m.f(i);
			System.out.println(i+") "+m +"    \tisZero: "+m.isZero()+"\t f("+i+") = "+fx);
		}
	}
	private static void test2() {
		System.out.println("*****  Test2:  *****");
		ArrayList<Monom> monoms = new ArrayList<Monom>();
		monoms.add(new Monom(0,5));
		monoms.add(new Monom(-1,0));
		monoms.add(new Monom(-1.3,1));
		monoms.add(new Monom(-2.2,2));

		for(int i=0;i<monoms.size();i++) {
			Monom m = new Monom(monoms.get(i));
			String s = m.toString();
			Monom m1 = new Monom(s);
			boolean e = m.equals(m1);
			System.out.println(i+") "+m +"    \tisZero: "+m.isZero()+"  \teq: "+e);
		}
	}


	private static void test3() {
		System.out.println("*****  Test3:  *****");
		String [] monom= {"0","2x","4x^6","3","5x^x"," ","0.00000001","-3x^2"};

		for (int i = 0; i < monom.length; i++) {
			System.out.println(i+")  "+monom[i]);
			try {
				Monom m=new Monom(monom[i]);
				if(m.isZero()) {
					System.out.println();
					System.out.println("Monom : "+monom[i]+" is zero.");
					System.out.println("\n");
					 continue;
				}
				System.out.println();
				System.out.println("Monom is : "+m);
				m.add(new Monom("3"));
				System.out.println("Monom m after being added with 3 is : "+m);
				m.subtract(new Monom("x"));
				System.out.println("Monom m after being substracted with x is : "+m);
				m.multipy(new Monom("3.2x^3"));
				System.out.println("Monom m after being multiplied with 3.2x^3 is : "+m);
				System.out.println("f(x)="+m.f(3.5));
				System.out.println("\n");
			}
			catch(ArithmeticException e) {
				System.out.println("Monom "+monom[i]+" is not valid");
				System.out.println("\n");
			}
		}






	}
}
