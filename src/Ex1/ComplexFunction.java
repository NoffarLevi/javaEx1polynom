package Ex1;

import java.util.Collection;
import java.util.Stack;

public class ComplexFunction implements complex_function
{
	//int FINAL =0;
	private function f1;
	private function f2;
	private Operation operation;


	public ComplexFunction() {}

//	public ComplexFunction(String s) {}
	
	public ComplexFunction(Operation g,function f1,function f2)
	{
		if(f1 == null) throw new RuntimeException();
		this.f1=f1;
		this.f2=f2;
		this.operation=g;
	}


	public ComplexFunction(function f1) {
		if(f1 == null) throw new RuntimeException();
		this.f1=f1;
		this.f2=null;
		this.operation=Operation.None;
	}

	public ComplexFunction(String strg,function f1,function f2)
	{
		if(f1 == null) throw new RuntimeException();

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
			break;
		case "error":
			this.operation=Operation.Error;
			break;

		default:
			break;
		}
		this.f1 = f1;
		this.f2 = f2;

	}



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
			if(f2.f(x)==0)
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

	@Override 
	public function initFromString(String s)   //mul(5x^2+3x,4) =>>> 5x^2+3x,4
	{  

		try {
		if((s.contains("(") && s.contains(")")))  
		{
			int indexof=s.indexOf('(');
			String oper=s.substring(0,indexof);
			String afterpeel=s.substring(indexof+1, s.length()-1);	
			int openPare=0;
			int closePare=0;
			int indexOfComma=0;

			for (int i = 0; i < afterpeel.length(); i++)
			{
				if(afterpeel.charAt(i)=='(') {
					openPare++;
				}
				else if (afterpeel.charAt(i)==')') {
					closePare++;
				}
				if(openPare==closePare && afterpeel.charAt(i)==',') {  //&&i+1<afterpeel.length()
					indexOfComma=i;
					i=afterpeel.length();	
				}
			}

			function f1=initFromString(afterpeel.substring(0, indexOfComma));
			function f2=initFromString(afterpeel.substring(indexOfComma+1,afterpeel.length()));

			ComplexFunction cf=new ComplexFunction(oper,f1,f2);
			
			return cf;	

		}
		
		
		function f= new Polynom(s);
		
		return f;
		} catch (Exception e) {
			throw new RuntimeException("Invalid input");
		}
	}

	



	@Override
	public function copy() {	
		ComplexFunction  copyCat = new ComplexFunction(this.operation, this.f1, this.f2);
		return copyCat;
	}

	@Override
	public void plus(function f1) {
		if(this.f2 == null) {
			this.f2 = f1;
			this.operation = Operation.Plus;
		}
		else {
			this.f1 = new ComplexFunction(this.operation,this.f1,this.f2);
			this.f2=f1;
			this.operation = Operation.Plus;
		}

	}

	@Override
	public void mul(function f1) {
		if(this.f2 == null) {
			this.f2 = f1;
			this.operation = Operation.Times;
		}
		else {
			this.f1 = new ComplexFunction(this.operation,this.f1,this.f2);
			this.f2=f1;
			this.operation = Operation.Times;
		}
	}

	@Override
	public void div(function f1) {

		if(this.f2 == null) {
			this.f2 = f1;
			this.operation = Operation.Divid;
		}
		else {
			this.f1 = new ComplexFunction(this.operation,this.f1,this.f2);
			this.f2=f1;
			this.operation = Operation.Divid;
		}
	}

	@Override
	public void max(function f1) {
		if(this.f2 == null) {
			this.f2 = f1;
			this.operation = Operation.Max;
		}
		else {
			this.f1 = new ComplexFunction(this.operation,this.f1,this.f2);
			this.f2=f1;
			this.operation = Operation.Max;
		}

	}

	@Override
	public void min(function f1) {
		if(this.f2 == null) {
			this.f2 = f1;
			this.operation = Operation.Min;
		}
		else {
			this.f1 = new ComplexFunction(this.operation,this.f1,this.f2);
			this.f2=f1;
			this.operation = Operation.Min;
		}
	}

	@Override
	public void comp(function f1) {
		if(this.f2 == null) {
			this.f2 = f1;
			this.operation = Operation.Comp;
		}
		else {
			this.f1 = new ComplexFunction(this.operation,this.f1,this.f2);
			this.f2=f1;
			this.operation = Operation.Comp;
		}
	}

	@Override
	public function left() {
		if (this.f1==null) {
			throw new ArithmeticException("No applicable f1 function");
		}	
		return this.f1;		
	}

	@Override
	public function right() {
		if (this.f2==null) {
			return null;
		}	
		return this.f2;
	}

	@Override
	public Operation getOp() {	
		return this.operation;
	}
	
	public String toString() {
		if(f2==null) {
			return this.f1.toString();
		}
		String ans ="("+this.f1+","+this.f2+")";
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
