package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.Client;
import entities.Dossier;

public class DossierImpl implements IDossier {
	@Override
	public String getEmail(int id_doc) {
		String email = "not found";
		Connection conn=DBconnect.getConnection();
		try {
			PreparedStatement st=conn.prepareStatement("select email from clientmail where id_doc=?");
			st.setInt(1, id_doc);
			ResultSet rs =st.executeQuery();
			if(rs.next()) {
				email=rs.getString("email");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return email;
	}
	@Override
	public void addDossier(Dossier d) {
		Connection conn=DBconnect.getConnection();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		try {
			PreparedStatement st=conn.prepareStatement("insert into Dossier values (?,?,?,?,?)");
			
			st.setInt(1, d.getId_doc());
			st.setString(2, d.getNom_cl());
			st.setString(3, d.getType());
			st.setInt(4, d.getNumTacheEnCour());
			st.setString(5,dateFormat.format(date));
			st.executeUpdate();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Dossier> ListDossier() {
		List<Dossier> cmd = new ArrayList();
		Connection conn=DBconnect.getConnection();
		
		try {
			PreparedStatement st=conn.prepareStatement("select * from Dossier");  
			ResultSet rs=st.executeQuery();
			while(rs.next()){
				Dossier c=new Dossier();
				c.setId_doc(rs.getInt("id_doc"));
				c.setNom_cl(rs.getString("nom_cl"));
				c.setType(rs.getString("type"));
				c.setNumTacheEnCour(rs.getInt("numTacheEnCour"));
				cmd.add(c);
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cmd;
	}

	@Override
	public Dossier getDoc(String idDoc) {
		Dossier c=new Dossier();
		Connection conn=DBconnect.getConnection();
		try {
			PreparedStatement st=conn.prepareStatement("select * from Dossier where id_doc=?");
			int chercher = Integer.parseInt(idDoc);
			st.setInt(1, chercher);
			ResultSet rs=st.executeQuery();
			if(rs.next()){
				c.setId_doc(rs.getInt("id_doc"));
				c.setNom_cl(rs.getString("nom_cl"));
				c.setType(rs.getString("type"));
				c.setNumTacheEnCour(rs.getInt("numTacheEnCour"));
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return c;
	}

	@Override
	public List<Dossier> ListDossierRespo(int id) {
	List<Dossier> cmd = new ArrayList();
	Connection conn=DBconnect.getConnection();
	
	try {
		PreparedStatement st=conn.prepareStatement("select * from Dossier where id_doc IN (select id_doc from tache where id_respo = ?)"); 
		st.setInt(1, id);
		ResultSet rs=st.executeQuery();
		while(rs.next()){
			Dossier c=new Dossier();
			c.setId_doc(rs.getInt("id_doc"));
			c.setNom_cl(rs.getString("nom_cl"));
			c.setType(rs.getString("type"));
			c.setNumTacheEnCour(rs.getInt("numTacheEnCour"));
			cmd.add(c);
		}
		st.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return cmd;}

	@Override
	public void deleteDoc(String idDossiere) {
		Connection conn=DBconnect.getConnection();
	try {
		PreparedStatement st=conn.prepareStatement("delete from dossier where id_doc=? ");
		st.setInt(1,Integer.parseInt(idDossiere));
		st.executeUpdate();
		st.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	}

	@Override
	public void editDossier(Dossier nouveauDossier) {
		Connection conn=DBconnect.getConnection(); 
		try {
			PreparedStatement st=conn.prepareStatement("UPDATE dossier SET nom_cl = ? , type = ? , numTacheEnCour = ?  where id_doc = ?");
			st.setString(1, nouveauDossier.getNom_cl());
			st.setString(2, nouveauDossier.getType());
			st.setInt(3, nouveauDossier.getNumTacheEnCour());
			st.setInt(4, nouveauDossier.getId_doc());
			st.executeUpdate();
			st.close();
			}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public String getNomCl(int id_dossier) {
		List<Dossier> cmd = new ArrayList();
		Connection conn=DBconnect.getConnection();
		String nom_client=null;
		try {
			PreparedStatement st=conn.prepareStatement("select nom_cl as NC from Dossier where id_doc = ?"); 
			st.setInt(1, id_dossier);
			ResultSet rs=st.executeQuery();
			while(rs.next()){
				nom_client = rs.getString("NC");
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nom_client;
	}

	@Override
	public List<Dossier> ListDossierClient(String nom_cl) {
		List<Dossier> cmd = new ArrayList();
		Connection conn=DBconnect.getConnection();
		
		try {
			PreparedStatement st=conn.prepareStatement("select * from Dossier where nom_cl = ?"); 
			st.setString(1, nom_cl);
			ResultSet rs=st.executeQuery();
			while(rs.next()){
				Dossier c=new Dossier();
				c.setId_doc(rs.getInt("id_doc"));
				c.setNom_cl(rs.getString("nom_cl"));
				c.setType(rs.getString("type"));
				c.setNumTacheEnCour(rs.getInt("numTacheEnCour"));
				cmd.add(c);
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cmd;
	}

	@Override
	public Dossier getDosser(int idD) {
		Dossier c=new Dossier();
		Connection conn=DBconnect.getConnection();
		try {
			PreparedStatement st=conn.prepareStatement("select * from Dossier where id_doc=?"); 
			st.setInt(1, idD);
			ResultSet rs=st.executeQuery();
			if(rs.next()){
				c.setId_doc(rs.getInt("id_doc"));
				c.setNom_cl(rs.getString("nom_cl"));
				c.setType(rs.getString("type"));
				c.setNumTacheEnCour(rs.getInt("numTacheEnCour"));
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return c;
	}

	@Override
	public String getIdRandom(int nbr) {
		//char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		String output="";
		for(int i=0;i<nbr;i++){
			int n = (int)(Math.random()*10);
			output+=n+"";
		}
		/*
		for(int i=0;i<nbr;i++){
			int n = (int)(Math.random()*alphabet.length);
			output+=alphabet[n]+"";
		}
		*/
		return output;
	}

	@Override
	public int getAllclient() {
		int cp=0;
		Connection conn=DBconnect.getConnection();
		try {
			PreparedStatement st=conn.prepareStatement("SELECT * from dossier"); 
			
			ResultSet rs=st.executeQuery();
			while(rs.next()){
				cp++;
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		return cp;
	}


	@Override
	public int getRegistredclient() {
		int cp=0;
		Connection conn=DBconnect.getConnection();
		try {
			PreparedStatement st=conn.prepareStatement("SELECT DISTINCT id_client from client"); 
			
			ResultSet rs=st.executeQuery();
			if(rs.next()){
				cp++;
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		return cp;
	}

}
