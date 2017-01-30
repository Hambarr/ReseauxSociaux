import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;

public class Main {

	   public static void main(String[] args) {
	    //Serveur
	     /* String host = "127.0.0.7";
	      int port = 2345;
	      
	      TimeServer ts = new TimeServer(host, port);
	      ts.open();
	      
	      System.out.println("Serveur initialisé.");
	      
	      for(int i = 0; i < 5; i++){
	         Thread t = new Thread(new ClientConnexion(host, port));
	         t.start();*/
		   Facebook f = new Facebook();
		   String accessToken = f.Token;
		   Connection c = new Connection(accessToken, f);
		   
		   
	    }
	   
	   
}
	  