import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class Main {

	    //Serveur
	     /* String host = "127.0.0.7";
	      int port = 2345;
	      
	      TimeServer ts = new TimeServer(host, port);
	      ts.open();
	      
	      System.out.println("Serveur initialisé.");
	      
	      for(int i = 0; i < 5; i++){
	         Thread t = new Thread(new ClientConnexion(host, port));
	         t.start();*/
		   
		   
		  //Connection avec le jeton
		   /*Facebook f = new Facebook();
		   String accessToken = f.Token;
		   Connection c = new Connection(accessToken, f);*/
		 
		   
		    static Document document;
			static Element racine;
			static Publication publication= new Publication();

			public static void main(String[] args) {
				// On crée une instance de SAXBuilder
				SAXBuilder sxb = new SAXBuilder();
				try {
					// On crée un nouveau document JDOM avec en argument le fichier XML
					// Le parsing est terminé ;)
					document = sxb.build(new File("Donnees/Coca-Cola_température.xml"));
				} catch (Exception e) {
				}

				// On initialise un nouvel élément racine avec l'élément racine du
				// document.
				racine = document.getRootElement();

				// Méthode définie dans la partie 3.2. de cet article
				afficheALL();
			}

			public static void afficheALL() {
				// On crée une List contenant tous les noeuds "etudiant" de l'Element
				// racine
				ArrayList<Publication> List = new ArrayList<Publication>();
				
				List pub = racine.getChildren("PUBLICATION");
				// On crée un Iterator sur notre liste
				Iterator i = pub.iterator();
				while (i.hasNext()) {
					// On recrée l'Element courant à chaque tour de boucle afin de
					// pouvoir utiliser les méthodes propres aux Element comme :
					// sélectionner un nœud fils, modifier du texte, etc...
					Element courant = (Element) i.next();
					// On affiche le nom de l’élément courant
					publication.setAuteur(courant.getChild("AUTEUR").getText());
					//System.out.println(publication.auteur);
					publication.setNb_like(Integer.parseInt(courant.getChild("LIKES").getText()));
					//System.out.println(publication.nb_like);
					List comm = courant.getChildren("COMMENTAIRE");
					Iterator j = comm.iterator();
					int count = 0;

					while (j.hasNext()) {
						Element cour = (Element) j.next();
						count++;

					}
					publication.setNb_com(count);

					//System.out.println(publication.nb_com);
				
					Publication publi = new Publication(publication.auteur, publication.nb_com, publication.nb_like);
					List.add(publi);

				}
				System.out.println(List.get(0).auteur);
				System.out.println(List.get(0).nb_like);
				System.out.println(List.get(0).nb_com);
				System.out.println(List.get(1).auteur);
				System.out.println(List.get(1).nb_like);
				System.out.println(List.get(1).nb_com);
				System.out.println(List.get(2).auteur);
				System.out.println(List.get(2).nb_like);
				System.out.println(List.get(2).nb_com);
				System.out.println(List.size());
				
	

	    }  
	   
}
	   