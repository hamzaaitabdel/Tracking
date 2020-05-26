package entities;



public class Responsable {
	
	private int id_respo;
	private String nom;
	private String email;
	private String password;
	private String photo;
	private boolean online;
	
	public boolean isOnline() {
		return online;
	}
	public void setOnline(boolean online) {
		this.online = online;
	}
	public int getId_respo() {
		return id_respo;
	}
	public void setId_respo(int id_respo) {
		this.id_respo = id_respo;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	@Override
	public String toString() {
		return "Responsable [id_respo=" + id_respo +"online="+online +", nom=" + nom
				+ ", email=" + email + ", password=" + password + ", photo="
				+ photo + "]";
	}
	public Responsable(int id_respo, String nom, String email, String password,
			String photo) {
		super();
		
		this.id_respo = id_respo;
		this.nom = nom;
		this.email = email;
		this.password = password;
		this.photo = photo;
	}
	
	public Responsable(String nom, String email, String password,
			String photo) {
		super();
		this.nom = nom;
		this.email = email;
		this.password = password;
		this.photo = photo;
	}
	public Responsable() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	 
	
	
}
