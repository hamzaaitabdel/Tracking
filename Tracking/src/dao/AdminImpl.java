package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Admin;

public class AdminImpl implements IAdmin{

	@Override
	public List<Admin> Admines() {
		// TODO Auto-generated method stub
		List<Admin>admines=new ArrayList<Admin>();
		Connection conn =DBconnect.getConnection();
		try {
			PreparedStatement st= conn.prepareStatement("select * from admin asc");
			ResultSet rs=st.executeQuery();
			while(rs.next()) {
				Admin admin = new Admin();
				admin.setId_admin(rs.getInt("id_admin"));
				admin.setNom(rs.getString("nom"));
				admin.setEmail(rs.getString("email"));
				admin.setPhoto(rs.getString("photo"));
				admin.setPassword(rs.getString("password"));
				admines.add(admin);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return admines;
	}
	@Override
	public boolean loginAdmin(String mail, String mdp) {
		Connection conn=DBconnect.getConnection();
		boolean b=false;
		try {
			PreparedStatement st=conn.prepareStatement("select password from admin where email=?");
			st.setString(1, mail);
		ResultSet rs=st.executeQuery();
		
		if(rs.next() && rs.getString("password").equals(mdp)){
			b=true;
			System.out.println("admin loged in :");
		}
		
	}catch (SQLException e) {
		e.printStackTrace();
	}
		return b;
	}
	@Override
	public Admin getAdmin(String email,String password) {
		Admin admin=new Admin();
		Connection conn=DBconnect.getConnection();
		try {
			PreparedStatement st=conn.prepareStatement("select * from admin where email=? and password=?");
			st.setString(1, email);
			st.setString(2, password);
		ResultSet rs=st.executeQuery();
		if(rs.next()) {
		admin.setId_admin(rs.getInt("id_admin"));
		admin.setNom(rs.getString("nom"));
		admin.setEmail(rs.getString("email"));
		admin.setPhoto(rs.getString("photo"));
		admin.setPassword(rs.getString("password"));
		}
	}catch (SQLException e) {
		e.printStackTrace();
	}
		return admin;
	}

}
