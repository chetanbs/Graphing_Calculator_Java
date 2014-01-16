/**
 * @author Lee Glendenning
 * id 120289190
 * glen9190@mylaurier.ca
 * @version Nov. 30, 2013
 * 
 */

package glen9190;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class LabelView extends JPanel{
	private JLabel exp = new JLabel("Function:");
	private JLabel v = new JLabel("Var:");
	private JLabel s = new JLabel("Start:");
	private JLabel e = new JLabel("End:");
	/**
	 * calls layoutview
	 */
	public LabelView()
	{
		this.layoutView();
	}
	/**
	 * adds labels
	 */
	public void layoutView()
	{
		this.setLayout(new GridLayout(1,4));
		this.add(exp);
		this.add(v);
		this.add(s);
		this.add(e);
	}
}
