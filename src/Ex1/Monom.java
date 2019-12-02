
package Ex1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.*;


/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */
public class Monom implements function {
	public static final Monom ZERO = new Monom(0, 0);
	public static final Monom MINUS1 = new Monom(-1, 0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();
	public  boolean isCreated=false;

	public static Comparator<Monom> getComp() {
		return _Comp;
	}

	public Monom(double a, int b) {
		this.set_coefficient(a);
		this.set_power(b);
		isCreated=true;
	}

	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}

	public double get_coefficient() {
		return this._coefficient;
	}

	public int get_power() {
		return this._power;
	}

	/**
	 * this method returns the derivative monom of this.
	 * 
	 * @return
	 */
	public Monom derivative() {
		if (this.get_power() == 0) {
			return getNewZeroMonom();
		}
		return new Monom((double)this.get_coefficient() * this.get_power(), this.get_power() - 1);
	}

	public double f(double x) {
		double ans = 0;
		double p = this.get_power();
		ans = this.get_coefficient() * Math.pow(x, p);
		return ans;
	}

	public boolean isZero() {
		if( Math.abs(this.get_coefficient())<=EPSILON) {
			return true;
		}
		return false;

	}
	// ** add your code below ***

	public Monom(String s)  {

		if(checkBuildString(s)==false)
		{
			throw new ArithmeticException("invlaid String "+s);
		}

	}

	/**
	 * 
	 * @param s-String that  we get in Monom(String)
	 * @return//This function checks validation of string and if its in correct form it builds the Monomial
	 * and return true or false depends on String validity
	 */
	public boolean checkBuildString( String s) 
	{
		String[] str = s.split("(x\\^)|(x)");
		try
		{
			if (str.length > 2) {
				System.out.println("Invlaid Monom");
				return false;
			}

			if (str.length == 0) 
			{
				if (s.equals("x")) {
					set_coefficient(1);
					set_power(1);
					isCreated=true;
					return true;
				}
				set_coefficient(Double.parseDouble(str[0]));

			}


			if (str.length == 1) 
			{
				if(s.contains("xx")) {
					System.out.println("Invalid Monom");
					return false ;	
				}
				if (s.equals("-x")) {
					set_coefficient(-1);
					set_power(1);
					isCreated=true;
					return true;
				}
				else if(s.contains("^")) {

					System.out.println("Invalid Monom");
					return false ; 
				}
				else if (s.contains("x")) 
				{ 

					set_coefficient(Double.parseDouble(str[0]));

					set_power(1);
					isCreated=true;
					return true;
				} 
				else
				{
					set_coefficient(Double.parseDouble(str[0]));							
					set_power(0);
					isCreated=true;
					return true;
				}

			}

			if (str.length == 2) { 


				if (str[0].equals("-")) {
					set_coefficient(-1);
					isCreated=true;

					set_power(Integer.parseInt(str[1]));
					isCreated=true;
					return true;
				} 

				else if (str[0].isEmpty()) {

					set_coefficient(1);
					isCreated=true;

					set_power(Integer.parseInt(str[1]));
					isCreated=true;
					return true;

				}

				else if(s.contains("^")){
					int counter=0;
					for (int i = 0; i < s.length(); i++) {
						if (s.charAt(i)=='x') { 
							counter++;
						}
					}
					if(counter>1)
						return false;
				}



				set_coefficient(Double.parseDouble(str[0]));
				set_power(Integer.parseInt(str[1]));
				isCreated=true;
				return true;
			}

		}
		catch (Exception e) {
			return false; 
		}
		return true;
	}

	public void subtract(Monom m) {
		if (m.get_power() != this.get_power()) {
			return;
		}
		set_coefficient(this.get_coefficient()-m.get_coefficient() );
	}

	public void add(Monom m) {
		if (m.get_power() != this.get_power()) {
			// System.out.println("Monom can't be added");
			return;
		}
		set_coefficient(m.get_coefficient() + this.get_coefficient());
	}

	public void multipy(Monom d) {
		set_coefficient(d.get_coefficient() * this.get_coefficient()); // i did something else with * insteed of +
		set_power(d.get_power() + this.get_power());

	}


	public String toString() 
	{
		if (!isCreated) {

			return "The Monom is Invalid";
		}

		if(this.get_power()==1) 
		{
			if(this.get_coefficient()==1)
				return "x";
			return this.get_coefficient()+"x";
		}
		else if(this.get_power()==0)		
			return this.get_coefficient()+"";	
		else if(this.isZero()) return "0";
		String s = this.get_coefficient()+"x^"+this.get_power();
		return s;

	}



	// ** Private Methods and Data ***

	private void set_coefficient(double a) {
		this._coefficient = a;
	}

	private void set_power(int p) {
		if (p < 0) {
			throw new RuntimeException("ERR the power of Monom should not be negative, got: " + p);
		}
		this._power = p;
	}



	private static Monom getNewZeroMonom() {
		return new Monom(ZERO);
	}

	private double _coefficient;
	private int _power;


	@Override
	public function initFromString(String s) {

		Monom temp= new Monom(s);
		if(!temp.isCreated) {
			System.out.println("No applicable Polynom");
			return null;
		}
		return (function)temp;

	}

	@Override
	public function copy() {
		
		function fc=new Monom(this.toString());
		return fc ;
	}

}