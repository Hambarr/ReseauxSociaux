package recupInfos;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class Main {

	static Document document;
	static Element racine;
	static ArrayList<Publication> ListPub = new ArrayList<Publication>();
	static Publication publication= new Publication();

	public static void main(String[] args) {
		// On cr�e une instance de SAXBuilder
		SAXBuilder sxb = new SAXBuilder();
		try {
			// On cr�e un nouveau document JDOM avec en argument le fichier XML
			// Le parsing est termin� ;)
			document = sxb.build(new File("Donnees/Coca-Cola_temp�rature.xml"));
		} catch (Exception e) {
		}

		// On initialise un nouvel �l�ment racine avec l'�l�ment racine du
		// document.
		racine = document.getRootElement();

		// M�thode d�finie dans la partie 3.2. de cet article
		afficheALL();
	}

	public static void afficheALL() {
		// On cr�e une List contenant tous les noeuds "etudiant" de l'Element
		// racine
		List pub = racine.getChildren("PUBLICATION");
		// On cr�e un Iterator sur notre liste
		Iterator i = pub.iterator();
		while (i.hasNext()) {
			// On recr�e l'Element courant � chaque tour de boucle afin de
			// pouvoir utiliser les m�thodes propres aux Element comme :
			// s�lectionner un n�ud fils, modifier du texte, etc...
			Element courant = (Element) i.next();
			// On affiche le nom de l��l�ment courant
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
			ListPub.add(publi);

		}
		System.out.println(ListPub.get(0).auteur);
		System.out.println(ListPub.get(0).nb_like);
		System.out.println(ListPub.get(0).nb_com);
		System.out.println(ListPub.get(1).auteur);
		System.out.println(ListPub.get(1).nb_like);
		System.out.println(ListPub.get(1).nb_com);
		System.out.println(ListPub.get(2).auteur);
		System.out.println(ListPub.get(2).nb_like);
		System.out.println(ListPub.get(2).nb_com);
	}
}
