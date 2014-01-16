/**
 * @author Lee Glendenning
 * id 120289190
 * glen9190@mylaurier.ca
 * @version Nov. 30, 2013
 * 
 */

package glen9190;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.text.DecimalFormat;

import javax.swing.JComponent;
@SuppressWarnings("serial")
public class PlotPoints extends JComponent{
	private Poly eval;
	private static double a;
	private static double b;
	private static int POINTS = 100;
	static double ymin = 0;
	static double ymax = 0;
	//for function points
	private static double [] x = new double [POINTS + 1];
	private static double [] y = new double [POINTS + 1];
	//for first derivative points
	private static double [] x2 = new double [POINTS + 1];
	private static double [] y2 = new double [POINTS + 1];
	//for second derivative points
	private static double [] x3 = new double [POINTS + 1];
	private static double [] y3 = new double [POINTS + 1];
	
	/**
	 * plotpoints constructor to initialize plotpoints object
	 * 
	 * @param exp - string expression
	 * @param var - variable used in expression
	 * @param start - initial x coordinate
	 * @param end - last x coordinate
	 */
	public PlotPoints(String exp, String var, double start, double end) {
		if (exp.startsWith("-"))
		{
			exp = "-1*" + exp.substring(1);
		}
		try{
			this.eval = new Poly(exp);}
		catch(Exception e){
			NumericalView.setError("input(s) invalid");
		}
		PlotPoints.a = start;
		PlotPoints.b = end;
		this.repaint();
		
	}
	/***
	 * paintComponent paints points to window
	 */
	public void paintComponent(Graphics g)
	{
		//System.out.println(getWidth());
  	  	//System.out.println(getHeight());
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		//initialize ymin and ymax to first values evaluated at
		PlotPoints.ymin = this.eval.evalAt(PlotPoints.a);
		PlotPoints.ymax = PlotPoints.ymin;
		
		//fill function, derivative and second derivative coordinate arrays
		if (!this.eval.equals("0"))
		{
			PlotPoints.evalPoints(PlotPoints.x, PlotPoints.y, this.eval);
			PlotPoints.evalPoints(PlotPoints.x2, PlotPoints.y2, this.eval.diff());
			PlotPoints.evalPoints(PlotPoints.x3, PlotPoints.y3, this.eval.diff().diff());
		}
		
		//print equations to window
		g2d.setColor(Color.green);
		g2d.drawString("y = " + this.eval.toString(), (getWidth()/2)+2, 12);
		g2d.setColor(Color.blue);
		g2d.drawString("y' = " + this.eval.diff().toString(), (getWidth()/2)+2, 26);
		g2d.setColor(Color.red);
		g2d.drawString("y'' = " + this.eval.diff().diff().toString(), (getWidth()/2)+2, 40);
		g2d.setColor(Color.black);
		
		//calculate pixels per unit
		double sx = getWidth()/(PlotPoints.b-PlotPoints.a);
		double sy = getHeight()/(PlotPoints.ymax - PlotPoints.ymin);
		
		/////////////
		double h = 0;
		DecimalFormat df = new DecimalFormat("#.##");
		//y scale
		for (double k = PlotPoints.ymax; k >= PlotPoints.ymin; k -= (PlotPoints.ymax-PlotPoints.ymin)/9)
		{
			g2d.draw(new Line2D.Double(0, h*(getHeight()/9), 5, h*(getHeight()/9)));
			g2d.drawString(df.format(k) + "", 7, (float)h*(getHeight()/9)+5);
			h += 1;
		}
		h = 0;
		//x scale
		for (double k = PlotPoints.a; k <= PlotPoints.b; k += (PlotPoints.b-PlotPoints.a)/9)
		{
			g2d.draw(new Line2D.Double(h*(getWidth()/9)+10, getHeight(), h*(getWidth()/9)+10, getHeight()-5));
			g2d.drawString(df.format(k) + "", (float)h*(getWidth()/9)+5, getHeight()-5);
			h += 1;
		}
		////////////////////////////////
		//Vertical axis
		if (PlotPoints.a <= 0 && PlotPoints.b >= 0)
		{
			g2d.draw(new Line2D.Double(-a*sx, 0, -PlotPoints.a*sx, getHeight()));
			g2d.drawString("0.00", 7, (float)((PlotPoints.ymax)*sy-1)+5);
		}
		//horizontal axis
		if ((int)PlotPoints.ymin <= 0 && (int)PlotPoints.ymax >= 0)
		{
			g2d.draw(new Line2D.Double(0, (PlotPoints.ymax)*sy-1, getWidth(), (PlotPoints.ymax)*sy-1));
			g2d.drawString("0.00",(float)((PlotPoints.ymax)*sy-1)+5, getHeight()-5);
		}
		//plot function, derivative and second derivative points
		for (int i = 0; i < (PlotPoints.x.length)-1; i ++)
		{
			g2d.setColor(Color.green);
			g2d.draw(new Line2D.Double((PlotPoints.x[i]-PlotPoints.a)*sx, (PlotPoints.ymax-PlotPoints.y[i])*sy, (PlotPoints.x[i+1]-PlotPoints.a)*sx, (PlotPoints.ymax-PlotPoints.y[i+1])*sy));
			g2d.setColor(Color.blue);
			g2d.draw(new Line2D.Double((PlotPoints.x2[i]-PlotPoints.a)*sx, (PlotPoints.ymax-PlotPoints.y2[i])*sy, (PlotPoints.x2[i+1]-PlotPoints.a)*sx, (PlotPoints.ymax-PlotPoints.y2[i+1])*sy));
			g2d.setColor(Color.red);
			g2d.draw(new Line2D.Double((PlotPoints.x3[i]-PlotPoints.a)*sx, (PlotPoints.ymax-PlotPoints.y3[i])*sy, (PlotPoints.x3[i+1]-PlotPoints.a)*sx, (PlotPoints.ymax-PlotPoints.y3[i+1])*sy));
		}
		
	}
	
	/**
	 * evalPoints fills coordinate arrays with points
	 * @param x - x coord array
	 * @param y - y coord array
	 * @param p - expression to call evalAt on
	 */
	public static void evalPoints(double x[], double y[], Poly p)
	{
		int j = 0; //count array index for coordinates
		//find and store 100 coordinates equally spaced between start/end
		for (double i = PlotPoints.a; i <= PlotPoints.b + ((PlotPoints.b-PlotPoints.a)/POINTS); i += ((PlotPoints.b-PlotPoints.a)/POINTS))
		{
			if (InputView.getEr() == false)
			{
				if (j!= x.length)
				{
					x[j] = i;
					y[j] = p.evalAt(i);
					if (y[j] > PlotPoints.ymax)
					{
						PlotPoints.ymax = y[j];
					}
					else if (y[j] < PlotPoints.ymin)
					{
						PlotPoints.ymin = y[j];
					}
					
					j ++;
				}
			}
		}
	}
	
	public static double getMin()
	{
		return PlotPoints.ymin;
	}
	
	public static double getMax()
	{
		return PlotPoints.ymax;
	}
	
}
