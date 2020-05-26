import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

public abstract class tttest1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
List<AdditionalDoc> list=additionalDoc();
for(AdditionalDoc a :list) {
	System.out.println(a.toString());
}
		
	}
	public static List<AdditionalDoc> additionalDoc() {
		int i=0;
		List<AdditionalDoc> list=new ArrayList();
		
		// TODO Auto-generated method stub
		Connection conn=DBconnect.getConnection();
		try {
			PreparedStatement st=(PreparedStatement) conn.prepareStatement("select * from additionaldoc ORDER BY id DESC");
			ResultSet rs=st.executeQuery();
			while(rs.next()) {
				AdditionalDoc doc=new AdditionalDoc();
				doc.setId(rs.getInt("id"));
				doc.setId_client(rs.getString("id_client"));
				doc.setId_respo(rs.getString("id_respo"));
				doc.setType(rs.getString("type"));
				doc.setId_tache(rs.getInt("id_tache"));
				doc.setDate(rs.getString("date"));
				list.add(doc);
				//doc.clear();
				System.out.println(i++);
			}
			st.close();
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return list;
	}
}
 class DBconnect {
	public static Connection connection;
	static{
	try{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tracking","root","");
			
		}catch(Exception e){
			System.out.println("Error"+e);
	}
	
}
	
	static Connection getConnection(){
		return connection;
	}
}
 class AdditionalDoc {
	 
		private int id;
		private String id_client;
		private int id_tache;
		private String id_respo;
		private String type ;
		private boolean done;
		private String date;
		public void clear() {
			this.id = 0;
			this.id_client = "";
			this.id_tache = 0;
			this.id_respo = "";
			this.type = "";
		}
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
