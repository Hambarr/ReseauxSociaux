import java.util.Date;

public class Publication {
	public Publication(String auteur, Date date, int nb_com, int nb_like) {
		super();
		this.auteur = auteur;
		this.date = date;
		this.nb_com = nb_com;
		this.nb_like = nb_like;
	}
	String auteur;
	Date date;
	int nb_com;
	int nb_like;
}
