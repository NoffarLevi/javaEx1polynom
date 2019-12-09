package Ex1;

import java.util.Collection;
import java.util.Stack;

public class ComplexFunction implements complex_function
{
	
	private function f1;
	private function f2;
	private Operation operation;
	

	public ComplexFunction() {}
	
	//Initializes a Complex Function given Op as enum value and 2 functions
	public ComplexFunction(Operation g,function f1,function f2)//Operation is an enum Operation
	{														  //func1&func2 are of Object type Polynomial or ComplexFunction
		if(f1 == null) throw new RuntimeException();
		this.f1=f1.copy();
		if(f2 != null) {
		this.f2=f2.copy();
		} else {
			this.f2 = null;
		}
		this.operation=g;
	}

	public ComplexFunction(function f1) {
		if(f1 == null) throw new RuntimeException();
		this.f1=f1.copy();
		this.f2=null;
		this.operation=Operation.None;
	}
//Initializes a Complex Function given Op as string and 2 functions
	public ComplexFunction(String strg,function f1,function f2) //Operation is in String form
	{														  //func1&func2 are of Object type Polynomial or ComplexFunction
		if(f1 == null) throw new RuntimeException(); //there must be an f1 function

		switch (strg) {
		case "plus":
			this.operation=Operation.Plus;	
			break;
		case "mul":
			this.operation=Operation.Times;
			break;
		case "div":
			this.operation=Operation.Divid;
			break;
		case "max":
			this.operation=Operation.Max;
			break;
		case "min":
			this.operation=Operation.Min;
			break;
		case "comp":
			this.operation=Operation.Comp;
			break;
		case "none":
			this.operation=Operation.None;
			throw new RuntimeException();
		
		case "error":
			this.operation=Operation.Error;
			throw new RuntimeException();

		default: 
			System.out.println("Unknown Operation");
			break;
		}
		this.f1 = f1.copy();
		
		this.f2 = f2.copy();

	}
	//given an x value compues f(x) for f1&f2 and then computes the given operation between them
	@Override
	public double f(double x)  { 
		if(f2==null) return f1.f(x);

		switch (operation) 
		{
		case Plus:
			return f1.f(x)+f2.f(x);

		case Times:
			return f1.f(x)*f2.f(x);
			
		case Divid:
			if(f2.f(x)==0)  //its arithmetically incorrect to divide by zero
			{throw new ArithmeticException("Can't divide by zero");}
			return f1.f(x)/f2.f(x);

		case Max:
			return Math.max(f1.f(x), f2.f(x));

		case Min:
			return Math.min(f1.f(x), f2.f(x));

		case Comp:
			return f1.f(f2.f(x));

		case None:
			if (f2==null) {
				return f1.f(x);
			}
			throw new ArithmeticException("No Applicable Operation"); 

		case Error:

			throw new ArithmeticException("Error in Operation");


		default:
			System.out.println("Unknown Operation");
			break;
		}


		return 0;
	}
    //Initializes a Complex Function given in the form of a string
	@Override 
	public function initFromString(String s)  
	{  

		try {
		if((s.contains("(") && s.contains(")")))  
		{
			int indexof=s.indexOf('(');  //index of first '('
			String oper=s.substring(0,indexof); //isolates main operation
			String afterpeel=s.substring(indexof+1, s.length()-1); // complex function without main operation
			int openPare=0; //counter for # of Open parenthesis 
			int closePare=0;//counter for # of Closed parenthesis
			int indexOfComma=0; //Comma that splits Function1 & Function2

			for (int i = 0; i < afterpeel.length(); i++) //counts number of parenthesis to find the comma that separates the f1&f2 functions
			{
				if(afterpeel.charAt(i)=='(') {
					openPare++;
				}
				else if (afterpeel.charAt(i)==')') {
					closePare++;
				}
				if(openPare==closePare && afterpeel.charAt(i)==',') {  //&&i+1<afterpeel.length()
					indexOfComma=i; //comma that splits f1&f2
					i=afterpeel.length(); //so that the for loop wont run again
				}
			}

			function f1=initFromString(afterpeel.substring(0, indexOfComma)); //initializes f1 function goes into recursion
			function f2=initFromString(afterpeel.substring(indexOfComma+1,afterpeel.length()));//initializes f2 function goes into recursion

			ComplexFunction cf=new ComplexFunction(oper,f1,f2);
			
			return cf;	

		}
		//initializes the algebraic expression expression
		function p = new Polynom(s); 
		function f= new ComplexFunction(p); 
		
		return f;
		
		} catch (Exception e) {
		//	e.printStackTrace();
			throw new RuntimeException("Invalid input");
		}
	}

	@Override
	public function copy() {	
		ComplexFunction  copyCat = new ComplexFunction(this.operation, this.f1, this.f2);
		return copyCat;
	}
	//initializes plus operation between the given Complex function and f1
	@Override
	public void plus(function f1) {
		if(this.f2 == null) {//there is only 1 function in the given complex function
			this.f2 = f1.copy();//f2 is initialized
			this.operation = Operation.Plus;
		}
		else {//the given CF was built of 2 functions so f1 computes this CF & this CF now computes Operation Plus and f2 
			this.f1 = new ComplexFunction(this.operation,this.f1.copy(),this.f2.copy());
			this.f2=f1.copy();
			this.operation = Operation.Plus;
		}

	}
	//initializes mul operation between the given Complex function and f1
	@Override
	public void mul(function f1) {
		if(this.f2 == null) {//there is only 1 function in the given complex function
			this.f2 = f1.copy();//f2 is initialized
			this.operation = Operation.Times;
		}
		else {//the given CF was built of 2 functions so f1 computes this CF & this CF now computes Operation Times and f2 
			this.f1 = new ComplexFunction(this.operation,this.f1.copy(),this.f2.copy());
			this.f2=f1.copy();
			this.operation = Operation.Times;
		}
	}
	//initializes divid operation between the given Complex function and f1
	@Override
	public void div(function f1) {
		if(this.f2 == null) {//there is only 1 function in the given complex function
			this.f2 = f1.copy();//f2 is initialized
			this.operation = Operation.Divid;
		}
		else {//the given CF was built of 2 functions so f1 computes this CF & this CF now computes Operation Divid and f2 
			this.f1 = new ComplexFunction(this.operation,this.f1.copy(),this.f2.copy());
			this.f2=f1.copy();
			this.operation = Operation.Divid;
		}
	}
//initializes max operation between the given Complex function and f1
	@Override
	public void max(function f1) {
		if(this.f2 == null) { //there is only 1 function in the given complex function
			this.f2 = f1.copy(); //f2 is initialized
			this.operation = Operation.Max;
		}
		else { //the given CF was built of 2 functions so f1 computes this CF & this CF now computes Operation  maxand f2 
			this.f1 = new ComplexFunction(this.operation,this.f1.copy(),this.f2.copy()); 
			this.f2=f1.copy();
			this.operation = Operation.Max;
		}

	}
	//initializes min operation between the given Complex function and f1
	@Override
	public void min(function f1) {
		if(this.f2 == null) {//there is only 1 function in the given complex function
			this.f2 = f1.copy();//f2 is initialized
			this.operation = Operation.Min;
		}
		else {//the given CF was built of 2 functions so f1 computes this CF & this CF now computes Operation min and f2 
			this.f1 = new ComplexFunction(this.operation,this.f1.copy(),this.f2.copy());
			this.f2=f1.copy();
			this.operation = Operation.Min;
		}
	}
	//initializes comp operation between the given Complex function and f1
	@Override
	public void comp(function f1) {
		if(this.f2 == null) {//there is only 1 function in the given complex function
			this.f2 = f1.copy();//f2 is initialized
			this.operation = Operation.Comp;
		}
		else {//the given CF was built of 2 functions so f1 computes this CF & this CF now computes Operation Comp and f2 
			this.f1 = new ComplexFunction(this.operation,this.f1.copy(),this.f2.copy());
			this.f2=f1.copy();
			this.operation = Operation.Comp;
		}
	}

	@Override
	public function left() {
		if (this.f1==null) {
			throw new ArithmeticException("No applicable f1 function");
		}	
		return this.f1.copy();		
	}

	@Override
	public function right() {
		if (this.f2==null) {
			return null;
		}	
		return this.f2.copy();
	}

	@Override
	public Operation getOp() {	
		return this.operation;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof function) {
			function temp=(function)obj;
			for (double i = -100; i < 100; i=i+0.1) {
				if(this.f(i)!=temp.f(i))
					return false;
			}
			return true;
		}
		return false;
		
		
		
	}
	
	public String toString() {
		if(f2==null) {
			return this.f1.toString();
		}
		String ans ="("+this.f1.toString()+","+this.f2.toString()+")";
		switch (this.operation) {
		case Plus:
			return "plus"+ans;
		case Divid:
			return "div"+ans;
		case Times:
			return "mul"+ans;
		case Min:
			return "min"+ans; 
		case Max:
			return "max"+ans;
		case Comp:
			return "comp"+ans;
		default:
			return ans;
		}
	}

}