package Ex1;

import java.util.Iterator;

public class PolynomTest1 {
	public static void main(String[] args) {

	}

	public static void test1() {
		System.out.println("Test 1");

		Polynom p1 = new Polynom();
		String[] monoms = {"1","x","2*x^2", "0.5x^2"};

		for(int i=0;i<monoms.length;i++) {

			Monom m = new Monom(monoms[i]);
			p1.add(m);
			double aa = p1.area(0, 1, 0.0001);
			p1.substract(p1);
			System.out.println(p1);
		}

	}

	public static void test2() {
		System.out.println("Test 2");
		Polynom p1 = new Polynom(), p2 =  new Polynom();
		String[] monoms1 = {"2", "-x","-3.2x^2","4","-1.5x^2"};
		String[] monoms2 = {"5", "1.7x","3.2x^2","-3","-1.5x^2"};
		for(int i=0;i<monoms1.length;i++) {
			Monom m = new Monom(monoms1[i]);
			p1.add(m);
		}
		for(int i=0;i<monoms2.length;i++) {
			Monom m = new Monom(monoms2[i]);
			p2.add(m);
		}
		System.out.println("p1: "+p1);
		System.out.println("p2: "+p2);
		p1.add(p2);
		System.out.println("p1+p2: "+p1);
		p1.multiply(p2);
		System.out.println("(p1+p2)*p2: "+p1);
		String s1 = p1.toString();
	}
	public static void test3()
	{
		System.out.println("Test 3");
		System.out.println("\n");
		String [] polynomarr = {"5x", "-xx","-3.2x^2x","4x^3","-1.2x^2"};
		;
		for(int i=0;i<polynomarr.length;i++) 
		{
			System.out.println("Polynom "+i+")");
			try 
			{
				Polynom p1  = new Polynom(polynomarr[i]);
				if (p1.isZero()) {
					System.out.println(i + ".) " + p1 + "\n");
					continue;
				}
				System.out.println("The area of Polynom"+p1+"at point [0,2] is : "+"\n"+p1.area(0, 2, Monom.EPSILON));	
				System.out.println("The Root of Polynom"+p1+"at point [0,2] is : "+"\n"+p1.root(0, 2, Monom.EPSILON));
				Polynom_able poli=p1.copy();
				System.out.println("Polynom poli is a copy of p1="+poli);
				p1.add(poli);
				System.out.println("p1  after being added is = "+p1);
				poli.multiply(new Monom("2.0000000001"));
				System.out.println("Check if poli is equal to p1 after we multiplied poli with 2 the answer is :"+poli.equals(p1));
				System.out.println(poli);
				System.out.println("Derivative="+poli.derivative());
				System.out.println("\n"+"\n");				

			}
			catch( ArithmeticException e)
			{
				System.out.println(polynomarr[i] + " is not a valid polynom string");
				System.out.println("\n"+"\n");
			}
		}
	}
}

