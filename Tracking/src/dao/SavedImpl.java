package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Dossier;
import entities.Saved;

public class SavedImpl implements ISaved {

	@Override
	public List<Saved> ListSaved(int id) {
		List<Saved> cmd = new ArrayList();
		Connection conn=DBconnect.getConnection();
		
		try {
			PreparedStatement st=conn.prepareStatement("select * from saved where id_client = ?");  
			st.setInt(1, id);
			ResultSet rs=st.executeQuery();
			while(rs.next()){
				Saved c=new Saved();
				c.setId_saved(rs.getInt("id_saved"));
				c.setId_client(id);
				c.setId_doc(rs.getInt("id_doc"));
				c.setNom_cl(rs.getString("nom_cl"));
				c.setType(rs.getString("type"));
				cmd.add(c);
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cmd;
	}

	@Override
	public void deleteSaved(String id_doc, String id_client) {
		Connection conn=DBconnect.getConnection();
		try {
			PreparedStatement st=conn.prepareStatement("delete from saved where id_doc=? and id_client=?");
			st.setInt(1,Integer.parseInt(id_doc));
			st.setInt(2,Integer.parseInt(id_client));
			st.executeUpdate();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void addSaved(String id_doc, String id_client, String nomClient,
			String typa) {
		Connection conn=DBconnect.getConnection();
		try {
			PreparedStatement st=conn.prepareStatement("insert into saved values (null,?,?,?,?)");
			
			st.setInt(1, Integer.parseInt(id_doc));
			st.setInt(2, Integer.parseInt(id_client));
			st.setString(3, nomClient);
			st.setString(4, typa);
			st.executeUpdate();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}

	@Override
	public boolean exist(int id_client, int id_doc) {
		Connection conn=DBconnect.getConnection();
		boolean b=false;
		try {
			PreparedStatement st=conn.prepareStatement("select * from saved where id_client=? and id_doc=?");
			st.setInt(1, id_client);
			st.setInt(2, id_doc);
		ResultSet rs=st.executeQuery();
		
		if(rs.next()){
			b=true;
		}
		
	}catch (SQLException e) {
	}
		return b;
	}

	@Override
	public int countSaved(int id) {

		int Nbr=0;
		Connection conn=DBconnect.getConnection();
		try {
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery("SELECT COUNT(*) AS total from saved where id_client = "+id);
			 while(rs.next()){
				 Nbr = rs.getInt("total");
			 }
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Nbr;
	 
	}

}
