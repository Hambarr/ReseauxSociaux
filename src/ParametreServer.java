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

public class ParametreServer extends JFrame {
	private JPanel container = new JPanel();
	private JTextField jtf = new JTextField("0");
	private JTextField jtf2 = new JTextField("");
	private JLabel label = new JLabel("Port : ");
	private JLabel label2 = new JLabel("Host : ");
	private JButton b = new JButton("OK");
	int pPort;
	String pHost;

	public ParametreServer() {
		this.setTitle("Parametre du serveur");
		this.setSize(470, 68);
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
		jtf2.setFont(police);
		jtf2.setPreferredSize(new Dimension(150, 30));
		jtf2.setForeground(Color.BLUE);
		b.addActionListener(new BoutonListener());
		top.add(label);
		top.add(jtf);
		top.add(label2);
		top.add(jtf2);
		top.add(b);
		container.add(top, BorderLayout.NORTH);
		this.setContentPane(top);
		this.setVisible(true);
	}

	class BoutonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Port : " + jtf.getText());
			System.out.println("Host : " + jtf2.getText());
			pPort = Integer.parseInt(jtf.getText());
			pHost = jtf2.getText();

		}
	}
}
