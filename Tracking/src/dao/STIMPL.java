package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Dossier;
import entities.SousTache;

public class STIMPL implements IST {

	@Override
	public void addST(SousTache ST) {
		Connection conn=DBconnect.getConnection();
		try {
			PreparedStatement st=conn.prepareStatement("insert into sous_tache values (null,?,?)");
			
			st.setInt(1, ST.getId_tache());
			st.setString(2, ST.getLibelle());
			st.executeUpdate();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}

	@Override
	public List<SousTache> ListST(int id) {
		List<SousTache> cmd = new ArrayList();
		Connection conn=DBconnect.getConnection();
		
		try {
			PreparedStatement st=conn.prepareStatement("select * from sous_tache where id_tache = ? order by id_sousTache DESC"); 
			st.setInt(1, id);
			ResultSet rs=st.executeQuery();
			while(rs.next()){
				SousTache c=new SousTache();
				c.setId_ST(rs.getInt("id_sousTache"));
				c.setId_tache(rs.getInt("id_tache"));
				c.setLibelle(rs.getString("libelle"));
				cmd.add(c);
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cmd;
	}

	@Override
	public void deleteST(int idT2) {
		Connection conn=DBconnect.getConnection();
		try {
			PreparedStatement st=conn.prepareStatement("delete from ended where id_tache=? ");
			st.setInt(1,idT2);
			st.executeUpdate();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void deleteSTdbsh(int idT2) {
		Connection conn=DBconnect.getConnection();
		try {
			PreparedStatement st=conn.prepareStatement("delete from sous_tache where id_sousTache =? ");
			st.setInt(1,idT2);
			st.executeUpdate();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public int NumST(int id) {
		int Nbr=0;
		Connection conn=DBconnect.getConnection();
		try {
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery("SELECT COUNT(*) AS total from sous_tache where id_tache = "+id);
			 while(rs.next()){
				 Nbr = rs.getInt("total");
			 }
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Nbr;
	}

	@Override
	public void deleteDocST(String idDossiere) {
		Connection conn=DBconnect.getConnection();
		int idD = Integer.parseInt(idDossiere);
		try {
			PreparedStatement st=conn.prepareStatement("delete from sous_tache where id_tache IN (select id_tache from tache where id_doc = ?) ");
			st.setInt(1,idD);
			st.executeUpdate();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
  

}
