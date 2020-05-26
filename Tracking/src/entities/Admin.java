package entities;



public class Admin {
	
	private int id_admin;
	private String nom;
	private String email;
	private String password;
	private String photo;
	
	
	public int getId_admin() {
		return id_admin;
	}
	public void setId_admin(int id_admin) {
		this.id_admin = id_admin;
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
		return "Admin [id_admin=" + id_admin +", nom=" + nom
				+ ", email=" + email + ", password=" + password + ", photo="
				+ photo + "]";
	}
	public Admin(int id_admin, String nom, String email, String password,
			String photo) {
		super();
		
		this.id_admin = id_admin;
		this.nom = nom;
		this.email = email;
		this.password = password;
		this.photo = photo;
	}
	
	public Admin(String nom, String email, String password,
			String photo) {
		super();
		this.nom = nom;
		this.email = email;
		this.password = password;
		this.photo = photo;
	}
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	 
	
	
}
