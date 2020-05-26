package entities;

public class Clientmail {
	
	private int id_cm;
	private int id_doc;
	private String email;
	public int getId_cm() {
		return id_cm;
	}
	public void setId_cm(int id_cm) {
		this.id_cm = id_cm;
	}
	public int getId_doc() {
		return id_doc;
	}
	public void setId_doc(int id_doc) {
		this.id_doc = id_doc;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Clientmail [id_cm=" + id_cm + ", id_doc=" + id_doc + ", email="
				+ email + "]";
	}
	public Clientmail(int id_cm, int id_doc, String email) {
		super();
		this.id_cm = id_cm;
		this.id_doc = id_doc;
		this.email = email;
	}
	public Clientmail(int id_doc, String email) {
		super();
		this.id_doc = id_doc;
		this.email = email;
	}
	public Clientmail() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
