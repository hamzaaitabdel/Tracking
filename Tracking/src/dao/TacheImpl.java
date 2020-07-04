package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import entities.Client;
import entities.Dossier;
import entities.Messenger;
import entities.Responsable;
import entities.Tache;

public class TacheImpl implements ITache {
	private ITache tacheDAO;
	private IResponsable respoDAO;
	private IDossier dossierDAO;
	private IClientmail clientmailDAO;
	private IClient clientDAO;
	private IMessage messageDAO;
	
	@Override
	public void GenerationTacheAuto(String id_respofull,String id_doc,String libelle,
			String dateDebut_,String dateFin_) {
		tacheDAO = new TacheImpl();
		respoDAO = new RespoImpl();
		dossierDAO = new DossierImpl();
		clientmailDAO = new ClientmailImpl();
		clientDAO = new ClientImpl();
		messageDAO = new MessageImpl();
		
		try {
			
			Dossier d1 = dossierDAO.getDoc(id_doc);
			String d_nom_cl = d1.getNom_cl();
			String d_type = d1.getType();
			Boolean b = false;
			
			//date difference
			int duree=tacheDAO.DateDifference(dateDebut_, dateFin_);  
			
			int i = id_respofull.indexOf(' ');
			String id_respo = id_respofull.substring(0, i);
			
			
			boolean a = false;
			int id = Integer.parseInt(id_doc);
			int deutschland = tacheDAO.exist(id);
			if(deutschland == 0){
				a=true;
				
			    Responsable respo = respoDAO.getRespo(id_respo);
				String RECIPIENT = respo.getEmail();
				String USER_NAME = "Tracking.GmbH";
				 String PASSWORD = "Bachelor."; 
				
				        String from = USER_NAME;
				        String pass = PASSWORD;
				        String[] to = { RECIPIENT };
				        String subject = "Bekanntmachung . ";
				        String body = "Guten Tag, Herr. "+respo.getNom()+". Sie haben die erste Aufgabe der Datei Nummer '"+id_doc+" vom Typ '"+d_type+"' "
				        		+ "' zu genehmigen und zu vervollst�ndigen. Letzter Termin die '"+dateFin_+"' .";
				respoDAO.sendFromGMail(from, pass, to, subject, body);
				String message = "Die erste Aufgabe der Datei "+id+" ist hinzugef�gt. (Verantwortlich: "+respo.getNom()+") ";

				Calendar rightNow = Calendar.getInstance();
				int hour = rightNow.get(Calendar.HOUR_OF_DAY);
				int minute = rightNow.get(Calendar.MINUTE);
				String Uhr= "h";
				String hr = String.valueOf(hour);
				String min = String.valueOf(minute);
				if(minute<10){
					Uhr= hr+":0"+min;
				}else{
					Uhr= hr+":"+min;
				}
				Messenger msg = new Messenger(id,0,message, null,Uhr);
				messageDAO.addMsgRespo(msg);
			}
			
			Tache nouvelleTache = new Tache(Integer.parseInt(id_respo) ,id,b
						,libelle,dateDebut_,dateFin_,d_nom_cl,d_type,0,a,duree);
			tacheDAO.addTache(nouvelleTache);
			
			List<Dossier> dossiers =  dossierDAO.ListDossier();
			for (Dossier dossier : dossiers) {
				String dateDebut = tacheDAO.getDatePremiereTache(dossier.getId_doc());
				String dateFin = tacheDAO.getDateDerniereTache(dossier.getId_doc());
				int totalTache = tacheDAO.TotalTache(dossier.getId_doc());
				int maxTache = tacheDAO.MaxTache(dossier.getId_doc());
				int dureeTotal = tacheDAO.DureeTotal(dossier.getId_doc());
				String respo = tacheDAO.RespoAdmin(dossier.getId_doc());
				dossier.setDateDebut(dateDebut);
				dossier.setDateFin(dateFin);
				dossier.setTotalTache(totalTache);
				dossier.setMaxTache(maxTache);
				dossier.setDureeTotal(dureeTotal);
				dossier.setRespo(respo);
				
			}
			List<Responsable> respo =  respoDAO.ListRespo();
			List<Client> clyan =  clientDAO.ListClient();
			for (Client client : clyan) {
				String email = clientmailDAO.getMailClient(client.getId_client());
				client.setEmail(email);
			}
			Responsable respoo = respoDAO.getRespo(id_respo);
			if(deutschland != 0){
			String message = "Die Datei Nummer "+id+" hat eine neue Aufgabe. (Verantwortlich: "+respoo.getNom()+") ";

			Calendar rightNow = Calendar.getInstance();
			int hour = rightNow.get(Calendar.HOUR_OF_DAY);
			int minute = rightNow.get(Calendar.MINUTE);
			String Uhr= "h";
			String hr = String.valueOf(hour);
			String min = String.valueOf(minute);
			if(minute<10){
				Uhr= hr+":0"+min;
			}else{
				Uhr= hr+":"+min;
			}
			Messenger msg = new Messenger(id,0,message, null,Uhr);
			messageDAO.addMsgRespo(msg);
			}
		} catch (Exception e) {
			
		} finally {
		}	

	}
	@Override
	public int getIdbyTracking(String tracking) {
		Connection conn=DBconnect.getConnection();
		int id=0;
		try {
			PreparedStatement st=conn.prepareStatement("select id_doc from dossier where tracking =?");  
			st.setString(1,tracking);
			ResultSet rs=st.executeQuery();
			if(rs.next()){
				id=rs.getInt("id_doc");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	@Override
	public List<Tache> getTache(String search) {
		List<Tache> cmd = new ArrayList();
		Connection conn=DBconnect.getConnection();
		
		try {
			int id=getIdbyTracking(search);
			PreparedStatement st=conn.prepareStatement("select * from Tache where id_doc =? order by dateDebut ASC");  
			st.setInt(1,id);
			ResultSet rs=st.executeQuery();
			while(rs.next()){
				Tache c=new Tache();
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
				c.setTurn(rs.getBoolean("turn"));
				c.setDuree(rs.getInt("duree"));
				cmd.add(c);
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cmd;
	}
	
	@Override
	public Tache getOneTache(int idR,int idT) {
		Tache c=new Tache();
		Connection conn=DBconnect.getConnection();
		
		try {
			PreparedStatement st=conn.prepareStatement("select * from Tache where id_respo =? and id_tache =?");  
			st.setInt(1, idR);
			st.setInt(2, idT);
			ResultSet rs=st.executeQuery();
			while(rs.next()){
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
				c.setTurn(rs.getBoolean("turn"));
				c.setDuree(rs.getInt("duree"));
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public void addTache(Tache nouvelleTache) {
		Connection conn=DBconnect.getConnection();
		try {
			PreparedStatement st=conn.prepareStatement("insert into Tache values (null,?,?,?,?,?,?,?,?,?,?,?)");
			
			st.setInt(1, nouvelleTache.getId_respo());
			st.setInt(2, nouvelleTache.getId_doc());
			st.setBoolean(3, nouvelleTache.isValide());
			st.setString(4, nouvelleTache.getLibelle());
			st.setString(5, nouvelleTache.getDateDebut());
			st.setString(6, nouvelleTache.getDateFin());
			st.setString(7, nouvelleTache.getD_nom_cl());
			st.setString(8, nouvelleTache.getD_type());
			st.setInt(9, nouvelleTache.getD_numTacheEnCour());
			st.setBoolean(10, nouvelleTache.isTurn());
			st.setInt(11, nouvelleTache.getDuree());
			
			st.executeUpdate();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}

	@Override
	public List<Tache> ListTache() {
		List<Tache> cmd = new ArrayList();
		Connection conn=DBconnect.getConnection();
		
		try {
			PreparedStatement st=conn.prepareStatement("select * from Tache");  
			ResultSet rs=st.executeQuery();
			while(rs.next()){
				Tache c=new Tache();
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
				c.setTurn(rs.getBoolean("turn"));
				c.setDuree(rs.getInt("duree"));
				cmd.add(c);
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cmd;
	}
	

	@Override
	public List<Tache> ListTacheRespo(int id) {
		List<Tache> cmd = new ArrayList();
		Connection conn=DBconnect.getConnection();
		
		try {
			PreparedStatement st=conn.prepareStatement("select * from Tache where id_tache NOT IN ( SELECT id_tache from ended) and id_respo = ? and turn = true");  
			st.setInt(1, id);
			ResultSet rs=st.executeQuery();
			while(rs.next()){
				Tache c=new Tache();
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
				c.setTurn(rs.getBoolean("turn"));
				c.setDuree(rs.getInt("duree"));
				cmd.add(c);
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cmd;
	}
	
	@Override
	public String getDatePremiereTache(int id){
		String dd=null;
		Connection conn=DBconnect.getConnection();
		try {
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery("select  dateDebut as ddebut FROM tache where id_doc = "+id+" order by dateDebut DESC");
			while(rs.next()){
				 dd = rs.getString("ddebut");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}return dd;
		
	}
	
	@Override
	public String getDateDerniereTache(int id){
		String df=null;
		Connection conn=DBconnect.getConnection();
		try {
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery("select dateFin as dfin FROM tache where id_doc = "+id+" order by dateDebut ASC");
			while(rs.next()){
				df = rs.getString("dfin");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}return df;
		
	}

	@Override
	public List<Tache> ListTachesAAprouveDossier(int idTache, int idDossier, String dateDebut) {
		List<Tache> taches = new ArrayList();
		Connection conn=DBconnect.getConnection();
		
		try {
			PreparedStatement st=conn.prepareStatement("select * from Tache where id_tache != ? and id_doc = ? and dateDebut > ? ORDER BY dateDebut");  
			st.setInt(1, idTache);
			st.setInt(2, idDossier);
			st.setString(3, dateDebut);
			ResultSet rs=st.executeQuery();
			while(rs.next()){
				Tache c=new Tache();
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
				c.setTurn(rs.getBoolean("turn"));
				c.setDuree(rs.getInt("duree"));
				taches.add(c);
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return taches;
	}

	@Override
	public void UpdateNTEC(int id,int x) {
		Connection conn=DBconnect.getConnection(); 
		try {
			PreparedStatement st=conn.prepareStatement("UPDATE Tache SET d_numTacheEnCour = ? where id_tache = ?");
			st.setInt(1, x);
			st.setInt(2, id);
			st.executeUpdate();
			st.close();
			}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public int TotalTache(int id) {
		int Nbr=0;
		Connection conn=DBconnect.getConnection();
		try {
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery("SELECT COUNT(*) AS total FROM tache where id_doc = "+id);
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
	public int MaxTache(int id) {
		int Nbr=0;
		Connection conn=DBconnect.getConnection();
		try {
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery("select MAX(d_numTacheEnCour) AS total from tache where id_doc = "+id);
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
	public void delete(int idT) {
		Connection conn=DBconnect.getConnection();
		try {
			PreparedStatement st=conn.prepareStatement("delete from tache where id_tache=? ");
			st.setInt(1,idT);
			st.executeUpdate();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Tache getOneOneTache(int idT) {
		Tache c=new Tache();
		Connection conn=DBconnect.getConnection();
		
		try {
			PreparedStatement st=conn.prepareStatement("select * from Tache where id_tache =?");  
			st.setInt(1, idT);
			ResultSet rs=st.executeQuery();
			while(rs.next()){
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
				c.setTurn(rs.getBoolean("turn"));
				c.setDuree(rs.getInt("duree"));
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public List<Tache> ListTacheRespoApprouver(int id) {
		List<Tache> cmd = new ArrayList();
		Connection conn=DBconnect.getConnection();
		
		try {
			PreparedStatement st=conn.prepareStatement("select * from Tache where id_tache NOT IN ( SELECT id_tache from ended) and id_respo = ? and valide = false and turn = true");  
			st.setInt(1, id);
			ResultSet rs=st.executeQuery();
			while(rs.next()){
				Tache c=new Tache();
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
				c.setTurn(rs.getBoolean("turn"));
				c.setDuree(rs.getInt("duree"));
				cmd.add(c);
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cmd;
	}

	@Override
	public void UpdateValide(int idT) {
		Connection conn=DBconnect.getConnection(); 
		try {
			PreparedStatement st=conn.prepareStatement("UPDATE Tache SET valide = TRUE where id_tache = ?");
			st.setInt(1, idT);
			st.executeUpdate();
			st.close();
			}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public List<Tache> ListTacheRespoEnCours(int id) {
		List<Tache> cmd = new ArrayList();
		Connection conn=DBconnect.getConnection();
		
		try {
			PreparedStatement st=conn.prepareStatement("select * from Tache where id_tache NOT IN ( SELECT id_tache from ended) and id_respo = ? and valide = true and turn = true"); 
			//and d_numTacheEnCour = (select MAX(d_numTacheEnCour) from tache where id_respo = 2)
			st.setInt(1, id);
			ResultSet rs=st.executeQuery();
			while(rs.next()){
				Tache c=new Tache();
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
				c.setTurn(rs.getBoolean("turn"));
				c.setDuree(rs.getInt("duree"));
				cmd.add(c);
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cmd;
	}

	@Override
	public int TotalTacheTache(int id) {
		int Nbr=0;
		Connection conn=DBconnect.getConnection();
		try {
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery("SELECT COUNT(*) AS total FROM tache where id_doc = "+id);
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
	public int MaxTacheTache(int id) {
		int Nbr=0;
		Connection conn=DBconnect.getConnection();
		try {
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery("select MAX(d_numTacheEnCour) AS total from tache where id_doc = "+id);
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
	public int TotalApprouve(int id) {
		int Nbr=0;
		Connection conn=DBconnect.getConnection();
		try {
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery("SELECT COUNT(*) AS total from Tache where id_tache NOT IN ( SELECT id_tache from ended) and id_respo = "+id+" and valide = true and turn = true");
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
	public int TotalEnCours(int id) {
		int Nbr=0;
		Connection conn=DBconnect.getConnection();
		try {
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery("SELECT COUNT(*) AS total from Tache where id_tache NOT IN ( SELECT id_tache from ended) and id_respo = "+id+" and valide = false and turn = true");
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
	public int exist(int id) {
		int Nbr=0;
		Connection conn=DBconnect.getConnection();
		try {
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery("SELECT COUNT(*) AS total from Tache where id_doc = "+id);
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
	public void UpdateTurn(int idT, boolean b, boolean c) {
		Connection conn=DBconnect.getConnection(); 
		try {
			PreparedStatement st=conn.prepareStatement("UPDATE Tache SET turn = ? , valide= ? where id_tache = ?");
			st.setBoolean(1, b);
			st.setBoolean(2, c);
			st.setInt(3, idT);
			st.executeUpdate();
			st.close();
			}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public int DateDifference(String debut, String fin) {
		int diff=0;
		try {  
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	    Date firstDate;
		
		firstDate = sdf.parse(debut);
		
	    Date secondDate = sdf.parse(fin);
	 
	    int diffInMillies = (int) Math.abs(secondDate.getTime() - firstDate.getTime());
	    diff = (int) TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
	 
	} catch (ParseException e) {
			e.printStackTrace();
		}
		return diff;}

	@Override
	public int DureeTotal(int id) {
		int Nbr=0;
		Connection conn=DBconnect.getConnection();
		try {
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery("select SUM(duree) as total from tache where id_doc = "+id);
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
	public String RespoAdmin(int id) {
		String resp = null;
		Connection conn=DBconnect.getConnection();
		try {
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery("select nom as bachtsaybhadlfonctionkhasstkonsmytkaymenbenjbara from responsable where id_respo = (select id_respo from tache where id_doc = "+id+" and turn = true) ");
			 while(rs.next()){
				 resp = rs.getString("bachtsaybhadlfonctionkhasstkonsmytkaymenbenjbara");
			 }
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resp;
	}

	@Override
	public List<Tache> ListTachesRollback(int idT, int idD, String date_Debut) {
		List<Tache> taches = new ArrayList();
		Connection conn=DBconnect.getConnection();
		
		try {
			PreparedStatement st=conn.prepareStatement("select * from Tache where id_tache != ? and id_doc = ? and dateDebut < ? ORDER BY dateDebut DESC");  
			st.setInt(1, idT);
			st.setInt(2, idD);
			st.setString(3, date_Debut);
			ResultSet rs=st.executeQuery();
			while(rs.next()){
				Tache c=new Tache();
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
				c.setTurn(rs.getBoolean("turn"));
				c.setDuree(rs.getInt("duree"));
				taches.add(c);
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return taches;
	}

	@Override
	public List<Tache> ListVenir(int id_respo) {
		List<Tache> taches = new ArrayList();
		Connection conn=DBconnect.getConnection();
		
		try {
			PreparedStatement st=conn.prepareStatement("SELECT * FROM tache where id_respo = ? and  d_numTacheEnCour = 0  and  turn = 0");  
			st.setInt(1, id_respo);
			ResultSet rs=st.executeQuery();
			while(rs.next()){
				Tache c=new Tache();
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
				c.setTurn(rs.getBoolean("turn"));
				c.setDuree(rs.getInt("duree"));
				taches.add(c);
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return taches;
	}

	@Override
	public void UpdateRollback(int idT) {
		Connection conn=DBconnect.getConnection(); 
		try {
			PreparedStatement st=conn.prepareStatement("UPDATE Tache SET d_numTacheEnCour= 0 , valide = false , turn = false where id_tache = ?");
			st.setInt(1, idT);
			st.executeUpdate();
			st.close();
			}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public Tache tacheValide(int id_doc) {
		Tache c=new Tache();
		Connection conn=DBconnect.getConnection();
		
		try {
			PreparedStatement st=conn.prepareStatement("select * from Tache where id_doc =? and turn = true");  
			st.setInt(1, id_doc);
			ResultSet rs=st.executeQuery();
			while(rs.next()){
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
				c.setTurn(rs.getBoolean("turn"));
				c.setDuree(rs.getInt("duree"));
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public void deleteTache(String idDossiere) {
		Connection conn=DBconnect.getConnection();
		try {
			PreparedStatement st=conn.prepareStatement("delete from tache where id_tache =? ");
			st.setInt(1,Integer.parseInt(idDossiere));
			st.executeUpdate();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Tache getOneOneTache(String string) {
		Tache c=new Tache();
		Connection conn=DBconnect.getConnection();
		int idT = Integer.parseInt(string);
		try {
			PreparedStatement st=conn.prepareStatement("select * from Tache where id_tache =?");  
			st.setInt(1, idT);
			ResultSet rs=st.executeQuery();
			while(rs.next()){
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
				c.setTurn(rs.getBoolean("turn"));
				c.setDuree(rs.getInt("duree"));
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public void editTache(Tache nouvelleTache) {
		Connection conn=DBconnect.getConnection(); 
//		boolean a = false;
//		boolean b = false;
//		if(nouvelleTache.isValide()){
//			a=true;
//		}
//		if(nouvelleTache.isTurn()){
//			b=true;
//		}
		try {
			PreparedStatement st=conn.prepareStatement("UPDATE tache SET id_respo = ? , id_doc = ? , valide = ? , "
					+ "libelle = ? , dateDebut = ? , dateFin = ? , d_nom_cl = ? , d_type = ? , d_numTacheEnCour = ?,"
					+ " turn = ?, duree = ? where id_tache = ?");
			st.setInt(1, nouvelleTache.getId_respo());
			st.setInt(2, nouvelleTache.getId_doc());
			st.setBoolean(3, nouvelleTache.isValide());
			st.setString(4, nouvelleTache.getLibelle());
			st.setString(5, nouvelleTache.getDateDebut());
			st.setString(6, nouvelleTache.getDateFin());
			st.setString(7, nouvelleTache.getD_nom_cl());
			st.setString(8, nouvelleTache.getD_type());
			st.setInt(9, nouvelleTache.getD_numTacheEnCour());
			st.setBoolean(10, nouvelleTache.isTurn());
			st.setInt(11, nouvelleTache.getDuree());
			st.setInt(12, nouvelleTache.getId_tache());
			
			st.executeUpdate();
			st.close();
			}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Override
	public int getNTEC(int idT) {
		int Nbr=0;
		Connection conn=DBconnect.getConnection();
		try {
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery("SELECT MAX(d_numTacheEnCour) AS numTacheEnCour FROM tache where id_doc = "+idT);
			 while(rs.next()){
				 Nbr = rs.getInt("numTacheEnCour");
			 }
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Nbr;
	}

	@Override
	public void deleteDocTache(String idDossiere) {
		Connection conn=DBconnect.getConnection();
		int idD = Integer.parseInt(idDossiere);
		try {
			PreparedStatement st=conn.prepareStatement("delete from tache where id_doc=? ");
			st.setInt(1,idD);
			st.executeUpdate();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Tache getLastTache(String dateFin) {
		Tache c=new Tache();
		Connection conn=DBconnect.getConnection();
		
		try {
			PreparedStatement st=conn.prepareStatement("select * from Tache where dateFin =?");  
			st.setString(1, dateFin);
			ResultSet rs=st.executeQuery();
			while(rs.next()){
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
				c.setTurn(rs.getBoolean("turn"));
				c.setDuree(rs.getInt("duree"));
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public void editDateFin(int idAkhirTache, String dt) {
		Connection conn=DBconnect.getConnection(); 
		try {
			PreparedStatement st=conn.prepareStatement("UPDATE Tache SET dateFin = ? where id_tache = ?");
			st.setString(1, dt);
			st.setInt(2, idAkhirTache);
			st.executeUpdate();
			st.close();
			}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public int TotalVenir(int id ) {
		int Nbr=0;
		Connection conn=DBconnect.getConnection();
		try {
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery("SELECT COUNT(*) AS total  FROM tache where id_respo = "+id+" and  d_numTacheEnCour = 0  and  turn = 0");
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
