package recupInfos;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class XML {
	static Document document;
	static Element racine;
	static Publication publication = new Publication();

	public XML() {

	}

	public Object[][] XMLintoTAB(String nomPage, String motCle) {
		SAXBuilder sxb = new SAXBuilder();
		try {
			document = sxb.build(new File("Donnees/"+nomPage+"_"+motCle+".xml"));
		} catch (Exception e) {
		}
		racine = document.getRootElement();
		ArrayList<Publication> List = new ArrayList<Publication>();
		List pub = racine.getChildren("PUBLICATION");
		Iterator i = pub.iterator();
		while (i.hasNext()) {
			Element courant = (Element) i.next();
			publication.setAuteur(courant.getChild("AUTEUR").getText());
			publication.setNb_like(Integer.parseInt(courant.getChild("LIKES").getText()));
			List comm = courant.getChildren("COMMENTAIRE");
			Iterator j = comm.iterator();
			int count = 0;
			while (j.hasNext()) {
				Element cour = (Element) j.next();
				count++;

			}
			publication.setNb_com(count);
			Publication publi = new Publication(publication.auteur, publication.nb_com, publication.nb_like);
			List.add(publi);
		}
		Object Tab[][] = TableauRecap(List.size(), List);

		return Tab;
	}

	public static Object[][] TableauRecap(int size, ArrayList<Publication> list) {
		Object[][] tab = new Object[size + 1][3];
		tab[0][0] = "Auteur de la publication";
		tab[0][1] = "Nombre de j'aime";
		tab[0][2] = "Nombre de commentaires";
		for (int i = 0; i < size; i++) {
			tab[i + 1][0] = list.get(i).auteur;
			tab[i + 1][1] = list.get(i).nb_like;
			tab[i + 1][2] = list.get(i).nb_com;
		}
		return tab;
	}

}