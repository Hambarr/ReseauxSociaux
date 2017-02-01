import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
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
	private JMenu token = new JMenu("Connexion Facebook");
	private JMenuItem obtenirToken = new JMenuItem("Paramétrer le token");
	private JTextField jtfNom = new JTextField("");
	private JTextField jtfMot = new JTextField("");
	private JLabel motCle = new JLabel("Entrez le mot à rechercher");	
	private JLabel nomPage = new JLabel("Entrez le nom de la page");
	private Facebook f = new Facebook();
	private Font police = new Font("Arial", Font.BOLD, 14);
	private JLabel labelVerification = new JLabel("");
	Object[][] donnees_vide={{"Auteur de la publication", "Nombre de j'aime", "Nombre de commentaires"},
            {"", "", ""},
            {"", "", ""},
            {"", "", ""},
            {"", "", ""},
            {"", "", ""},
            {"", "", ""},};

    String[] entetes = {"Auteur de la publication", "Nombre de j'aime", "Nombre de commentaires"};
    JTable tableau = new JTable(donnees_vide, entetes);
	JPanel onglet1 = new JPanel();

	
	public Fenetre() {
		this.setTitle("Simulation de la propagation des intérêts dans les réseaux sociaux");
		this.setSize(1300, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.token.add(obtenirToken);
		JTabbedPane onglets = new JTabbedPane();
		jtfNom.setPreferredSize(new Dimension(150, 30));
		jtfMot.setPreferredSize(new Dimension(150, 30));
		

		
		onglet1.add(nomPage);
		onglet1.add(jtfNom);
		onglet1.add(motCle);
		onglet1.add(jtfMot);
		onglet1.add(valider);
		onglet1.add(recherche);
		onglet1.add(labelVerification);	
		onglet1.setPreferredSize(new Dimension(this.getWidth()-88, this.getHeight() +5000));
		
        JScrollPane scroll = new JScrollPane(onglet1);
        scroll.setPreferredSize(new Dimension(this.getWidth()-20, this.getHeight() -88));
       	
		onglets.addTab("Extraction", scroll);
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
			if(f.Extraction(jtfNom.getText(), jtfMot.getText())){
				labelVerification.setForeground(Color.GREEN);
				labelVerification.setText("Données extraites avec succès!");
				try{
					XML xml = new XML();
					Object[][] donnees=xml.XMLintoTAB(jtfNom.getText(),jtfMot.getText());
					tableau=new JTable(donnees, entetes);
					tableau.getColumnModel().getColumn(0).setPreferredWidth(150);
					tableau.getColumnModel().getColumn(1).setPreferredWidth(150);
					tableau.getColumnModel().getColumn(2).setPreferredWidth(150);
			        onglet1.add(tableau);
				}
				catch(Exception ex){
					labelVerification.setForeground(Color.GREEN);
					labelVerification.setText("Données extraites avec succès! Trop de données pour un affichage.");
				}
				
			}
			else{
				labelVerification.setForeground(Color.RED);
				labelVerification.setText("Erreur durant l'extraction.");
			}
		}
	}
	
	public class BoutonListenerToken implements ActionListener{
		public void actionPerformed (ActionEvent e) {
			Connection c = new Connection(f.Token, f);
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