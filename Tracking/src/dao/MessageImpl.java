package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Dossier;
import entities.Messenger;

public class MessageImpl implements IMessage {

	@Override
	public List<Integer> ListMsgRespo(int idR) {
		List<Integer> cmd = new ArrayList();
		Connection conn=DBconnect.getConnection();
		int m=0;
		try {
			PreparedStatement st=conn.prepareStatement("select DISTINCT id_doc from Messenger where id_respo = ?");  
			st.setInt(1, idR);
			ResultSet rs=st.executeQuery();
			while(rs.next()){
				m= rs.getInt("id_doc");
				cmd.add(m);
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cmd;
	}
	
	@Override
	public List<Messenger> ListMsg(int idD) {
		List<Messenger> cmd = new ArrayList();
		Connection conn=DBconnect.getConnection();
		try {
			PreparedStatement st=conn.prepareStatement("select * from Messenger where id_doc = ? order by id_mess ASC");  
			st.setInt(1, idD);
			ResultSet rs=st.executeQuery();
			while(rs.next()){
				Messenger m=new Messenger();
				m.setId_mess(rs.getInt("id_mess"));
				m.setId_doc(rs.getInt("id_doc"));
				m.setId_respo(rs.getInt("id_respo"));
				m.setmessage(rs.getString("message"));
				m.setAttachement(rs.getBlob("attachement"));
				m.setDat(rs.getString("dat"));
				cmd.add(m);
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cmd;
	}

	@Override
	public void addMsgRespo(Messenger msg) {
		Connection conn=DBconnect.getConnection();
		try {
			PreparedStatement st=conn.prepareStatement("insert into messenger values (null,?,?,?,?,?)");
			

			st.setInt(1, msg.getId_doc());
			st.setInt(2, msg.getId_respo());
			st.setString(3, msg.getmessage());
			st.setBlob(4, msg.getAttachement());
			st.setString(5, msg.getDat());
			st.executeUpdate();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}

	@Override
	public Messenger getMsg(String id_mess) {
		Messenger m = new Messenger();
		int id = Integer.parseInt(id_mess);
		Connection conn=DBconnect.getConnection();
		try {
			PreparedStatement st=conn.prepareStatement("select * from Messenger where id_mess = ?");  
			st.setInt(1, id);
			ResultSet rs=st.executeQuery();
			while(rs.next()){
				m.setId_mess(rs.getInt("id_mess"));
				m.setId_doc(rs.getInt("id_doc"));
				m.setId_respo(rs.getInt("id_respo"));
				m.setmessage(rs.getString("message"));
				m.setAttachement(rs.getBlob("attachement"));
				m.setDat(rs.getString("dat"));
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return m;
	}

	@Override
	public void deleteDbl3ani(String id_mess, String msg) {
		Connection conn=DBconnect.getConnection(); 
		int id = Integer.parseInt(id_mess);
		try {
			PreparedStatement st=conn.prepareStatement("UPDATE Messenger SET message = ?  where id_mess = ?");
			st.setString(1, msg);
			st.setInt(2, id);
			st.executeUpdate();
			st.close();
			}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
