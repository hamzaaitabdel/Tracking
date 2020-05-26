package entities;

public class Client {
	
	private int id_client;
	private int id_clientmail;
	private String nom;
	private String password;
	private String adresse;
	private String tel;
	private String carte;
	private int ccv;
	private String expiry;
	private boolean premium;
	private String email
	;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId_client() {
		return id_client;
	}
	public void setId_client(int id_client) {
		this.id_client = id_client;
	}
	public int getId_clientmail() {
		return id_clientmail;
	}
	public void setId_clientmail(int id_clientmail) {
		this.id_clientmail = id_clientmail;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getCarte() {
		return carte;
	}
	public void setCarte(String carte) {
		this.carte = carte;
	}
	public int getCcv() {
		return ccv;
	}
	public void setCcv(int ccv) {
		this.ccv = ccv;
	}
	public String getExpiry() {
		return expiry;
	}
	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}
	public boolean isPremium() {
		return premium;
	}
	public void setPremium(boolean premium) {
		this.premium = premium;
	}
	@Override
	public String toString() {
		return "Client [id_client=" + id_client + ", id_clientmail="
				+ id_clientmail + ", nom=" + nom + ", password=" + password
				+ ", adresse=" + adresse + ", tel=" + tel + ", carte=" + carte
				+ ", ccv=" + ccv + ", expiry=" + expiry + ", premium="
				+ premium + "]";
	}
	public Client(int id_client, int id_clientmail, String nom,
			String password, String adresse, String tel, String carte, int ccv,
			String expiry, boolean premium) {
		super();
		this.id_client = id_client;
		this.id_clientmail = id_clientmail;
		this.nom = nom;
		this.password = password;
		this.adresse = adresse;
		this.tel = tel;
		this.carte = carte;
		this.ccv = ccv;
		this.expiry = expiry;
		this.premium = premium;
	}
	public Client(int id_clientmail, String nom,
			String password, String adresse, String tel, String carte, int ccv,
			String expiry, boolean premium) {
		super();
		this.id_clientmail = id_clientmail;
		this.nom = nom;
		this.password = password;
		this.adresse = adresse;
		this.tel = tel;
		this.carte = carte;
		this.ccv = ccv;
		this.expiry = expiry;
		this.premium = premium;
	}

	public Client(int id_clientmail, String nom,
			String password,String tel, boolean premium) {
		super();
		this.id_clientmail = id_clientmail;
		this.nom = nom;
		this.password = password;
		this.tel = tel;
		this.premium = premium;
	}
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}



}
