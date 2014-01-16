/**
 * @author Lee Glendenning
 * id 120289190
 * glen9190@mylaurier.ca
 * @version Nov. 30, 2013
 * 
 */

package glen9190;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class InputView extends JPanel {
	private static boolean er = false;
	private JButton plot = new JButton("Plot");
	private JButton out = new JButton("Zoom out");
	private JButton in = new JButton("Zoom in");
	private static JTextField expression = new JTextField(7);
	private static JTextField var = new JTextField(7);
	private static JTextField start = new JTextField(7);
	private static JTextField end = new JTextField(7);
	private static JLabel coords = new JLabel("(0,0)");
	
	/**
	 * 
	 * inner button listener class
	 *
	 */
	private class ButtonListener extends JPanel implements ActionListener
	{
		private boolean firstPass = true;
		private int zoom = 0;
		public ButtonListener()
		{
		}
		public ButtonListener(int value)
        {
			if (firstPass == true)
	        {
				in.setEnabled(false);
				out.setEnabled(false);
	        }

			zoom = value;
			
            
        }
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			try{
				
				if (zoom == 1)
				{
					InputView.in();
				}
				else if (zoom == 2)
				{
					InputView.out();
				}
				
			}catch(Exception err)
			{
				NumericalView.setError("can't zoom");
			}
	        
			try{
			double a = InputView.getStart();
			double b = InputView.getEnd();
			
			if (!InputView.expression.getText().equals("0") && InputView.getStart() < InputView.getEnd() && InputView.var.getText().length() == 1 && !InputView.isNumeric(InputView.var.getText()))
			{
				String exp = InputView.getExpression();
				String variable = InputView.getVar();
				InputView.er = false;
				PlotPoints p = new PlotPoints(exp, variable, a ,b);
				if (InputView.er == false)
				{
					if (firstPass != false)
					{
						firstPass = false;
						in.setEnabled(true);
						out.setEnabled(true);
					}
					NumericalView.setError("");
					JFrame panel = new JFrame("Plotter");
					MouseHandler handler = new MouseHandler(); 
					panel.addMouseListener( handler ); 
				    panel.addMouseMotionListener( handler ); 
					panel.setContentPane(p);
					panel.setSize(600,600);
					panel.setLocation(0, 100);
					panel.setBackground(Color.WHITE);
					panel.setResizable(true);
					panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					panel.setVisible(true);
				}
			}
			else
			{
				NumericalView.setError("input(s) invalid");
			}
			}
			catch(Exception err){
				NumericalView.setError("input(s) invalid");
			}
	        
		}
	}
	/**
	 * 
	 * inner mouse handler class
	 *
	 */
	private class MouseHandler implements MouseListener, MouseMotionListener 
    {
		// MouseListener event handlers
	      // handle event when mouse released immediately after press
	      public void mouseClicked( MouseEvent event ){}

	      // handle event when mouse pressed
	      public void mousePressed( MouseEvent event ){}

	      // handle event when mouse released after dragging
	      public void mouseReleased( MouseEvent event ){}

	      // handle event when mouse enters area
	      public void mouseEntered( MouseEvent event ){}

	      // handle event when mouse exits area
	      public void mouseExited( MouseEvent event )
	      {
	    	  coords.setText( "Outside" );
	      }

	      // MouseMotionListener event handlers
	      // handle event when user drags mouse with button pressed
	      public void mouseDragged( MouseEvent event ){}

	      // handle event when user moves mouse
	      public void mouseMoved( MouseEvent event )
	      {
	    	  double b = InputView.getEnd();
	  		  double a = InputView.getStart();
	    	  DecimalFormat df = new DecimalFormat("#.##");
	    	  double sx = 594/(b-a);
	    	  double sy = 572/(PlotPoints.getMax()-PlotPoints.getMin());
	    	  coords.setText( "("+ df.format((event.getX()/sx)+a-.02) + ", " + df.format((.17 + PlotPoints.getMax()) - (event.getY()/sy)) + ")" );
	      }
    }
	
	/**
	 * inputview calls layoutview and registerbuttons
	 */
	public InputView()
	{
		this.layoutView();
		this.registerButtons();
	}
	/**
	 * layoutview adds buttons and text fields
	 */
	public void layoutView()
	{
		this.setLayout(new GridLayout(2,5));
		this.add(expression);
		this.add(var);
		this.add(start);
		this.add(end);
		this.add(this.plot);
		this.add(InputView.coords);
		this.add(this.in);
		this.add(this.out);
	}
	/**
	 * registerbuttons registers button values
	 */
	public void registerButtons()
	{
		plot.addActionListener(new ButtonListener());
		in.addActionListener(new ButtonListener(1));
		out.addActionListener(new ButtonListener(2));
		
	}
	/**
	 * 
	 * @return - string expression
	 */
	public static String getExpression()
	{
		return expression.getText();
				
	}
	/**
	 * 
	 * @return - double initial x coordinate
	 */
	public static double getStart()
	{
		return Double.parseDouble(start.getText());
				
	}
	/**
	 * 
	 * @return - double last x coordinate
	 */
	public static double getEnd()
	{ 
		return Double.parseDouble(end.getText());
				
	}
	/**
	 * 
	 * @return - string variable used in expression
	 */
	public static String getVar()
	{
		return var.getText();
				
	}
	/**
	 * 
	 * @param str - string to check
	 * @return boolean
	 */
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	/**
	 * sets error
	 * @param b
	 */
	public static void setEr(boolean b)
	{
		InputView.er = b;
	}
	/**
	 * gets error
	 * @return - boolean
	 */
	public static boolean getEr()
	{
		boolean b = InputView.er;
		return b;
	}
	/**
	 * zoom in button
	 */
	public static void in()
	{
		double b = InputView.getEnd();
		double a = InputView.getStart();
		if (b - a > 2)
		{
			InputView.start.setText(a + 1 + "");
			InputView.end.setText(b - 1 + "");
		}
		else
		{
			NumericalView.setError("can't zoom in anymore");
		}
	}
	/**
	 * zoom out button
	 */
	public static void out()
	{
		double b = InputView.getEnd();
		double a = InputView.getStart();
		InputView.start.setText(a - 1 + "");
		InputView.end.setText(b + 1 + "");
	}
}
