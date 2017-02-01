package recupInfos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class AnalyseSimulation extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {

		AnalyseSimulation ana = new AnalyseSimulation();
	}

	private JPanel container = new JPanel();

	public AnalyseSimulation() {
		this.setTitle("Analyse et Simulation");
		this.setSize(1300, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		JTabbedPane onglets = new JTabbedPane();

		JPanel onglet2 = new JPanel();
		JLabel titreOnglet2 = new JLabel("Analyse");
		onglet2.add(titreOnglet2);
		onglet2.setPreferredSize(new Dimension(this.getWidth()-20, this.getHeight() -65));
		onglets.addTab("Analyse", onglet2);
		JPanel onglet3 = new JPanel();
		JLabel titreOnglet3 = new JLabel("Simulation");
		onglet3.add(titreOnglet3);
		onglets.addTab("Simulation", onglet3);

		onglets.setOpaque(true);

		container.setBackground(Color.blue);
		JPanel top = new JPanel();
		top.add(onglets);
		top.setLayout(new FlowLayout(FlowLayout.LEFT));
		container.add(top, BorderLayout.NORTH);
		this.setContentPane(top);
		this.setVisible(true);

	}
}