import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Gui extends JFrame {
	public static JTextField jtf = new JTextField();

	public  Gui(JTextArea jta) {
		setTitle("consumer");
	    setSize(500,300);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
	    setLayout(new BorderLayout());
	    add(new JScrollPane(jta), BorderLayout.CENTER);
	    setVisible(true);

	}

	}

