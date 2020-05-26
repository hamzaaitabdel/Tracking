package entities;

public class AdditionalDoc {
 
	private int id;
	private String id_client;
	private int id_tache;
	private String id_respo;
	private String type ;
	private boolean done;
	private String date;
	
	public AdditionalDoc(int id, String id_client, int id_tache, String id_respo, String type) {
		super();
		this.id = id;
		this.id_client = id_client;
		this.id_tache = id_tache;
		this.id_respo = id_respo;
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getId_client() {
		return id_client;
	}

	public void setId_client(String id_client) {
		this.id_client = id_client;
	}

	public int getId_tache() {
		return id_tache;
	}

	public void setId_tache(int id_tache) {
		this.id_tache = id_tache;
	}

	public String getId_respo() {
		return id_respo;
	}

	public void setId_respo(String id_respo) {
		this.id_respo = id_respo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public AdditionalDoc() {
		
	}

	@Override
	public String toString() {
		return "AdditionalDoc [id=" + id + ", id_client=" + id_client + ", id_tache=" + id_tache + ", id_respo="
				+ id_respo + ", type=" + type + ", done=" + done + ", date=" + date + "]";
	}
	
	
}
