package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Type;

public class TypeImpl implements Itypes{

	@Override
	public List<Type> getTypes() {
		List<Type> cmd=new ArrayList();
		Connection conn=DBconnect.getConnection();
		try {
			PreparedStatement st=conn.prepareStatement("select * from doctypes");  
			ResultSet rs=st.executeQuery();
			while(rs.next()){
				Type t=new Type(rs.getInt("id"),rs.getString("type"),rs.getInt("respo1"),rs.getInt("respo2"),
						rs.getInt("respo3"),rs.getInt("respo4"));
				cmd.add(t);
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cmd;
	}

	@Override
	public int[] IdRespo(String type) {
		// TODO Auto-generated method stub
		Connection conn=DBconnect.getConnection();
		int[] n=new int[4];
		int i=0;
		try {
			PreparedStatement st=conn.prepareStatement("select * from doctypes where type=?");  
			st.setString(1, type);
			ResultSet rs=st.executeQuery();
			if(rs.next()){
				n[i++]=rs.getInt("respo1");
				n[i++]=rs.getInt("respo2");
				n[i++]=rs.getInt("respo3");
				n[i++]=rs.getInt("respo4");
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}

}
