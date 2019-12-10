

package Ex1;
import java.awt.color.*;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
//import of its test?why

public class Functions_GUI implements functions {

	private LinkedList<function> funcs; //class variable to connect between all the functions

	public Functions_GUI() {
		funcs = new LinkedList<function>();
	}

	public LinkedList<function> getFuncs() {
		return funcs;
	}

	public void setFuncs(LinkedList<function> funcs) {
		this.funcs = funcs;
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

	//initializes a new collection of functions from a given  file
	@Override
	public void initFromFile(String file) throws IOException {
		File readFile = new File(file); //creates file

		BufferedReader br = new BufferedReader(new FileReader(readFile)); //Object that can read from file line to line

		String st; 
		while ((st = br.readLine()) != null) {
			function f= new ComplexFunction();
			f= f.initFromString(st);
			funcs.add(f);		
		}
		br.close();
	} 

	//saves of functions in LinkedList to file
	@Override
	public void saveToFile(String file) throws IOException {

		PrintWriter writer;
		writer = new PrintWriter(file);

		String function="";
		for (int i = 0; i < funcs.size(); i++) 
		{
			function=funcs.get(i).toString();
			writer.println(function);
		}
		writer.close();		
	}
	//draws all the functions in the collection in a GUI window using the given parameters for the GUI windo and the range & resolution
	@Override
	public  void drawFunctions(int width, int height, Range rx, Range ry, int resolution) 
	{

		StdDraw.setCanvasSize( width,height); // sets the width and the height of the window
		StdDraw.setXscale(rx.get_min(),rx.get_max() );
		StdDraw.setYscale(ry.get_min(),ry.get_max());

		double maxbetXY=Math.max(rx.get_max(), ry.get_max()); //to get max value between x and y use to draw the lines of the grid
		double minbetXY=Math.min(rx.get_min(), ry.get_min());  //to get min value between x and y use to draw the lines of the grid

		for (int i = (int)maxbetXY; i >= minbetXY; i--) { //Drawing horizontal lines of grid according to x range
			StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
			StdDraw.line(rx.get_max(), i, rx.get_min(), i);
			StdDraw.setPenColor(StdDraw.BLACK);
			Font font = new Font("Arial", Font.BOLD, 20);
			StdDraw.setFont(font);
			String number=Integer.toString(i);
			StdDraw.text(i+0.25, -0.4,number) ;
		}
		for (int i = (int)maxbetXY; i >= (int)minbetXY; i--) { //Drawing vertical lines of grid according to y range
			StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
			StdDraw.line(i, ry.get_max(), i, ry.get_min());
			StdDraw.setPenColor(StdDraw.BLACK);
			Font font = new Font("Arial", Font.BOLD, 20);
			StdDraw.setFont(font);
			String number=Integer.toString(i);
			StdDraw.text(-0.25, i+0.15,number) ;
		}
		StdDraw.setPenColor(StdDraw.BLACK);   //Making x&y axis bold
		StdDraw.setPenRadius(0.003); 
		StdDraw.line(rx.get_max(), 0, rx.get_min(), 0); //x
		StdDraw.line(0, ry.get_max(), 0, ry.get_min()); //y
		
		for (int i = 0; i < funcs.size(); i++) //drawing the functions
		{
			function f=funcs.get(i);
			StdDraw.setPenRadius(0.005);
			StdDraw.setPenColor(Colors[i%7]);

			double pixel=(rx.get_max()-rx.get_min())/resolution; //sets the size given by resolution and range of x

			double [] x=new double[resolution]; //initialize array of x&y
			double [] y=new double[resolution];
			int j=0;
			double x_j=rx.get_min();

			while(j<x.length) 
			{	
				x[j]=x_j;        //adding param to x&y array
				y[j]=f.f(x[j]); //
				x_j=x_j+pixel;

				j++;
			}
			j=0;	
			while(j+1<x.length)
			{ 
				StdDraw.line(x[j],y[j],x[j+1],y[j+1]); //drawing the function
				j++;
			}					
			System.out.print(Colors[i%7].toString());
			System.out.println("\t"+"f(x)"+"="+f.toString());
		}
	}


	//initializes a new collection of functions from a given  json file
	@Override
	public void drawFunctions(String json_file) {
		Gson gson = new Gson();
	
		try {// JSON file to JsonElement, later String
			JsonGui json = gson.fromJson(new FileReader(json_file), JsonGui.class);
			String result = gson.toJson(json);
			System.out.println(result);
			drawFunctions(json.height,json.width,json.rx,json.ry,json.resolution);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}
