package dao;

import java.util.List;





import entities.Responsable;

public interface IResponsable {

	Responsable getRespo(String mail, String mdp);
	boolean login(String mail, String mdp);
	public void addRespo(Responsable r);
	public List<Responsable> ListRespo();
	void deleteRespo(String idRespo);
	Responsable getRespo(String idRespo);
	void editRespo(Responsable nouveauRespo);
	void sendFromGMail(String from, String pass, String[] to, String subject,
			String body);
	List<Responsable> ListRespoMessage(int idD);
	void online(boolean statue, int ind_resp);
	int countRepoTache();
	void sendFromGMail(String from, String pass, String to, String subject, String body);

}
