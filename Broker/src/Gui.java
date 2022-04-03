import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Gui extends JFrame {
	
    String msg ;

	public Gui(JTextArea jta)
	{
		setTitle("Leader");
	    setSize(500,300);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
	    setLayout(new BorderLayout());
	    add(new JScrollPane(jta), BorderLayout.CENTER);
	    setVisible(true);
	}
}
