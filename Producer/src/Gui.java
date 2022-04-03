import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Gui extends JFrame {
	public JTextField jtf = new JTextField();
    public DataOutputStream x ;
	public Gui(DataOutputStream toServer) {
		x= toServer;
		setTitle("producer");
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setVisible(true);
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		p.add(jtf, BorderLayout.CENTER);
		jtf.setHorizontalAlignment(JTextField.RIGHT);

		setLayout(new BorderLayout());
		add(p, BorderLayout.NORTH);
		jtf.addActionListener(new TextFieldListener());

	}

	private class TextFieldListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			try {
				String msg = jtf.getText().trim();
				x.writeUTF(msg);
				x.flush();
			} catch (IOException ex) {
				System.err.println(ex);
			}
		}
	}

}
