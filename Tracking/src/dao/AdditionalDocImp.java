package dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entities.AdditionalDoc;

public class AdditionalDocImp implements IadditionalDoc {
public static int cp=0;
@Override
public AdditionalDoc getadditionalDoc(int id) {
	// TODO Auto-generated method stub
	Connection conn=DBconnect.getConnection();
	AdditionalDoc doc=new AdditionalDoc();

	try {
		PreparedStatement st=conn.prepareStatement("select * from additionaldoc where id=?");
		st.setInt(1, id);
		ResultSet rs=st.executeQuery();
		if(rs.next()) {
			doc.setId(rs.getInt("id"));
			doc.setId_client(rs.getString("id_client"));
			doc.setId_respo(rs.getString("id_respo"));
			doc.setType(rs.getString("type"));
			doc.setId_tache(rs.getInt("id_tache"));
			doc.setDate(rs.getString("date"));
			
		}
		st.close();
	}
	catch(SQLException ex) {
		System.out.println(ex.getMessage());
	}
	return doc;
}
	@Override
	public List<AdditionalDoc> additionalDoc() {
		List<AdditionalDoc> list=new ArrayList();
		// TODO Auto-generated method stub
		Connection conn=DBconnect.getConnection();
		try {
			cp=0;
			PreparedStatement st=conn.prepareStatement("select * from additionaldoc ORDER BY id DESC");
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
				cp++;
			}
			st.close();
			System.out.println("cp :"+cp);
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return list;
	}
    @Override
    public void addAdditionalDoc(AdditionalDoc doc) {
    	Connection conn=DBconnect.getConnection();
    	try {
    		PreparedStatement st=conn.prepareStatement("insert into additionaldoc values (?,?,?,?,?,?)");
    		st.setInt(1, 0);
    		System.out.println("here we go again");
    		st.setString(2, doc.getId_respo());
    		st.setString(3,doc.getId_client());
    		st.setString(4, doc.getType());
    		st.setInt(5,doc.getId_tache());
    		st.setString(6,doc.getDate());
    		System.out.println("here we go again 3awd");

    		st.executeUpdate();
			st.close();
    	}
    	catch(SQLException ex) {
    		System.out.println("error"+ex.getMessage());
    		ex.printStackTrace();
    	}
    }
	@Override
	public void removeRequest(int id) {
		// TODO Auto-generated method stub
		Connection conn=DBconnect.getConnection();
    	try {
    		PreparedStatement st=conn.prepareStatement("delete from additionaldoc where id=?");
    		st.setInt(1, id);
    		
    		st.executeUpdate();
			st.close();
    	}
    	catch(SQLException ex) {
    		System.out.println(ex.getMessage());
    		ex.printStackTrace();
    	}
		
	}
	@Override
	public void removeRequestDossier(int id) {
		// TODO Auto-generated method stub
		Connection conn=DBconnect.getConnection();
    	try {
    		PreparedStatement st=conn.prepareStatement("delete from additionaldoc where id_tache in(select id_tache from tache where id doc=?)");
    		st.setInt(1, id);
    		
    		st.executeUpdate();
			st.close();
    	}
    	catch(SQLException ex) {
    		System.out.println(ex.getMessage());
    		ex.printStackTrace();
    	}
	}
	
}
