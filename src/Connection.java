import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Connection extends JFrame {
	private JPanel container = new JPanel();
	private JTextField jtf = new JTextField("");
	private JLabel label = new JLabel("Ajouter votre jeton d'accès : ");
	private JLabel labeltest = new JLabel("");
	private JButton b = new JButton("OK");
	private JButton b2 = new JButton("Obtenir un jeton");
	private JButton b3 = new JButton("Test");
	private Facebook f;

	public Connection(String token, Facebook facebook) { 
		if (token != "") {
			this.jtf = new JTextField(token);
		}
		f=facebook;
		this.setTitle("Connection à Facebook");
		this.setSize(400, 100);
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
		b.addActionListener(new BoutonListenerConnection());
		b2.addActionListener(new BoutonListenerJeton());
		b3.addActionListener(new BoutonListenerTest());
		labeltest.setFont(police);
		labeltest.setForeground(Color.BLUE);
		
		top.add(label);
		top.add(jtf);
		top.add(b);
		top.add(b2);
		top.add(b3);
		top.add(labeltest);

	    container.add(top, BorderLayout.NORTH);
		this.setContentPane(top);
		this.setVisible(true);
	}
	
	class BoutonListenerTest implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Test : ");
			f.setToken(jtf.getText());
			if(f.Test()){
				labeltest.setForeground(Color.GREEN);
				labeltest.setText("Ok");
			}
			else{
				labeltest.setForeground(Color.RED);
				labeltest.setText("Erreur");
			}
				
				
		}
	}

	class BoutonListenerConnection implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Token : " + jtf.getText());
			f.setToken(jtf.getText());
		}
	}
	
	class BoutonListenerJeton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Utilisateur envoyé vers https://developers.facebook.com/tools/explorer/");
			URI uri = URI.create("https://developers.facebook.com/tools/explorer/");
			try {
				Desktop.getDesktop().browse(uri);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}