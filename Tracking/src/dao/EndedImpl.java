package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;




import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Ended;
import entities.Tache;

public class EndedImpl implements IEnded {

	@Override
	public void add(Tache nouvelleTache) {
		Connection conn=DBconnect.getConnection();
		try {
			PreparedStatement st=conn.prepareStatement("insert into ended values (?,?,?,?,?,?,?,?,?,?)");
			
			st.setInt(1, nouvelleTache.getId_tache());
			st.setInt(2, nouvelleTache.getId_respo());
			st.setInt(3, nouvelleTache.getId_doc());
			st.setBoolean(4, nouvelleTache.isValide());
			st.setString(5, nouvelleTache.getLibelle());
			st.setString(6, nouvelleTache.getDateDebut());
			st.setString(7, nouvelleTache.getDateFin());
			st.setString(8, nouvelleTache.getD_nom_cl());
			st.setString(9, nouvelleTache.getD_type());
			st.setInt(10, nouvelleTache.getD_numTacheEnCour());
			
			
			st.executeUpdate();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	
	@Override
	public List<Ended> ListTachat(int idR) {
		List<Ended> cmd = new ArrayList();
		Connection conn=DBconnect.getConnection();
		
		try {
			PreparedStatement st=conn.prepareStatement("select * from ended where id_respo = ?");  
			st.setInt(1,idR);
			ResultSet rs=st.executeQuery();
			while(rs.next()){
				Ended c=new Ended();
				c.setId_tache(rs.getInt("id_tache"));
				c.setId_respo(rs.getInt("id_respo"));
				c.setId_doc(rs.getInt("id_doc"));
				c.setValide(rs.getBoolean("valide"));
				c.setLibelle(rs.getString("libelle"));
				c.setDateDebut(rs.getString("dateDebut"));
				c.setDateFin(rs.getString("dateFin"));
				c.setD_nom_cl(rs.getString("d_nom_cl"));
				c.setD_type(rs.getString("d_type"));
				c.setD_numTacheEnCour(rs.getInt("d_numTacheEnCour"));
				cmd.add(c);
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cmd;
	}

	@Override
	public int countEnded(int id ) {
		int Nbr=0;
		Connection conn=DBconnect.getConnection();
		try {
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery("SELECT COUNT(*) AS total from ended where id_respo = "+id);
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
