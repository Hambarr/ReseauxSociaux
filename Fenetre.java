package recupInfos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Fenetre extends JFrame {

	private JButton bExtraction = new JButton("Extraction");
	private JButton bSimulation = new JButton("Analyse et Simulation");
	private JPanel container = new JPanel();

	public Fenetre() {
		this.setTitle("Outil de simulation");
		this.setSize(270, 65);
		this.setResizable(false);
		this.setAlwaysOnTop(false);
		this.setLocationRelativeTo(null);
		container.add(bExtraction);
		container.add(bSimulation);
		bExtraction.addActionListener(new BoutonListenerExtraction());
		//bSimulation.addActionListener(new BoutonListenerSimulation());
		this.setContentPane(container);
		this.setVisible(true);
	}

	public static void main(String[] args) {

		Fenetre fen = new Fenetre();
	}

	class BoutonListenerExtraction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
				Extraction ext = new Extraction();
				setVisible(false);
		}
	}
	
	/*class BoutonListenerSimulation implements ActionListener {
		public void actionPerformed(ActionEvent e) {
				 = new Extraction();
				 setVisible(false);
		}
	}*/
}
