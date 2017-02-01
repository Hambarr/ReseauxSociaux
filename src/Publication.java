package recupInfos;

public class Publication {
	
	public Publication() {
	}
	public Publication(String auteur, int nb_com, int nb_like) {
		super();
		this.auteur = auteur;
		this.nb_com = nb_com;
		this.nb_like = nb_like;
	}
	String auteur;
	int nb_com;
	int nb_like;
	public  String getAuteur() {
		return auteur;
	}
	public  void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	
	public int getNb_com() {
		return nb_com;
	}
	public void setNb_com(int nb_com) {
		this.nb_com = nb_com;
	}
	public int getNb_like() {
		return nb_like;
	}
	public void setNb_like(int nb_like) {
		this.nb_like = nb_like;
	}
}