package recupInfos;

import java.util.List;

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
		String accessToken = "EAACEdEose0cBAAAwZAn87LzMeFxigLUsJlo8rPPvRzcIexTiZBLFmOLZCcdZCZALXz0YnbotNTz9ZCcZBrk7DSbDaNjQYrfBC8ZC5XOZAGoEgrDxc03impUCsioy32GE4s935DsB4LrMnZArOjV08MlGQQTH4yGhwTtQFZAA7vrmGP3WwZDZD"; // Lien
																																																					// d'accés

		FacebookClient fbClient = new DefaultFacebookClient(accessToken);

		Page page = fbClient.fetchObject("cocacolafrance", Page.class);
		Connection<Post> postFeed = fbClient.fetchConnection(page.getId() + "/feed", Post.class);

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

					Connection<Comment> allComments = fbClient.fetchConnection(aPost.getId() + "/comments",
							Comment.class);
					for (List<Comment> postcomments : allComments) {
						int count = 1;
						for (Comment comment : postcomments) {
							System.out.println("Message numéro " + count +" : " + comment.getMessage());
							System.out.println("Mentions J'aime : " + comment.getLikeCount());
							System.out.println("fb.com/" + comment.getId());
							System.out.println("");
							count++;
						}
					}
				}
			}
		}

		// Connection<User> result = fbClient.fetchConnection("me", User.class);
		/*
		 * int counter = 0;
		 * 
		 * User user = fbClient.fetchObject("me", User.class);
		 * System.out.println("Compte : " + user.getName());
		 * 
		 * // Avoir accès aux informations d'une page
		 * System.out.println("* Page Cocacola * "); Page page =
		 * fbClient.fetchObject("cocacola", Page.class);
		 * System.out.println("Birthday: " + page); System.out.println("Likes: "
		 * + page.getLikes()); System.out.println("ID: " + page.getId());
		 * //System.out.println("Publication: " + page.);
		 * 
		 * // Effectuer la recherche de page
		 * 
		 * /*Connection<Page> publicSearch2 = fbClient.fetchConnection("search",
		 * Page.class, Parameter.with("q", "Cocacola"), Parameter.with("type",
		 * "page")); for (int i = 0; i < publicSearch2.getData().size(); i++) {
		 * System.out.println("Cocacola de ID " +
		 * publicSearch2.getData().get(i).getId() + " se nomme " +
		 * publicSearch2.getData().get(i).getName() + " : " +
		 * publicSearch2.getData().get(i)); }
		 */

	}

}