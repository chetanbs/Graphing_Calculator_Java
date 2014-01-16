/**
 * @author Lee Glendenning
 * id 120289190
 * glen9190@mylaurier.ca
 * @version Nov. 30, 2013
 * 
 */

package glen9190;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
@SuppressWarnings("serial")
public class NumericalView extends JPanel

{
	private LabelView lv = null;
	private InputView iv = null;
	private static JLabel error = new JLabel("");
	
	/**
	 * sets error message
	 * @param s - error message
	 */
	public static void setError(String s)
	{
		error.setText(s);
	}
	/**
	 * calls labelview, inputview and layoutview
	 */
	public NumericalView()
	{
		lv = new LabelView();
		iv = new InputView();
		this.layoutView();
	}
	/**
	 *adds add label/views
	 */
	public void layoutView()
	{
		this.setLayout(new BorderLayout());
		this.add(lv, BorderLayout.NORTH);
		this.add(iv, BorderLayout.WEST);
		this.add(error, BorderLayout.SOUTH);
	}
}
