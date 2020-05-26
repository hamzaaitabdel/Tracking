package entities;




public class Dossier {
	private int id_doc; 
	private String nom_cl; 
	private String type; 
	private int numTacheEnCour;
	
	// date debut premiere tache du dossier
	private String dateDebut;
	// date fin derniere tache du dossier
	private String dateFin;
	private int totalTache;
	private int maxTache;
	private int dureeTotal;
	private String respo;
	
	
	public int getDureeTotal() {
		return dureeTotal;
	}
	public void setDureeTotal(int dureeTotal) {
		this.dureeTotal = dureeTotal;
	}
	public String getRespo() {
		return respo;
	}
	public void setRespo(String respo) {
		this.respo = respo;
	}
	public int getId_doc() {
		return id_doc;
	}
	public void setId_doc(int id_doc) {
		this.id_doc = id_doc;
	}
	public String getNom_cl() {
		return nom_cl;
	}
	public void setNom_cl(String nom_cl) {
		this.nom_cl = nom_cl;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getNumTacheEnCour() {
		return numTacheEnCour;
	}
	public void setNumTacheEnCour(int numTacheEnCour) {
		this.numTacheEnCour = numTacheEnCour;
	}
	@Override
	public String toString() {
		return "Dossier [id_doc=" + id_doc + ", nom_cl=" + nom_cl + ", type="
				+ type + ", numTacheEnCour=" + numTacheEnCour + ", dateDebut="
				+ dateDebut + ", dateFin=" + dateFin + ", totalTache="
				+ totalTache + ", maxTache=" + maxTache + ", dureeTotal="
				+ dureeTotal + ", respo=" + respo + "]";
	}
	public Dossier(int id_doc, String nom_cl, String type, int numTacheEnCour) {
		super();
		this.id_doc = id_doc;
		this.nom_cl = nom_cl;
		this.type = type;
		this.numTacheEnCour = numTacheEnCour;
	}
	
	public Dossier( String nom_cl, String type, int numTacheEnCour) {
		super(); 
		this.nom_cl = nom_cl;
		this.type = type;
		this.numTacheEnCour = numTacheEnCour;
	}
	public Dossier() {
		super();
	}
	
	public String getDateDebut() {
		return dateDebut;
	}
	public int getTotalTache() {
		return totalTache;
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
	public void setTotalTache(int totalTache) {
		this.totalTache = totalTache;
		
	}
	public int getMaxTache() {
		return maxTache;
	}
	public void setMaxTache(int maxTache) {
		this.maxTache = maxTache;
	}
	
	
}
