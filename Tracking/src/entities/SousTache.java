package entities;

public class SousTache {
	private int id_ST;
	private int id_tache;
	private String libelle;
	
	
	public int getId_ST() {
		return id_ST;
	}
	public void setId_ST(int id_ST) {
		this.id_ST = id_ST;
	}
	public int getId_tache() {
		return id_tache;
	}
	public void setId_tache(int id_tache) {
		this.id_tache = id_tache;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	@Override
	public String toString() {
		return "SousTache [id_ST=" + id_ST + ", id_tache=" + id_tache
				+ ", libelle=" + libelle + "]";
	}
	public SousTache(int id_ST, int id_tache, String libelle) {
		super();
		this.id_ST = id_ST;
		this.id_tache = id_tache;
		this.libelle = libelle;
	}
	
	public SousTache(int id_tache, String libelle) {
		super();
		this.id_tache = id_tache;
		this.libelle = libelle;
	}
	public SousTache() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
