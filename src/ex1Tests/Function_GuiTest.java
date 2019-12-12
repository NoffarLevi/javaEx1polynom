package ex1Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import Ex1.ComplexFunction;
import Ex1.Functions_GUI;
import Ex1.Monom;
import Ex1.Polynom;
import Ex1.Range;
import Ex1.function;

class Function_GuiTest {

	@Test
	void testSaveAndFromFile() {
		function f1=new ComplexFunction().initFromString("mul(5x^4,div(3x-4,4x^2))");
		function f2=new ComplexFunction().initFromString("comp(3x^5,div(4x^3,2))");
		function f3=new ComplexFunction().initFromString("max(plus(-3x^4,plus(12x,2x^4+0.5x)),4x)");
		function f4=new ComplexFunction().initFromString("min(34x^2,div(11x,76x^2))");

		Functions_GUI funcGui= new Functions_GUI();

		funcGui.add(f1);
		funcGui.add(f2);
		funcGui.add(f3);
		funcGui.add(f4);

		Functions_GUI funcGui_2 = new Functions_GUI();

		try {
			funcGui.saveToFile("gudum.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			funcGui_2.initFromFile("gudum.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		boolean result=	funcGui.containsAll(funcGui_2);
		Range rx = new Range(-15,15);
		Range ry = new Range(-10,10);
		int resolution=500;
		funcGui.drawFunctions(700, 700, rx, ry, resolution);;
		assertTrue(result);
	}
	
	@Test
	void testinitJson() {
		Functions_GUI f1=new Functions_GUI();;
		String s1 = "3.1+2.4x^2-x^4";
		String s2 = "5+2x-3.3x+0.1x^5";
		String[] s3 = {"x+3","x-2", "x-4"};
		Polynom p1 = new Polynom(s1);
		Polynom p2 = new Polynom(s2);
		Polynom p3 = new Polynom(s3[0]);
		ComplexFunction cf3 = new ComplexFunction(p3);
		
		for(int i=1;i<s3.length;i++) {
			cf3.mul(new Polynom(s3[i]));
		}	
		ComplexFunction cf = new ComplexFunction("plus", p1,p2);
		ComplexFunction cf4 = new ComplexFunction("div", new Polynom("x+1"),cf3);
		cf4.plus(new Monom("2"));
		f1.add(cf.copy());
		f1.add(cf4.copy());
		cf.div(p1);
		f1.add(cf.copy());
		function cf5 = cf4.initFromString(s1);
		function cf6 = cf4.initFromString(s2);
		f1.add(cf5.copy());
		f1.add(cf6.copy());
		ComplexFunction max = new ComplexFunction(f1.getFuncs().get(0).copy());
		ComplexFunction min = new ComplexFunction(f1.getFuncs().get(0).copy());
		for(int i=1;i<f1.size();i++) {
			max.max(f1.getFuncs().get(i));
			min.min(f1.getFuncs().get(i));
		}
		f1.add(max);
		f1.add(min);
		f1.drawFunctions("jsontest.json");
	}

}
