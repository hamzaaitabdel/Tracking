package dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Client;
import entities.Tache;

public class ClientImpl implements IClient{
	
	@Override
	public void addClient(Client c) {
		Connection conn=DBconnect.getConnection();
		try {
			PreparedStatement st=conn.prepareStatement("insert into client values (null,?,?,?,null,?,null,null,null,false)");
			st.setInt(1, c.getId_clientmail());
			st.setString(2, c.getNom());
			st.setString(3, c.getPassword());
			st.setString(4, c.getTel());
			
			st.executeUpdate();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}

	@Override
	public Client getClient(String id_c) {
		Client c=null;
		Connection conn=DBconnect.getConnection();
		try {
			PreparedStatement st=conn.prepareStatement("select * from Client where id_client=?");
			st.setString(1, id_c);
			ResultSet rs=st.executeQuery();
			if(rs.next()){
				c=new Client();
				c.setId_client(rs.getInt("Id_Client"));
				c.setId_clientmail(rs.getInt("id_clientmail"));
				c.setNom(rs.getString("Nom"));
				c.setPassword(rs.getString("Password"));
				c.setAdresse(rs.getString("adresse"));
				c.setTel(rs.getString("Tel"));
				c.setCarte(rs.getString("carte"));
				c.setCcv(rs.getInt("ccv"));
				c.setExpiry(rs.getString("expiry"));
				c.setPremium(rs.getBoolean("premium"));
				
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return c;
	}
	@Override
	public String getpassword(String email) {
		Connection conn=DBconnect.getConnection();
		String password="";
		try {
			PreparedStatement st=conn.prepareStatement("select Password from Client where id_clientmail in (select id_cm from clientmail where email = ?)");
			st.setString(1, email);
		ResultSet rs=st.executeQuery();
		
		if(rs.next()){
		   password=rs.getString("Password");
		}
		
	}catch (SQLException e) {
	}
		return password;
	}
	@Override
	public boolean login(String email, String mdp) {
		Connection conn=DBconnect.getConnection();
		boolean b=false;
		try {
			PreparedStatement st=conn.prepareStatement("select Password from Client where id_clientmail in (select id_cm from clientmail where email = ?)");
			st.setString(1, email);
		ResultSet rs=st.executeQuery();
		
		if(rs.next() && rs.getString("Password").equals(mdp)){
			b=true;
		}
		
	}catch (SQLException e) {
	}
		return b;
	}

	@Override
	public Client getClient(String id_clientmail, String mdp) {
		Client c=null;
		int id=Integer.parseInt(id_clientmail);
		Connection conn=DBconnect.getConnection();
		try {
			PreparedStatement st=conn.prepareStatement("select * from Client where id_clientmail=? and password = ?");
			st.setInt(1, id);
			st.setString(2, mdp);
			ResultSet rs=st.executeQuery();
			if(rs.next()){
				c=new Client();
				c.setId_client(rs.getInt("Id_Client"));
				c.setId_clientmail(rs.getInt("id_clientmail"));
				c.setNom(rs.getString("Nom"));
				c.setPassword(rs.getString("Password"));
				c.setAdresse(rs.getString("adresse"));
				c.setTel(rs.getString("Tel"));
				c.setCarte(rs.getString("carte"));
				c.setCcv(rs.getInt("ccv"));
				c.setExpiry(rs.getString("expiry"));
				c.setPremium(rs.getBoolean("premium"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return c;
	}
	@Override
	public Client getClientByEmail(String email) {
		Connection conn=DBconnect.getConnection();
		Client c=new Client();
		try {
			PreparedStatement st=conn.prepareStatement("select * from Client where id_clientmail in (select id_cm from clientmail where email =?)");
			st.setString(1, email);
		ResultSet rs=st.executeQuery();
		
		if(rs.next()){
			c.setId_client(rs.getInt("id_client"));
			c.setId_clientmail(rs.getInt("id_clientmail"));
			c.setNom(rs.getString("nom"));
			c.setPassword(rs.getString("password"));
			c.setAdresse(rs.getString("adresse"));
			c.setTel(rs.getString("tel"));
			c.setCarte(rs.getString("carte"));
			c.setExpiry(rs.getString("expiry"));
			c.setCcv(rs.getInt("ccv"));
			c.setPremium(rs.getBoolean("premium"));
		}
		
	}catch (SQLException e) {
	}
		return c;
	}
	@Override
	public boolean exist(String email) {
		Connection conn=DBconnect.getConnection();
		boolean b=false;
		try {
			PreparedStatement st=conn.prepareStatement("select * from Client where id_clientmail in (select id_cm from clientmail where email =?)");
			st.setString(1, email);
		ResultSet rs=st.executeQuery();
		
		if(rs.next()){
			b=true;
		}
		
	}catch (SQLException e) {
	}
		return b;
	}

	@Override
	public void updateClient(int id, String ad, String numcarte,
			int cc, String expiry,boolean prem) {
		Connection conn=DBconnect.getConnection(); 
		try {
			PreparedStatement st=conn.prepareStatement("UPDATE client SET adresse = ?,carte = ?,ccv = ?,expiry = ?,premium=? where id_client = ?");
			
			st.setString(1, ad);
			st.setString(2,numcarte);
			st.setInt(3, cc);
			st.setString(4, expiry);
			st.setBoolean(5, prem);
			st.setInt(6, id);
			st.executeUpdate();
			st.close();
			}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void deleteClient(String idClient) {
		Connection conn=DBconnect.getConnection();
		int id = Integer.parseInt(idClient);
		try {
			PreparedStatement st=conn.prepareStatement("delete from client where id_client=? ");
			st.setInt(1,id);
			st.executeUpdate();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Client> ListClient() {
		List<Client> cmd = new ArrayList();
		Connection conn=DBconnect.getConnection();
		
		try {
			PreparedStatement st=conn.prepareStatement("select * from Client"); 
			ResultSet rs=st.executeQuery();
			while(rs.next()){
				Client c=new Client();
				c.setId_client(rs.getInt("id_client"));
				c.setId_clientmail(rs.getInt("id_clientmail"));
				c.setNom(rs.getString("nom"));
				c.setPassword(rs.getString("password"));
				c.setAdresse(rs.getString("adresse"));
				c.setTel(rs.getString("tel"));
				c.setCarte(rs.getString("carte"));
				c.setExpiry(rs.getString("expiry"));
				c.setCcv(rs.getInt("ccv"));
				c.setPremium(rs.getBoolean("premium"));
				
				cmd.add(c);
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cmd;
	}
 
	
	@Override
	public String sms(String number,String msg) {
		try {
			// Construct data
			String apiKey = "apikey=" + "b7kymC+mP6w-3xymyyVOMmpWBGkhEyMVOqnryi2tb3 ";
			String message = "&message=" + msg;
			String sender = "&sender=" + "WEBTRACK";
			String numbers = "&numbers=" + number;
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();
			return stringBuffer.toString();
		} catch (Exception e) {
			System.out.println("Error SMS "+e);
			return "Error "+e;
		}
	
}

	@Override
	public void editProfile(int id, String adresse, String tel, String password) {
		Connection conn=DBconnect.getConnection(); 
		try {
			PreparedStatement st=conn.prepareStatement("UPDATE client SET adresse = ?,tel = ?,password = ? where id_client = ?");
			
			st.setString(1, adresse);
			st.setString(2,tel);
			st.setString(3, password); 
			st.setInt(4, id);
			st.executeUpdate();
			st.close();
			}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void editPrem(String id_client) {
		Connection conn=DBconnect.getConnection(); 
		int id=Integer.parseInt(id_client);
		try {
			PreparedStatement st=conn.prepareStatement("UPDATE client SET premium = false where id_client = ?");


			st.setInt(1, id);
			st.executeUpdate();
			st.close();
			}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void editClient(int id_client, String nom, String password,
			String adresse, String tel) {
		Connection conn=DBconnect.getConnection(); 
		try {
			PreparedStatement st=conn.prepareStatement("UPDATE client SET nom = ?,password = ?,adresse = ?,tel = ? where id_client = ?");
			
			st.setString(1, nom);
			st.setString(2,password);
			st.setString(3, adresse);
			st.setString(4, tel);
			st.setInt(5, id_client);
			st.executeUpdate();
			st.close();
			}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
		
	}


