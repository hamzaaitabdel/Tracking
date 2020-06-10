package dao;

import java.util.List;

import entities.Dossier;

public interface IDossier {
	public void addDossier(Dossier d);
	public List<Dossier> ListDossier();
	public List<Dossier> ListDossierRespo(int id);
	public void deleteDoc(String idDossiere);
	public Dossier getDoc(String idRespo);
	public void editDossier(Dossier nouveauDossier);
	public List<Dossier> ListDossierClient(String nom_cl);
	public Dossier getDosser(int idD);
	public String getIdRandom(int nbr,boolean b);
	int getAllclient();
	String getNomCl(int id_dossier);
	int getRegistredclient();
	String getEmail(int id_doc);

}
