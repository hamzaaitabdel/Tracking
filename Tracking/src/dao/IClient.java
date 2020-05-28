package dao;

import java.util.List;

import entities.Client;

public interface IClient {
	public int getPremiumCount();
	public void addClient(Client c);
	public Client getClient(String id_c);
	public boolean login(String email, String mdp);
	public boolean exist(String email);
	public Client getClient(String email, String mdp);
	public void updateClient(int id, String ad, String zip, int cc, String expiry, boolean prem);
	public void deleteClient(String idClient);
	public List<Client> ListClient(); 
	String sms(String number, String msg);
	public void editProfile(int id, String adresse, String tel, String password);
	public void editPrem(String id_client);
	public void editClient(int id_client, String nom, String password,
			String adresse, String tel);
	String getpassword(String email);
	Client getClientByEmail(String email);

}
