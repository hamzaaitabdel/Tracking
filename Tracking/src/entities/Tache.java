package entities;

import java.sql.Date;



public class Tache {
	
	private int id_tache;
	private int id_respo;
	private int id_doc;
	private boolean valide;
	private String libelle;
	private String dateDebut;
	private String dateFin;
	private String d_nom_cl;
	private String d_type; 
	// 0 = a confirmer ; 1  = en cours ; 2	= terminé 
	private int d_numTacheEnCour;
	private int totalTache;
	private int maxTache;
	private boolean turn;
	private int duree;
	public int getId_tache() {
		return id_tache;
	}
	public void setId_tache(int id_tache) {
		this.id_tache = id_tache;
	}
	public int getId_respo() {
		return id_respo;
	}
	public void setId_respo(int id_respo) {
		this.id_respo = id_respo;
	}
	public int getId_doc() {
		return id_doc;
	}
	public void setId_doc(int id_doc) {
		this.id_doc = id_doc;
	}
	public boolean isValide() {
		return valide;
	}
	public void setValide(boolean valide) {
		this.valide = valide;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}
	public String getDateFin() {
		return dateFin;
	}
	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}
	public String getD_nom_cl() {
		return d_nom_cl;
	}
	public void setD_nom_cl(String d_nom_cl) {
		this.d_nom_cl = d_nom_cl;
	}
	public String getD_type() {
		return d_type;
	}
	public void setD_type(String d_type) {
		this.d_type = d_type;
	}
	public int getD_numTacheEnCour() {
		return d_numTacheEnCour;
	}
	public void setD_numTacheEnCour(int d_numTacheEnCour) {
		this.d_numTacheEnCour = d_numTacheEnCour;
	}
	public int getTotalTache() {
		return totalTache;
	}
	public void setTotalTache(int totalTache) {
		this.totalTache = totalTache;
	}
	public int getMaxTache() {
		return maxTache;
	}
	public void setMaxTache(int maxTache) {
		this.maxTache = maxTache;
	}
	public boolean isTurn() {
		return turn;
	}
	public void setTurn(boolean turn) {
		this.turn = turn;
	}
	public int getDuree() {
		return duree;
	}
	public void setDuree(int duree) {
		this.duree = duree;
	}
	@Override
	public String toString() {
		return "Tache [id_tache=" + id_tache + ", id_respo=" + id_respo
				+ ", id_doc=" + id_doc + ", valide=" + valide + ", libelle="
				+ libelle + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin
				+ ", d_nom_cl=" + d_nom_cl + ", d_type=" + d_type
				+ ", d_numTacheEnCour=" + d_numTacheEnCour + ", totalTache="
				+ totalTache + ", maxTache=" + maxTache + ", turn=" + turn
				+ ", duree=" + duree + "]";
	}
	public Tache(int id_tache, int id_respo, int id_doc, boolean valide,
			String libelle, String dateDebut, String dateFin, String d_nom_cl,
			String d_type, int d_numTacheEnCour, int totalTache, int maxTache,
			boolean turn, int duree) {
		super();
		this.id_tache = id_tache;
		this.id_respo = id_respo;
		this.id_doc = id_doc;
		this.valide = valide;
		this.libelle = libelle;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.d_nom_cl = d_nom_cl;
		this.d_type = d_type;
		this.d_numTacheEnCour = d_numTacheEnCour;
		this.totalTache = totalTache;
		this.maxTache = maxTache;
		this.turn = turn;
		this.duree = duree;
	}
	public Tache(int id_respo, int id_doc, boolean valide,
			String libelle, String dateDebut, String dateFin, String d_nom_cl,
			String d_type, int d_numTacheEnCour, int totalTache, int maxTache,
			boolean turn, int duree) {
		super();
		this.id_respo = id_respo;
		this.id_doc = id_doc;
		this.valide = valide;
		this.libelle = libelle;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.d_nom_cl = d_nom_cl;
		this.d_type = d_type;
		this.d_numTacheEnCour = d_numTacheEnCour;
		this.totalTache = totalTache;
		this.maxTache = maxTache;
		this.turn = turn;
		this.duree = duree;
	}
	
	public Tache(int id_tache, int id_respo, int id_doc, boolean valide,
			String libelle, String dateDebut, String dateFin, String d_nom_cl,
			String d_type, int d_numTacheEnCour,boolean turn, int duree) {
		super();
		this.id_tache = id_tache;
		this.id_respo = id_respo;
		this.id_doc = id_doc;
		this.valide = valide;
		this.libelle = libelle;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.d_nom_cl = d_nom_cl;
		this.d_type = d_type;
		this.d_numTacheEnCour = d_numTacheEnCour;
		this.turn = turn;
		this.duree = duree;
	}
	public Tache(int id_respo, int id_doc, boolean valide,
			String libelle, String dateDebut, String dateFin, String d_nom_cl,
			String d_type, int d_numTacheEnCour,boolean turn, int duree) {
		super();
		this.id_respo = id_respo;
		this.id_doc = id_doc;
		this.valide = valide;
		this.libelle = libelle;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.d_nom_cl = d_nom_cl;
		this.d_type = d_type;
		this.d_numTacheEnCour = d_numTacheEnCour;
		this.turn = turn;
		this.duree = duree;
	}
	public Tache() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}
