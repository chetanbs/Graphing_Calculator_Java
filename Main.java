/**
 * @author Lee Glendenning
 * id 120289190
 * glen9190@mylaurier.ca
 * @version Nov. 30, 2013
 * 
 */

package glen9190;

import javax.swing.JFrame;
import javax.swing.JPanel;
@SuppressWarnings("serial")
public class Main extends JPanel {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame panel = new JFrame("Plotter");
		NumericalView view = new NumericalView();
		panel.setContentPane(view);
		panel.setSize(430,100);
		panel.setLocation(0,0);
		panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setVisible(true);

	}

}
