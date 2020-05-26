package dao;

import entities.Clientmail;

public interface IClientmail {
	
	public void addCm(Clientmail cm);
	public String getMail(int id);
	public int getIdCM(String email);
	public void deleteEmail(String idClient);
	public void updateMail(int id_client, String email);
	public String getMailClient(int parseInt);
	public void editMail(int idM, String email);
	String reverse(String password);

}
