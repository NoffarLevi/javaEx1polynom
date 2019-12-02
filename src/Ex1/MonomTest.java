package Ex1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MonomTest {

	Monom m;
	/**
	 * test monom  from the shape (double x,int y)
	 */
	@Test
	void testMonom() {
		m = new Monom(3.4,5);
		assertEquals("3.4x^5",m.toString());		
	}
	
	/**
	 * test monom from the shape monom(monom ot)
	 */
	
	@Test
	void testMonomot (){
		m = new Monom("3x");
		Monom s=new Monom(m);

		assertEquals("3.0x",s.toString());	
	}
	
	/**
	 * test Derivativ
	 */
	
	@Test
	void testDerivative() {
		m = new Monom("3x");
		System.out.println(m);
		System.out.println(m.derivative());
		assertEquals("3.0",m.derivative().toString());

		
	}	
	
	/**
	 * test monom that calculates a given x value
	 */
	
	@Test
	void testf(){
		m = new Monom("3x^3");
		assertEquals(24.0,m.f(2.0));	
	}
	
	
	
	/**
	 * test if monom is logically equal to zero
	 */
	
	@Test
	void testIsZero(){
		m = new Monom("3x^3");
		Monom a=new Monom("0.000000000001x^2");
		assertEquals(false,m.isZero());	
		assertEquals(true,a.isZero());
	}
	
	/**
	 * test monom that gets a String
	 */
	
	@Test
	void testMonomfromString(){
		m = new Monom("3.5x^3");
		assertEquals("3.5x^3",m.toString());
	}
	
	
	/**
	 * test substract
	 */
	
	@Test
	void testSubstract(){
		m = new Monom("3x^3");
		Monom a=new Monom("1x^3");
		m.subtract(a);
		assertEquals("2.0x^3",m.toString());
		m.subtract(a);
		assertEquals(m.toString(),a.toString());
	}
	
	/**
	 * test add
	 */
	
	@Test
	void testAdd() {
		m = new Monom("3x^3");
		Monom a=new Monom("1x^3");
		m.add(a);
		assertEquals("4.0x^3",m.toString());
		m.add(a);
		assertEquals("5.0x^3",m.toString());
	}
	
	/**
	 * test multiply
	 */
	
	@Test
	void testMultiply() {
		m = new Monom("3x^3");
		Monom a=new Monom("3x^3");
		m.multipy(a);
		assertEquals("9.0x^6",m.toString());

	}
	
	/**
	 * test multiply
	 */
	
	@Test
	void testMultiply() {
		m = new Monom("3x^3");
		Monom a=new Monom("3x^3");
		m.multipy(a);
		assertEquals("9.0x^6",m.toString());

	}
	
	
	/**
	 * test is Monom build Zero
	 */
	
	@Test
	void testCopy() {
		m = new Monom(3.7,9);
		Monom a=new Monom(m.copy().toString());
		assertEquals("3.7x^9",a.toString());

	}
	


}
