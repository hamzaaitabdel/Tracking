package entities;

import java.sql.Blob;

public class Messenger {
	
	private int id_mess;
	private int id_doc;
	private int id_respo;
	private String message; 
	private Blob attachement;
	private String dat;
	private String nom_respo;
	
	public int getId_mess() {
		return id_mess;
	}
	public void setId_mess(int id_mess) {
		this.id_mess = id_mess;
	}
	public int getId_doc() {
		return id_doc;
	}
	public void setId_doc(int id_doc) {
		this.id_doc = id_doc;
	}
	public int getId_respo() {
		return id_respo;
	}
	public void setId_respo(int id_respo) {
		this.id_respo = id_respo;
	}
	public String getmessage() {
		return message;
	}
	public void setmessage(String message) {
		this.message = message;
	}
	public Blob getAttachement() {
		return attachement;
	}
	public void setAttachement(Blob attachement) {
		this.attachement = attachement;
	}
	public String getDat() {
		return dat;
	}
	public void setDat(String dat) {
		this.dat = dat;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getNom_respo() {
		return nom_respo;
	}
	public void setNom_respo(String nom_respo) {
		this.nom_respo = nom_respo;
	}
	@Override
	public String toString() {
		return "Messenger [id_mess=" + id_mess + ", id_doc=" + id_doc
				+ ", id_respo=" + id_respo + ", message=" + message
				+ ", attachement=" + attachement + ", dat=" + dat
				+ "]";
	}
	public Messenger(int id_mess, int id_doc, int id_respo, String message,
			Blob attachement, String dat) {
		super();
		this.id_mess = id_mess;
		this.id_doc = id_doc;
		this.id_respo = id_respo;
		this.message = message;
		this.attachement = attachement;
		this.dat = dat;
	}
	
	public Messenger(int id_doc, int id_respo, String message,
			Blob attachement, String dat) {
		super();
		this.id_doc = id_doc;
		this.id_respo = id_respo;
		this.message = message;
		this.attachement = attachement;
		this.dat = dat;
	}
	public Messenger() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
