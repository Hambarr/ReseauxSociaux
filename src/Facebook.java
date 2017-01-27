package recupInfos;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.Comment;
import com.restfb.types.Page;
import com.restfb.types.Post;

public class Facebook {

	// Permet d'avoir la liste des pages aimés par l'utilisateur
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String accessToken = ""; // Lien
		boolean stop = false;
		
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();// d'accés

			Element rootElement = doc.createElement("PAGE");
			doc.appendChild(rootElement);

			FacebookClient fbClient = new DefaultFacebookClient(accessToken);

			Page page = fbClient.fetchObject("cocacolafrance", Page.class);
			Connection<Post> postFeed = fbClient.fetchConnection(page.getId() + "/feed", Post.class);

			Element nom = doc.createElement("NOM");
			nom.appendChild(doc.createTextNode(page.getName()));
			rootElement.appendChild(nom);
			
			Element idpage = doc.createElement("ID");
			idpage.appendChild(doc.createTextNode(page.getId()));
			rootElement.appendChild(idpage);
			
			Element publication = doc.createElement("PUBLICATION");
			rootElement.appendChild(publication);

			for (List<Post> postPage : postFeed) {
				for (Post aPost : postPage) {
					if (aPost.getMessage() != null && aPost.getMessage().contains("papacocas")) {
						System.out.println(aPost.getFrom().getName());
						System.out.println("-->" + aPost.getMessage());
						System.out.println("fb.com/" + aPost.getId());
						System.out.println("Date : " + aPost.getUpdatedTime());
						Post.Likes likes = fbClient.fetchObject(aPost.getId() + "/likes", Post.Likes.class,
								Parameter.with("summary", 1), Parameter.with("limit", 0));
						long likesTotalCount = likes.getTotalCount();
						System.out.println("Mentions J'aime sur la publication : " + likesTotalCount);
						System.out.println("");

						Element posteur = doc.createElement("POSTEUR");
						posteur.appendChild(doc.createTextNode(aPost.getFrom().getName()));
						publication.appendChild(posteur);
						
						Element id = doc.createElement("ID");
						id.appendChild(doc.createTextNode(aPost.getId()));
						publication.appendChild(id);
						
						Element message = doc.createElement("MESSAGE");
						message.appendChild(doc.createTextNode(aPost.getMessage()));
						publication.appendChild(message);
						
						Element date = doc.createElement("DATE");
						date.appendChild(doc.createTextNode(aPost.getUpdatedTime().toString()));
						publication.appendChild(date);
						
						Element like = doc.createElement("LIKES");
						like.appendChild(doc.createTextNode(String.valueOf(likes.getTotalCount())));
						publication.appendChild(like);

						Connection<Comment> allComments = fbClient.fetchConnection(aPost.getId() + "/comments",
								Comment.class);
						for (List<Comment> postcomments : allComments) {
							int count = 1;
							for (Comment comment : postcomments) {
								System.out.println("Message numéro " + count + " : " + comment.getMessage());
								System.out.println("Mentions J'aime : " + comment.getLikeCount());
								System.out.println("fb.com/" + comment.getId());
								System.out.println("");

								Element commentaire = doc.createElement("COMMENTAIRE");
								publication.appendChild(commentaire);
								Element idcommentaire = doc.createElement("ID");
								idcommentaire.appendChild(doc.createTextNode(comment.getId()));
								commentaire.appendChild(idcommentaire);
								Element likecomm = doc.createElement("LIKES");
								likecomm.appendChild(doc.createTextNode(String.valueOf(comment.getLikeCount())));
								commentaire.appendChild(likecomm);
								Element messagecomm = doc.createElement("MESSAGE");
								messagecomm.appendChild(doc.createTextNode(comment.getMessage()));
								commentaire.appendChild(messagecomm);

								count++;
							}
						}
						break;
					}
				}
			}

			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("Extraction.xml"));
			transformer.transform(source, result);
			System.out.println("Le fichier est sauvegardé !");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}

}