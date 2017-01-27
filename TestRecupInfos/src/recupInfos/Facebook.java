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
		String accessToken = ""; // Lien
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
							System.out.println("Message numéro " + count + " : " + comment.getMessage());
							System.out.println("Mentions J'aime : " + comment.getLikeCount());
							System.out.println("fb.com/" + comment.getId());
							System.out.println("");
							count++;
						}
					}
				}
			}
		}

	}

}