package ex1Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import Ex1.ComplexFunction;
import Ex1.Functions_GUI;
import Ex1.function;

class Function_GuiTest {

	
	
	
	@Test
	void testSaveAndFromFile() {
		function f1=new ComplexFunction().initFromString("mul(5x^4,div(3x-4,4x^2))");
		function f2=new ComplexFunction().initFromString("comp(3x^5,div(4x^3,2))");
		function f3=new ComplexFunction().initFromString("max(plus(-3x^4,plus(12x,2x^4+0.5x)),4x)");
		function f4=new ComplexFunction().initFromString("min(34x^2,div(11x,76x^2))");
//		System.out.println(f1.toString());
//		System.out.println(f2.toString());
//		System.out.println(f3.toString());
//		System.out.println(f4.toString());
//		LinkedList<function> link = new LinkedList<function>();
//		link.add(f1);
//		link.add(f2);
//		link.add(f3);
//		link.add(f4);
		
		Functions_GUI funcGui= new Functions_GUI();
		Functions_GUI funcGui_2 = new Functions_GUI();
		funcGui.add(f1);
		funcGui.add(f2);
		funcGui.add(f3);
		funcGui.add(f4);
		funcGui.saveToFile("yishay.txt");
		try {
			funcGui_2.initFromFile("yishay.txt");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(funcGui_2.toString());
		//assertTrue(funcGui_2.containsAll(funcGui));
		assertTrue(funcGui_2.contains(f1));
//		assertTrue(funcGui_2.contains(f2));
//		assertTrue(funcGui_2.contains(f3));
//		assertTrue(funcGui_2.contains(f4));
	}

//	@Test
//	void testDrawFunctionsString() {
//		fail("Not yet implemented");
//	}

}
