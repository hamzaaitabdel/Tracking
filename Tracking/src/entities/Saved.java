package entities;

public class Saved {

	private int id_saved;
	private int id_doc;
	private int id_client;
	private String nom_cl; 
	private String type;
	// date debut premiere tache du dossier
		private String dateDebut;
		// date fin derniere tache du dossier
		private String dateFin;
		private int totalTache;
		private int maxTache;
		private int dureeTotal;
		
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
		public int getDureeTotal() {
			return dureeTotal;
		}
		public void setDureeTotal(int dureeTotal) {
			this.dureeTotal = dureeTotal;
		}
	public int getId_saved() {
		return id_saved;
	}
	public void setId_saved(int id_saved) {
		this.id_saved = id_saved;
	}
	public int getId_doc() {
		return id_doc;
	}
	public void setId_doc(int id_doc) {
		this.id_doc = id_doc;
	}
	public int getId_client() {
		return id_client;
	}
	public void setId_client(int id_client) {
		this.id_client = id_client;
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
	@Override
	public String toString() {
		return "saved [id_saved=" + id_saved + ", id_doc=" + id_doc
				+ ", id_client=" + id_client + ", nom_cl=" + nom_cl + ", type="
				+ type + "]";
	}
	public Saved(int id_saved, int id_doc, int id_client, String nom_cl,
			String type) {
		super();
		this.id_saved = id_saved;
		this.id_doc = id_doc;
		this.id_client = id_client;
		this.nom_cl = nom_cl;
		this.type = type;
	}
	public Saved(int id_doc, int id_client, String nom_cl,
			String type) {
		super();
		this.id_doc = id_doc;
		this.id_client = id_client;
		this.nom_cl = nom_cl;
		this.type = type;
	}
	public Saved() {
		super();
		// TODO Auto-generated constructor stub
	} 
	
	

}
