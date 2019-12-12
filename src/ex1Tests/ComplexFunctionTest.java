package ex1Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.security.spec.ECField;

import org.junit.jupiter.api.Test;

import Ex1.ComplexFunction;
import Ex1.Monom;
import Ex1.Operation;
import Ex1.Polynom;
import Ex1.function;

class ComplexFunctionTest {

	@Test
	void testComplexFunction() {

		function f1=new Polynom("5x+2x^3-4x");	
		function f2=new Polynom("4x^3-2+3x^2-5x");
		Operation cfoper1=Operation.Times;

		function f3=new Polynom("9x^3-4x^2-5x");	
		function f4=new Polynom("12x+2x^3+9");
		String cfoper2="div";

		ComplexFunction cf1=new ComplexFunction(cfoper1,f1,f2);
		ComplexFunction cf2=new ComplexFunction(cfoper2,f3,f4);
		ComplexFunction cf3=new ComplexFunction(f1);

		assertEquals("mul(2.0x^3+x,4.0x^3+3.0x^2-5.0x-2.0)", cf1.toString());
		assertEquals("div(9.0x^3-4.0x^2-5.0x,2.0x^3+12.0x+9.0)", cf2.toString());
		assertEquals("2.0x^3+x", cf3.toString());
	}

	@Test
	void testF() {
		String a="3+2x^2-x^4";
		String b="5+2x-3x+x^5";
		String c="plus(-1.0x^4+2.5x^2+3,0.5x^5-1.5x+5.0)";

		function f1=new Polynom("2x^4+x^3+3");
		function f2=new Polynom("x^5-x+5.0");
		Operation oper=Operation.Plus;

		ComplexFunction cf=(ComplexFunction)new ComplexFunction().initFromString(a);
		ComplexFunction cf1=(ComplexFunction)new ComplexFunction().initFromString(b);
		ComplexFunction cf2=(ComplexFunction) new ComplexFunction().initFromString(c);

		ComplexFunction cf3=new ComplexFunction("plus",f1,f2);

		assertEquals(-5.0, cf.f(2));
		assertEquals(5, cf1.f(0));
		assertEquals(-2793.850564, cf2.f(-5.3),0.00001);
		assertEquals(-97, cf3.f(-3));
	}     

	@Test
	void testInitFromString()
	{
		String a="-1.0   x^4    +2   .0x  ^2+3.  0";
		String b="5+2x-3x+x^5";
		String c="plus(-1.0x^4+2.5x^2+3,plus(0.5x^5-1.5x+5.0,3.5x^4))";
		String d="plus(div(8.0x^2+2.0x^2+3,5x^3),plus(7.5x^5-1.5x+5.0,mul(3.5x^4,2)))";
		String f="plus(mul(3x,div(5,5x^2)adasd),3x)";  //wrong input

		ComplexFunction cf=null;
		ComplexFunction cf1=null;
		ComplexFunction cf2=null;
		ComplexFunction cf4=null;
		ComplexFunction cf3=null;
		try {
			cf=(ComplexFunction)new ComplexFunction().initFromString(a);
			cf1=(ComplexFunction)new ComplexFunction().initFromString(b);
			cf2=(ComplexFunction)new ComplexFunction().initFromString(c);
			cf4=(ComplexFunction)new ComplexFunction().initFromString(d);
			cf3=(ComplexFunction)new ComplexFunction().initFromString(f);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		assertEquals("-1.0x^4+2.0x^2+3.0", cf.toString());
		assertEquals("1.0x^5-1.0x+5.0", cf1.toString());
		assertEquals("plus(-1.0x^4+2.5x^2+3.0,plus(0.5x^5-1.5x+5.0,3.5x^4))", cf2.toString());
		assertEquals("plus(div(10.0x^2+3.0,5.0x^3),plus(7.5x^5-1.5x+5.0,mul(3.5x^4,2.0)))", cf4.toString());
	}

	@Test
	void testCopy() {

		Polynom polynom = new Polynom ("2x^3+4x");
		ComplexFunction cf0 = new ComplexFunction("plus",polynom,polynom); 
		polynom.multiply(new Monom ("2")); //we changed Polynom by multiflied it with 2 but it didnt change the complex function
		System.out.println(polynom);
		System.out.println(cf0.toString());

		String a="3+2x^2-x^4";
		String b="div(5+2x-3x+x^5,mul(4x^4+4,-6x^3))";
		String c="plus(-1.0x^4+2.5x^2+3,plus(0.5x^5-1.5x+5.0,3.5x^4))";

		ComplexFunction cf=(ComplexFunction)new ComplexFunction().initFromString(a);
		ComplexFunction cf1=(ComplexFunction)new ComplexFunction().initFromString(b);
		ComplexFunction cf2=(ComplexFunction)new ComplexFunction().initFromString(c);

		function copy1=new ComplexFunction(cf.copy());
		function copy2=new ComplexFunction(cf1.copy());
		function copy3=new ComplexFunction(cf2.copy());


		assertTrue( cf0.equals(polynom));		
		assertEquals("-1.0x^4+2.0x^2+3.0", copy1.toString());
		assertEquals("div(1.0x^5-1.0x+5.0,mul(4.0x^4+4.0,-6.0x^3))", copy2.toString());
		assertEquals("plus(-1.0x^4+2.5x^2+3.0,plus(0.5x^5-1.5x+5.0,3.5x^4))", copy3.toString());

		cf1.max(cf2);
		assertEquals("div(1.0x^5-1.0x+5.0,mul(4.0x^4+4.0,-6.0x^3))", copy2.toString());
		assertEquals("max(div(1.0x^5-1.0x+5.0,mul(4.0x^4+4.0,-6.0x^3)),plus(-1.0x^4+2.5x^2+3.0,plus(0.5x^5-1.5x+5.0,3.5x^4)))", cf1.toString());

	}

	@Test
	void testPlus() {

		String a="plus(-1.0x^4+2.5x^2+3,0.5x^5-1.5x+5.0)";
		String b="plus(-1.0x^4+2.4x^2+3.1,0.5x^5-1.5x+5.0)";
		String c="plus(plus(1.0x+1.0,plus(plus(1.0x+3.0,1.0x-2.0),1.0x+4.0)),2.0)";

		ComplexFunction cf=(ComplexFunction)new ComplexFunction().initFromString(a);
		ComplexFunction cf1=(ComplexFunction)new ComplexFunction().initFromString(b);
		ComplexFunction cf2=(ComplexFunction)new ComplexFunction().initFromString(c);

		assertEquals(66.5, cf.f(3));
		assertEquals(14.7, cf1.f(2.0));
		assertEquals(0.0, cf2.f(-2));
	}

	@Test
	void testMul()
	{
		String a="mul(-1.0x^4+2.5x^2+3,0.5x^5-1.5x+5.0)";
		String b="mul(-1.0x^4+2.4x^2+3.1,0.5x^5-1.5x+5.0)";
		String c="mul(mul(1.0x+1.0,mul(mul(1.0x+3.0,1.0x-2.0),1.0x+4.0)),2.0)";

		ComplexFunction cf=(ComplexFunction)new ComplexFunction().initFromString(a);
		ComplexFunction cf1=(ComplexFunction)new ComplexFunction().initFromString(b);
		ComplexFunction cf2=(ComplexFunction)new ComplexFunction().initFromString(c);

		assertEquals(-6771.0, cf.f(3));
		assertEquals(-59.4000, cf1.f(2.0),0.000001);
		assertEquals(13.9453125, cf2.f(-2.25));
	}

	@Test
	void testDiv() {

		String a="div(-1.0x^4+2.5x^2+3,0.5x^5-1.5x+5.0)";
		String b="div(-1.0x^4+2.4x^2+3.1,0.5x^5-1.5x+5.0)";
		String c="div(div(1.0x+1.0,div(div(1.0x+3.0,1.0x-2.0),1.0x+4.0)),2.0)";

		ComplexFunction cf=(ComplexFunction)new ComplexFunction().initFromString(a);
		ComplexFunction cf1=(ComplexFunction)new ComplexFunction().initFromString(b);
		ComplexFunction cf2=(ComplexFunction)new ComplexFunction().initFromString(c);

		assertEquals(-0.16666666, cf.f(2),0.0000001);
		assertEquals(-0.448097, cf1.f(3.5),0.000001);
		assertEquals(2.03125, cf2.f(-4.25));	
	}

	@Test
	void testMax() {

		String a="max(-1.0x^4+2.5x^2+3,0.5x^5-1.5x+5.0)";
		String b="max(-1.0x^4+2.4x^2+3.1,0.5x^5-1.5x+5.0)";
		String c="max(max(1.0x+1.0,max(max(1.0x+3.0,1.0x-2.0),1.0x+4.0)),2.0)";

		ComplexFunction cf=(ComplexFunction)new ComplexFunction().initFromString(a);
		ComplexFunction cf1=(ComplexFunction)new ComplexFunction().initFromString(b);
		ComplexFunction cf2=(ComplexFunction)new ComplexFunction().initFromString(c);

		assertEquals(86.85183, cf.f(2.8),0.0001);
		assertEquals(6.546875, cf1.f(1.5),0.000001);
		assertEquals(3.75, cf2.f(-0.25));	
	}

	@Test
	void testMin() {
		String a="min(-1.0x^4+2.5x^2+3,0.5x^5-1.5x+5.0)";
		String b="min(-1.0x^4+2.4x^2+3.1,0.5x^5-1.5x+5.0)";
		String c="min(min(1.0x+1.0,min(min(1.0x+3.0,1.0x-2.0),1.0x+4.0)),2.0)";

		ComplexFunction cf=(ComplexFunction)new ComplexFunction().initFromString(a);
		ComplexFunction cf1=(ComplexFunction)new ComplexFunction().initFromString(b);
		ComplexFunction cf2=(ComplexFunction)new ComplexFunction().initFromString(c);

		assertEquals(-169.41359, cf.f(3.8),0.0001);
		assertEquals(-1680.5625, cf1.f(6.5),0.0001);
		assertEquals(-2.55, cf2.f(-0.55));	
	}

	@Test
	void testComp() {
		String a="comp(-1.0x^4+2.5x^2+3,0.5x^5-1.5x+5.0)";
		String b="comp(-1.0x^4+2.4x^2+3.1,0.5x^5-1.5x+5.0)";
		String c="comp(comp(1.0x+1.0,comp(comp(1.0x+3.0,1.0x-2.0),1.0x+4.0)),2.0)";

		ComplexFunction cf=(ComplexFunction)new ComplexFunction().initFromString(a);
		ComplexFunction cf1=(ComplexFunction)new ComplexFunction().initFromString(b);
		ComplexFunction cf2=(ComplexFunction)new ComplexFunction().initFromString(c);

		assertEquals(1.304674, cf.f(-1.8),0.0001);
		assertEquals(-104195.29999, cf1.f(2),0.001);
		assertEquals(8.0, cf2.f(0),0.00001);
	}

	@Test
	void testLeft() {
		String a="mul(-1.0x^4+2.5x^2+3,0.5x^5-1.5x+5.0)";
		String b="div(-1.0x^4+2.4x^2+3.1,0.5x^5-1.5x+5.0)";
		String c="mul(plus(1.0x+1.0,plus(mul(1.0x+3.0,1.0x-2.0),1.0x+4.0)),2.0)";

		ComplexFunction cf=(ComplexFunction)new ComplexFunction().initFromString(a);
		ComplexFunction cf1=(ComplexFunction)new ComplexFunction().initFromString(b);
		ComplexFunction cf2=(ComplexFunction)new ComplexFunction().initFromString(c);

		assertEquals(0.6024, cf.left().f(-1.8),0.0001);
		assertEquals(3.6375, cf1.left().f(0.5),0.001);
		assertEquals(-1.0, cf2.left().f(-3),0.00001);
		assertEquals("-1.0x^4+2.5x^2+3.0", cf.left().toString());
		assertEquals("plus(x+1.0,plus(mul(x+3.0,x-2.0),x+4.0))", cf2.left().toString());
	}

	@Test
	void testRight() {
		String a="mul(-1.0x^4+2.5x^2+3,0.5x^5-1.5x+5.0)";
		String b="div(-1.0x^4+2.4x^2+3.1,0.5x^5-1.5x+5.0)";
		String c="mul(plus(1.0x+1.0,plus(mul(1.0x+3.0,1.0x-2.0),1.0x+4.0)),2.0)";

		ComplexFunction cf=(ComplexFunction)new ComplexFunction().initFromString(a);
		ComplexFunction cf1=(ComplexFunction)new ComplexFunction().initFromString(b);
		ComplexFunction cf2=(ComplexFunction)new ComplexFunction().initFromString(c);

		assertEquals(-1.747840, cf.right().f(-1.8),0.0001);
		assertEquals(4.265625, cf1.right().f(0.5),0.001);
		assertEquals(2.0, cf2.right().f(-3),0.00001);
		assertEquals("2.0", cf2.right().toString());
	}

	@Test
	void testGetOp() {
		String a="mul(-1.0x^4+2.5x^2+3,0.5x^5-1.5x+5.0)";
		String b="div(-1.0x^4+2.4x^2+3.1,0.5x^5-1.5x+5.0)";
		String c="mul(plus(1.0x+1.0,plus(mul(1.0x+3.0,1.0x-2.0),1.0x+4.0)),2.0)";

		ComplexFunction cf=(ComplexFunction)new ComplexFunction().initFromString(a);
		ComplexFunction cf1=(ComplexFunction)new ComplexFunction().initFromString(b);
		ComplexFunction cf2=(ComplexFunction)new ComplexFunction().initFromString(c);

		assertEquals("Times" ,cf.getOp().toString());
		assertEquals("Divid", cf1.getOp().toString());
		assertEquals("Times", cf2.getOp().toString());
	}

	@Test
	void testToString() {
		String a="mul(-1.0x^4+2.5x^2+3,0.5x^5-1.5x+5.0)";
		String b="div(-1.0x^4+2.4x^2+3.1,0.5x^5-1.5x+5.0)";
		String c="mul(plus(1.0x+1.0,plus(mul(1.0x+3.0,1.0x-2.0),1.0x+4.0)),2.0)";
		String d="3+2x^2-x^4";
		String e="5+2x-3x+x^5";
		String f="plus(-1.0x^4+2.5x^2+3,0.5x^5-1.5x+5.0)";

		ComplexFunction cf=(ComplexFunction)new ComplexFunction().initFromString(a);
		ComplexFunction cf1=(ComplexFunction)new ComplexFunction().initFromString(b);
		ComplexFunction cf2=(ComplexFunction)new ComplexFunction().initFromString(c);
		ComplexFunction cf3=(ComplexFunction)new ComplexFunction().initFromString(d);
		ComplexFunction cf4=(ComplexFunction)new ComplexFunction().initFromString(e);
		ComplexFunction cf5=(ComplexFunction)new ComplexFunction().initFromString(f);

		assertEquals("mul(-1.0x^4+2.5x^2+3.0,0.5x^5-1.5x+5.0)" ,cf.toString());
		assertEquals("div(-1.0x^4+2.4x^2+3.1,0.5x^5-1.5x+5.0)", cf1.toString());
		assertEquals("mul(plus(x+1.0,plus(mul(x+3.0,x-2.0),x+4.0)),2.0)", cf2.toString());
		assertEquals("-1.0x^4+2.0x^2+3.0" ,cf3.toString());
		assertEquals("1.0x^5-1.0x+5.0", cf4.toString());
		assertEquals("plus(-1.0x^4+2.5x^2+3.0,0.5x^5-1.5x+5.0)" ,cf5.toString());

	}
	@Test
	void testEquals() {
		String a="mul(-1.0x^4+2.5x^2+3,0.5x^5-1.5x+5.0)";
		String b="div(-1.0x^4+2.4x^2+3.1,0.5x^5-1.5x+5.0)";
		String c="mul(plus(1.0x+1.0,plus(mul(1.0x+3.0,1.0x-2.0),1.0x+4.0)),2.0)";
		function d= new Polynom("3+2x^2-x^4");
		function e= new Polynom("3+2x^2-x^4");	
		Operation oper=Operation.Plus;
		String a1="mul(0.5x^5-1.5x+5.0,-1.0x^4+2.5x^2+3)";
		String b1="div(0.5x^5-1.5x+5.0,-1.0x^4+2.4x^2+3.1)";
		String c1="mul(2.0,plus(1.0x+1.0,plus(mul(1.0x+3.0,1.0x-2.0),1.0x+4.0)))";

		ComplexFunction cf=(ComplexFunction)new ComplexFunction().initFromString(a);
		ComplexFunction cf1=(ComplexFunction)new ComplexFunction().initFromString(b);
		ComplexFunction cf2=(ComplexFunction)new ComplexFunction().initFromString(c);
		ComplexFunction cf3=(ComplexFunction)new ComplexFunction(oper,d,e);
		ComplexFunction cf7=(ComplexFunction)new ComplexFunction(oper,e,d);
		ComplexFunction cf4=(ComplexFunction)new ComplexFunction().initFromString(a1);
		ComplexFunction cf5=(ComplexFunction)new ComplexFunction().initFromString(b1);
		ComplexFunction cf6=(ComplexFunction)new ComplexFunction().initFromString(c1);

		assertTrue(cf.equals(cf4));	
		assertFalse(cf1.equals(cf5));
		assertTrue(cf2.equals(cf6));
		assertTrue(cf3.equals(cf7));
	}

}










