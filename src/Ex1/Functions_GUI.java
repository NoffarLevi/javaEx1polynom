package Ex1;
import java.awt.color.*;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class Functions_GUI implements functions {

	private LinkedList<function> funcs;

	public Functions_GUI() {
		funcs = new LinkedList<function>();
	}
	
	public static Color[] Colors = {Color.blue, Color.cyan, Color.MAGENTA, Color.ORANGE, Color.red, Color.GREEN, Color.PINK}; 

	@Override
	public boolean add(function e) {
		return funcs.add(e);
	}

	@Override
	public boolean addAll(Collection<? extends function> c) {	

		return funcs.addAll(c);
	}

	@Override
	public void clear() {
		funcs.clear();

	}

	@Override
	public boolean contains(Object o) {

		return funcs.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {

		return funcs.containsAll(c);
	}

	@Override
	public boolean isEmpty() {
		return funcs.isEmpty();
	}

	@Override
	public Iterator<function> iterator() {
		return funcs.iterator();
	}

	@Override
	public boolean remove(Object o) {

		return funcs.remove(o);
	}

	@Override
	public boolean removeAll(Collection<?> c) {

		return funcs.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return funcs.retainAll(c);
	}

	@Override
	public int size() {		
		return funcs.size();
	}

	@Override
	public Object[] toArray() {
		return funcs.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return funcs.toArray(a);
	}

	
	@Override
	public void initFromFile(String file) throws IOException {
		File readFile = new File(file); 

		BufferedReader br = new BufferedReader(new FileReader(readFile)); 

		String st; 
		while ((st = br.readLine()) != null) {
//			System.out.println(st);
			function f= new ComplexFunction();
			f= f.initFromString(st);
			funcs.add(f);
		}


	} 

	@Override
	public void saveToFile(String file) throws IOException {

		PrintWriter writer = new PrintWriter(file);

		for (int i = 0; i < funcs.size(); i++) 
		{
			writer.println(funcs.get(i));
		}

		writer.close();



	}

	@Override
	public  void drawFunctions(int width, int height, Range rx, Range ry, int resolution) 
	{

		StdDraw.setCanvasSize( width,height); // sets the width and the height of the window
		StdDraw.setXscale(rx.get_min(),rx.get_max() );
		StdDraw.setYscale(ry.get_min(),ry.get_max());


		for (int i = (int)ry.get_max(); i >= ry.get_min(); i--) { //Drawing x verticle line
			StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
			StdDraw.line(rx.get_max(), i, rx.get_min(), i);
			StdDraw.setPenColor(StdDraw.BLACK);
			Font font = new Font("Arial", Font.BOLD, 20);
			StdDraw.setFont(font);
			String number=Integer.toString(i);
			StdDraw.text(i+0.25, -0.4,number) ;

		}

		for (int i = (int)ry.get_max(); i >= (int)ry.get_min(); i--) { //Drawing y horizontal line

			StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
			StdDraw.line(i, ry.get_max(), i, ry.get_min());
			StdDraw.setPenColor(StdDraw.BLACK);
			Font font = new Font("Arial", Font.BOLD, 20);
			StdDraw.setFont(font);
			String number=Integer.toString(i);
			StdDraw.text(-0.25, i+0.15,number) ;

		}

		StdDraw.setPenColor(StdDraw.BLACK);   //drawing boldly the y and x axis
		StdDraw.setPenRadius(0.003); 
		StdDraw.line(rx.get_max(), 0, rx.get_min(), 0); //x
		StdDraw.line(0, ry.get_max(), 0, ry.get_min()); //y




		for (int i = 0; i < funcs.size(); i++) // drawing the functions
		{
			function f=funcs.get(i);
			StdDraw.setPenRadius(0.003);
			StdDraw.setPenColor(Colors[i%7]);

			double pixel=(rx.get_max()-rx.get_min())/resolution;

			double [] x=new double[resolution]; //initional array of x and y
			double [] y=new double[resolution];
			int j=0;
			double x_j=rx.get_min();
			
			while( j<x.length) 
			{	
				x[j]=x_j;                // adding param to arrays x and y
				y[j]=f.f(x[j]);
				x_j=x_j+pixel;

				j++;

			}

			j=0;	
			while(j+1<x.length)
			{ 

				StdDraw.line(x[j],y[j],x[j+1],y[j+1]);   //drwaing the function
				j++;
			}
			
			
			System.out.print(Colors[i%7].toString());
			System.out.println("       "+"f(x)"+"shave"+f.toString());


		}



	}

	@Override
	public void drawFunctions(String json_file) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) throws IOException {

		
		Range rx=new Range(-10,10);
		Range ry=new Range(-20,20);
		Functions_GUI f= new Functions_GUI();
//		f.initFromFile("test.txt");
//		f.saveToFile("mosh.txt");

		function f1=new Polynom("2x^2");
		function f2=new Polynom("5x^3+5x-5");
		function f3=new Polynom("5x");
		function f4=new Polynom("-2x^2-4");
		function cf=new ComplexFunction();
		cf=cf.initFromString("div(plus(2.0x^4-3.0x,5.0x),mul(7.0x^3,3.0x))");
		f.funcs.add(f1);
		f.funcs.add(f2);
		f.funcs.add(f3);
		f.funcs.add(f4);
		f.funcs.add(cf);
		f.drawFunctions(700,700,rx,ry,1000);

	}



}
