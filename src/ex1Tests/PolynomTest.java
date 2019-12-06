package ex1Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Ex1.*;

class PolynomTest {

	@Test
	void testPolynomString() {
		String [] monoms= {"1.0x^2","4-5x^3","2x^3","-4x^4","5x^3"};

		Polynom p=new Polynom();
		int result=0;

		for (int i = 0; i < monoms.length; i++) 
		{
			try {
				Monom m=new Monom(monoms[i]);
				p.add(m);
				result++;
				
			}
			catch (RuntimeException error) {
				System.out.println(error.getMessage() +"  "+ monoms[i]);
			}
		}

		assertEquals(4,result);

	}

	@Test
	void testF() {
		String a="4x";
		String b="2x^2+3x-4";
		String c="-4x^4-3x^2+5x-3";

		Polynom p1=new Polynom(a);
		Polynom p2=new Polynom(b);
		Polynom p3=new Polynom(c);

		assertEquals(16.8,p1.f(4.2));
		assertEquals(5, p2.f(-3));
		assertEquals(-69, p3.f(2));
	}

	@Test
	void testAddPolynom_able() {
		String a="3x^4-5x^3+2";
		String b="-2x^2-2";
		String c="7x^2+4-3x";

		Polynom_able pa1=new Polynom(b);
		Polynom p1=new Polynom(a);
		p1.add(pa1);

		Polynom_able pa2=new Polynom(c);
		Polynom p2=new Polynom(a);
		p2.add(pa2);	


		assertEquals("3.0x^4+-5.0x^3+-2.0x^2+0.0x^0", p1.toString());
		assertEquals("3.0x^4+-5.0x^3+7.0x^2+-3.0x^1+6.0x^0", p2.toString());

	}

	@Test
	void testAddMonom() {
		String a="2x^2+5x-7";
		String b="6-9x+10x^3";
		String c="-3x^2";
		String d="-11x^3";

		Polynom p1=new Polynom(a);
		Polynom p2=new Polynom(b);

		Monom m=new Monom(3.5,2);
		Monom m1=new Monom(c);
		Monom m2=new Monom(d);

		p1.add(m1);
		p2.add(m2);

		assertEquals("-1.0x^2+5.0x^1+-7.0x^0", p1.toString());
		assertEquals("-1.0x^3+-9.0x^1+6.0x^0", p2.toString());

		p1.add(m);
		assertEquals("2.5x^2+5.0x^1+-7.0x^0", p1.toString());


	}

	@Test
	void testSubstract() {
		String a="3x^4-5x^3+2";
		String b="-2x^2-2";
		String c="7x^2+4-3x";

		Polynom_able pa1=new Polynom(b);
		Polynom p1=new Polynom(a);
		p1.substract(pa1);

		Polynom_able pa2=new Polynom(c);
		Polynom p2=new Polynom(a);
		p2.substract(pa2);

		assertEquals("3.0x^4+-5.0x^3+2.0x^2+4.0x^0", p1.toString());
		assertEquals("3.0x^4+-5.0x^3+-7.0x^2+3.0x^1+-2.0x^0", p2.toString());

	}

	@Test
	void testMultiplyPolynom_able() {
		String a="3x^4-5x^3";
		String b="-2x^2+8x-2";
		String c="7x^2-1";

		Polynom_able pa1=new Polynom(b);
		Polynom p1=new Polynom(a);
		p1.multiply(pa1);

		Polynom_able pa2=new Polynom(c);
		Polynom p2=new Polynom(a);
		p2.multiply(pa2);

		assertEquals("-6.0x^6+34.0x^5+-46.0x^4+10.0x^3", p1.toString());
		assertEquals("21.0x^6+-35.0x^5+-3.0x^4+5.0x^3", p2.toString());
	}

	@Test
	void testEqualsObject() {
		String a="3x^4-5x^3";
		String b="2x^4-5x^3+x^4+0x^2";
		String c="3x^4-5.0000000001x^3";
		String d="3x^4-4x^3";

		Polynom p1=new Polynom(a);
		Polynom_able pa1=new Polynom(b);
		Polynom_able pa2=new Polynom(c);
		Polynom_able pa3=new Polynom(d);


		assertEquals(true, p1.equals(pa1));
		assertEquals(true,  p1.equals(pa2));
		assertEquals(false,  p1.equals(pa3));

	}

	@Test
	void testIsZero() {
		String a="3x^4-5x^3";
		String b="0";
		String c="7x^2-1";

		Polynom_able pa1=new Polynom(b);
		Polynom p1=new Polynom(a);
		p1.multiply(pa1);

		Polynom_able pa2=new Polynom(a);
		Polynom p2=new Polynom(a);
		p2.substract(pa2);
		Polynom p3=new Polynom(c);

		boolean flag1 = p1.isZero();
		boolean flag2 = p2.isZero();
		boolean flag3 = p3.isZero();

		assertEquals(true, flag1);
		assertEquals(true, flag2);
		assertEquals(false, flag3);
	}

	@Test
	void testRoot() {
		String a="3x^2-5x";
		String b="-2x^3+8x-2";
		String c="7x^2-1";


		Polynom p1=new Polynom(a);
		double r1=p1.root(1,3,Monom.EPSILON);

		Polynom p2=new Polynom(b);
		double r2=p2.root(-5,0,Monom.EPSILON);

		System.out.println(r1);
		System.out.println(r2);
		assertEquals(1.6666666865348816, r1);
		assertEquals(-2.1149075776338577, r2);
	}

	@Test
	void testCopy() {


		Polynom_able copy=new Polynom("2x^2-5+3");
		Polynom testcopy=new Polynom();
		testcopy=(Polynom)copy.copy();
		copy.substract(copy);

		Polynom_able copy2=new Polynom("-4x^3+5x-5+6");
		Polynom testcopy2=new Polynom();
		testcopy2=(Polynom)copy2.copy();
		Monom m=new Monom(3.0,4);
		copy2.add(m);

		Polynom_able copy3=new Polynom("-4x+1.2x^3-9");
		Polynom testcopy3=new Polynom();
		testcopy3=(Polynom)copy3.copy();



		assertEquals(false, copy.equals(testcopy));
		assertEquals(false, copy2.equals(testcopy2));
		assertEquals(true, copy3.equals(testcopy3));
	}

	@Test
	void testDerivative() {
		String a="3x^2-5x";
		String b="-2x^3+8x-2";
		String c="7x^2-1";

		Polynom p1=new Polynom(a);
		Polynom p2=new Polynom(b);
		Polynom p3=new Polynom(c);

		Polynom_able pa1=p1.derivative();
		Polynom_able pa2=p2.derivative();
		Polynom_able pa3=p3.derivative();
		Polynom_able pa4=pa2.derivative();

		assertEquals("6.0x^1+-5.0x^0", pa1.toString());
		assertEquals("-6.0x^2+8.0x^0", pa2.toString());
		assertEquals("14.0x^1+0.0x^0", pa3.toString());
		assertEquals("-12.0x^1+0.0x^0",pa4.toString());


	}

	@Test
	void testArea() {

		String a="4x^4-3x^2+5x-3";

		Polynom p1=new Polynom(a);

		double x=	p1.area(0, 2, Monom.EPSILON);

		assertEquals(22.683554274759736, x);

	}


	@Test
	void testMultiplyMonom() {
		String a="5x^3+4x-6";
		String b="2x";
		String c="2x^2-5x+7.5";
		String d="0.5x^2";

		Polynom p1= new Polynom(a);
		Polynom p2= new Polynom(c);
		Monom m1=new Monom(b);
		Monom m2=new Monom(d);

		p1.multiply(m1);
		p2.multiply(m2);

		assertEquals("10.0x^4+8.0x^2+-12.0x^1", p1.toString());
		assertEquals("1.0x^4+-2.5x^3+3.75x^2", p2.toString());

	}

	@Test
	void testToString() {
		String a="2x^2-x^2";
		String b="2x^3+10x-4-5x+6-6";
		String c="-4x^4+7x^4-5x^3";

		Polynom p1=new Polynom(a);
		Polynom p2=new Polynom(b);
		Polynom p3=new Polynom(c);

		assertEquals("1.0x^2", p1.toString());
		assertEquals("2.0x^3+5.0x^1+-4.0x^0", p2.toString());
		assertEquals("3.0x^4+-5.0x^3", p3.toString());

	}

	@Test
	void testInitFromString() {
		String a="3x^4-2.4x+3x^5-6";
		String b="7x^7+6x-2";
		String c="13x^5-23x+2x-5";

		function p1= new Polynom().initFromString(a);

		Polynom p2=(Polynom) new Polynom().initFromString(b);

		Polynom p3=(Polynom) new Polynom().initFromString(c);


		assertEquals("3.0x^5+3.0x^4+-2.4x^1+-6.0x^0", p1.toString());
		assertEquals("7.0x^7+6.0x^1+-2.0x^0", p2.toString());
		assertEquals("13.0x^5+-21.0x^1+-5.0x^0", p3.toString());
	}
	
	@Test
	void testEquals() {
		String [] monoms= {"1.0x^2","4-5x^3","2x^3","-4x^4","5x^3"};
		String [] monoms2= {"0.5x^2","0.5x^2","4-5x^3","2x^3","-4x^4","5x^3"};
		Polynom p3=new Polynom("3x^2-5x+7");
		Polynom p=new Polynom();
		Polynom p2=new Polynom();


		for (int i = 0; i < monoms.length; i++) 
		{
			try {
				Monom m=new Monom(monoms[i]);
				p.add(m);				
			}
			catch (RuntimeException error) {
				System.out.println(error.getMessage() +"  "+ monoms[i]);
			}
		}
		for (int i = 0; i < monoms2.length; i++) 
		{
			try {
				Monom m=new Monom(monoms2[i]);
				p2.add(m);
				
				
			}
			catch (RuntimeException error) {
				System.out.println(error.getMessage() +"  "+ monoms[i]);
			}
		}

		assertTrue(p.equals(p2));
		assertFalse(p.equals(p3));
		
	}

}
