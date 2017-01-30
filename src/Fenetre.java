package recupInfos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Fenetre extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {

		Fenetre fen = new Fenetre();
	}

	private JPanel container = new JPanel();
	
	private JButton valider = new JButton("OK");
	private JButton recherche = new JButton("Rechercher une page");
	private JMenuBar menuBar = new JMenuBar();
	private JMenu token = new JMenu("Token Utilisateur");
	private JMenuItem obtenirToken = new JMenuItem("Paramétrer le token");
	private JTextField jtfNom = new JTextField("");
	private JTextField jtfMot = new JTextField("");
	private JLabel motCle = new JLabel("Entrez le mot à rechercher");	
	private JLabel nomPage = new JLabel("Entrez le nom de la page");
	private Facebook f = new Facebook();
	
	public Fenetre() {
		this.setTitle("Simulation de la propagation des intérêts dans les réseaux sociaux");
		this.setSize(1300, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		this.setLocationRelativeTo(null);
		this.token.add(obtenirToken);
		JTabbedPane onglets = new JTabbedPane(SwingConstants.TOP);
		jtfNom.setPreferredSize(new Dimension(150, 30));
		jtfMot.setPreferredSize(new Dimension(150, 30));
		
		JPanel onglet1 = new JPanel();
		onglet1.add(nomPage);
		onglet1.add(jtfNom);
		onglet1.add(motCle);
		onglet1.add(jtfMot);
		onglet1.add(valider);
		onglet1.add(recherche);
		onglet1.setPreferredSize(new Dimension(this.getWidth() - 30, this.getHeight() - 100));
		onglets.addTab("Extraction", onglet1);

		JPanel onglet2 = new JPanel();
		JLabel titreOnglet2 = new JLabel("Analyse");
		onglet2.add(titreOnglet2);
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
		this.menuBar.add(token);
		obtenirToken.addActionListener(new BoutonListenerToken());
		valider.addActionListener(new BoutonListenerOk());
		recherche.addActionListener(new BoutonListenerRecherche());
		this.setJMenuBar(menuBar);
		this.setVisible(true);

	}
	
	class BoutonListenerOk implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			System.out.println("Nom : " + jtfNom.getText());
			System.out.println("Mot : " + jtfMot.getText());
			f.Extraction(jtfNom.getText(), jtfMot.getText());
		}
	}
	
	public class BoutonListenerToken implements ActionListener{
		public void actionPerformed (ActionEvent e) {
			Connection c = new Connection("", new Facebook());
		}
		
	}
	
	public class BoutonListenerRecherche implements ActionListener{
		public void actionPerformed (ActionEvent e) {
			URI uri = URI.create("https://facebook.com/");
			try {
				Desktop.getDesktop().browse(uri);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

}
