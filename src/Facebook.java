import java.util.List;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.Parameter;
import com.restfb.types.Comment;
import com.restfb.types.Page;
import com.restfb.types.Post;

public class Facebook {
	static String Token;
	
	public static void setToken (String token){
		Token = token;
	}
	public static String getToken (){
		return Token;
	}
	
	public Facebook (){
		Token = "";
	}
	
	public static boolean Test(){
		FacebookClient fbClient = new DefaultFacebookClient(Token);
		//Voir pour un Test qui retourne true ou false
		return fbClient.debugToken(Token).isValid();
	}
	
	
	//Permet d'avoir la liste des pages aimés par l'utilisateur
	public static void main(String[] args) {
		//https://developers.facebook.com/tools/explorer/
		
		//Lien d'accés
		String accessToken = "EAACEdEose0cBAFDhKe9O7sz6H0P0bHsFLoxPubpis5hol6FnGv8hL74zeh3bjc6nzAZC12eU9hRAn65JFkeaFeuxpAseUaj76gGGZCyNeS6EDcztgswfZBW04i9GvWHzAKECLGgFdzQsnoboKyVvwYum30rd1VreyeqFshiNgZDZD"; 
		
		FacebookClient fbClient = new DefaultFacebookClient(accessToken);
		//Connection<Page> result = fbClient.fetchConnection("me", Page.class);
		//User user = fbClient.fetchObject("me", User.class);
		
		//Avoir accès aux informations d'une page
		/*System.out.println("* Page Cocacola * ");
	    Page page = fbClient.fetchObject("cocacola", Page.class);
	    System.out.println("Birthday: " + page.getBirthday());
	    System.out.println("Likes: " + page.getLikes());
	    System.out.println("ID: " + page.getId());
	    System.out.println("Publication: " + page.getDescription());*/

	  
	    
       Page page2 = fbClient.fetchObject("cocacolafrance", Page.class);
       Connection<Post> postFeed = fbClient.fetchConnection(page2.getId() + "/feed", Post.class);
              
        //Publication + Commentaire
		for (List<Post> postPage : postFeed) {
            for (Post aPost : postPage) {
                if (aPost.getMessage()!=null && aPost.getMessage().contains("papacocas")) {
                    System.out.println(aPost.getFrom().getName());
                    System.out.println("-->" + aPost.getMessage());
                    System.out.println("fb.com/" + aPost.getId());
                    System.out.println("Date : " + aPost.getUpdatedTime());
                    Post.Likes likes = fbClient.fetchObject(aPost.getId() + "/likes", Post.Likes.class,Parameter.with("summary", 1), Parameter.with("limit", 0));
                    long likesTotalCount = likes.getTotalCount();
                    System.out.println("Mentions J'aime : " + likesTotalCount);
                    
                    Connection<Comment> allComments = fbClient.fetchConnection(aPost.getId()+"/comments", Comment.class);
                    for(List<Comment> postcomments : allComments){
                        for (Comment comment : postcomments){
                            System.out.println("nb like : " + comment.getLikeCount()); //nb aime 
                            System.out.println("message : " + comment.getMessage());  	//message
                            System.out.println("ID : " + comment.getId());		//id
                        }
                    }
                }
            }
        }


	    /*
	    //Effectuer la recherche de profil
	    
	    
	    Connection<User> publicSearch = fbClient.fetchConnection("search", User.class, Parameter.with("q", "Dylan"), Parameter.with("type", "user"));
	    for(int i=0;i<publicSearch.getData().size();i++){
	    	System.out.println("Dylan de ID " + publicSearch.getData().get(i).getId() 
	    			+ " se nomme " + publicSearch.getData().get(i).getName()+" : "
	    			+ publicSearch.getData().get(i));
	    }
	    
	    //Effectuer la recherche de page
	    
	    Connection<Page> publicSearch2 = fbClient.fetchConnection("search", Page.class, Parameter.with("q", "Cocacola"), Parameter.with("type", "page"));
	    for(int i=0;i<publicSearch.getData().size();i++){
	    	System.out.println("Cocacola de ID " + publicSearch2.getData().get(i).getId() 
	    			+ " se nomme " + publicSearch2.getData().get(i).getName()+" : "
	    			+ publicSearch2.getData().get(i));
	    }
	    
	    */
	    
		//System.out.println("User name: " + user.getName());
		
	    
	    //Mes Likes
		/*int counter = 0;
		Connection<Page> result = fbClient.fetchConnection("me/likes", Page.class);
		
		System.out.println("\n\n\n"+result);
				
		for(List<Page> feedPage : result){
			for(Page page2 : feedPage){
				System.out.println(page2.getName());
				System.out.println("fb.com/"+page2.getId());
				counter++;

			}
		}
		System.out.println("Results: "+counter);*/
		
		//Mes Amis
		/*Connection<User> myFriends = fbClient.fetchConnection("me/friends", User.class);
		System.out.println("Count of my friends :" + myFriends.getData().size());*/
		
		//Récupérer le contenu HTML de ma page d'ami Facebook
		/*HttpURLConnection conn;
        try {
            conn = (HttpURLConnection) new URL("https://www.facebook.com/me/friends").openConnection();
            conn.connect();
            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
            byte[] bytes = new byte[1024];
            int tmp ;
            while( (tmp = bis.read(bytes) ) != -1 ) {
                String chaine = new String(bytes,0,tmp);
                System.out.print(chaine);
            }

                conn.disconnect();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
	   
	}

}
