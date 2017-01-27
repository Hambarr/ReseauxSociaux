import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Connection extends JFrame {
	private JPanel container = new JPanel();
	private JTextField jtf = new JTextField("");
	private JLabel label = new JLabel("Ajouter votre Token d'accès : ");
	private JButton b = new JButton("OK");

	public Connection(String token) { 
		if (token != "") {
			this.jtf = new JTextField(token);
		}
		this.setTitle("Connection");
		this.setSize(400, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		this.setLocationRelativeTo(null);
		container.setBackground(Color.blue);
		container.setLayout(new BorderLayout());
		JPanel top = new JPanel();
		Font police = new Font("Arial", Font.BOLD, 14);
		jtf.setFont(police);
		jtf.setPreferredSize(new Dimension(150, 30));
		jtf.setForeground(Color.BLUE);
		b.addActionListener(new BoutonListener());
		top.add(label);
		top.add(jtf);
		top.add(b);
	    container.add(top, BorderLayout.NORTH);
		this.setContentPane(top);
		this.setVisible(true);

	}

	class BoutonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Token : " + jtf.getText());

		}
	}
}