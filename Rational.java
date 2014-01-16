
/**
 * @author Lee Glendenning
 * id 120289190
 * glen9190@mylaurier.ca
 * @version sep. 27, 2013
 * 
 */

package glen9190;

//import scanner
import java.util.Scanner;

public class Rational
{
	
	private double a; //numerator
	private double b; //denominator
	
	/**
	 * Rational constructor calls method normalize and updates this numerator and denominator
	 * 
	 * @param numerator - int numerator
	 * @param denominator - int denominator
	 * 
	 */
	public Rational(double numerator, double denominator)
	{
		this.a = numerator;
        this.b = denominator;
        //this.normalize();
    }
	
	/**
	 * Rational constructor reads line of input.txt, creates rational object and calls normalize
	 * 
	 * @param input - string line of input.txt file 
	 */
	public Rational(String input)
	{
		Scanner split = new Scanner(input);
		split.useDelimiter("/");
		this.a = split.nextInt();
		this.b = 1;
		if (split.hasNext())
		{
			this.b = split.nextInt();
			//this.normalize();
		}
		split.close();
	}
	
	/**
	 * toString method formats a rational object to a string and returns it
	 * 
	 * @return c - formatted string form of rational object (String)
	 * 
	 */
	public String toString()
	{
		String c;
		if (a < 0 && b < 0)
		{
			a = Math.abs(a);
			b = Math.abs(b);
		}else if(a > 0 && b < 0)
		{
			a = a - 2*a;
			b = Math.abs(b);
		}
		if(b == 1)
		{
			c = a + "";
		}else
		{
			c = a + "/" + b;
		}
		return c;
					
	}
	
	/**
	 * equals method defines equivalency between rational objects
	 * 
	 * @param other - rational object to compare this to
	 * 
	 * @return true or false based on equivalency (boolean)
	 * 
	 */
	
	public boolean equals(Rational other)
	{
		return(this.a == other.a && this.b == other.b);
	}
	
	/**
	 * mod method performs a modulus operation on rational objects
	 * 
	 * @param i - rational object to perform operation
	 * 
	 * @return result - true or false based on equivalency (boolean)
	 * 
	 */
	
	public boolean mod(Rational i)
	{
		boolean result = true;
		if (i.a == 0)
		{
			result = false;
		}
		else
		{
			result = (this.a/this.b)%(i.a/i.b) == 0;
		}
		return(result);
	}
	
	/**
	 * abs method takes absolute value of rational object
	 * 
	 * @return c - rational object with absolute value of this
	 * 
	 */
	
	public Rational abs()
	{
		Rational c = new Rational(Math.abs(a),b);
		return (c);
	}
	
	/**
	 * lesseq method defines less than or equal to other
	 * 
	 * @param other - rational object to compare this to
	 * 
	 * @return true or false based on values (boolean)
	 * 
	 */
	
	public boolean lesseq(Rational other)
	{
		return(this.a <= other.a);
	}
	
	/**
	 * less method defines less than other
	 * 
	 * @param other - rational object to compare this to
	 * 
	 * @return true or false based on values (boolean)
	 * 
	 */
	
	public boolean less(Rational other)
	{
		
		return(this.a < other.a);
	}
	
	public boolean greater(Rational other)
	{
		
		return(this.a > other.a);
	}
	
	/**
	 * add method adds two rational objects
	 * 
	 * @param other - rational object to add to
	 * 
	 * @return c - sum of two rational objects (rational object)
	 */
	
	public Rational add(Rational other)
	{
		Rational c = new Rational(this.a * other.b + this.b * other.a, this.b * other.b);
		
		return (c);
	}
	
	
	/**
	 * sub method subtracts one rational object from another
	 * 
	 * @param other - rational object to subtract from
	 * 
	 * @return c - difference of two rational objects (rational object)
	 */
	public Rational sub(Rational other)
	{
		Rational c = new Rational(this.a * other.b - this.b * other.a, this.b * other.b);
		return(c);
	}
	
	/**
	 * mul method multiplies two rational objects
	 * 
	 * @param other - rational object to multiply by
	 * 
	 * @return c - product of two rational objects (rational object)
	 */
	public Rational mul(Rational other)
	{
		Rational c = new Rational(this.a*other.a, this.b*other.b);
		return (c);
	}
	
	/**
	 * div method divides two rational objects
	 * 
	 * @param other - rational object to divide by
	 * 
	 * @return c - quotient of two rational objects (rational object)
	 */
	public Rational div(Rational other)
	{
		
		Rational c = new Rational(this.a*other.b, this.b*other.a);
		return (c);
	}
	
	public double convert()
	{
		if (this.b == 0)
		{
			InputView.setEr(true);
		}
		return this.a/this.b;
	}
	
	
	/**
	 * gcd method finds the greatest common denominator of two denominator integers
	 * 
	 * @param int a - this denominator
	 * @param int b - other denominator
	 * 
	 * @return c - sum of two rational objects (rational object)
	 */
	/*public static int gcd(double a, double b)
	{
		int g = 0;

		if (a < b) {
		    g = a;
		    a = b;
		    b = g;
		}
		while (b != 0) {
		    g = a % b;
		    a = b;
		    b = g;
		}
		return (a);
	}*/
	
	/**
	 * normalize method calls gcd and reduces rational objects based on their gcd
	 * 
	 */
	/*private void normalize() 
	{
		int g = gcd(this.a, this.b);
		this.a = this.a / g;
		this.b = this.b / g;

		if (this.b < 0) {
		    this.b = -this.b;
		    this.a = -this.a;
		}
	}*/
	
}

