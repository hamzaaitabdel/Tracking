package dao;

import java.sql.Date;
import java.util.List;

import entities.Responsable;
import entities.Tache;

public interface ITache {

	public List<Tache> getTache(String search);
	public void addTache(Tache a);
	public List<Tache> ListTache();
	public int DateDifference(String debut, String fin);
	public List<Tache> ListTacheRespo(int id);
	public String getDatePremiereTache(int id);
	public String getDateDerniereTache(int id);
	public List<Tache> ListTachesAAprouveDossier(int indTache , int idDossier , String dateDebut);
	public void UpdateNTEC(int id,int x);
	public int TotalTache(int id);
	public int MaxTache(int id_doc);
	public Tache getOneTache(int idR, int idT);
	public void delete(int idT);
	public Tache getOneOneTache(int idT);
	public List<Tache> ListTacheRespoApprouver(int id_respo);
	public void UpdateValide(int idT);
	public List<Tache> ListTacheRespoEnCours(int id_respo);
	public int TotalTacheTache(int id_tache);
	public int MaxTacheTache(int id_tache);
	public int TotalApprouve(int id_respo);
	public int TotalEnCours(int id_respo);
	public int exist(int id);
	public void UpdateTurn(int idT, boolean b, boolean c);
	public int DureeTotal(int id_doc);
	public String RespoAdmin(int id_doc);
	public List<Tache> ListTachesRollback(int idT, int idD, String date_Debut);
	public List<Tache> ListVenir(int id_respo);
	public void UpdateRollback(int idT);
	public Tache tacheValide(int id_doc);
	public void deleteTache(String idDossiere);
	public Tache getOneOneTache(String string);
	public void editTache(Tache nouvelleTache);
	public int getNTEC(int id_doc);
	public void deleteDocTache(String idDossiere);
	public Tache getLastTache(String dateFin);
	public void editDateFin(int idAkhirTache, String dt);
	public int TotalVenir(int id_respooo);
}
