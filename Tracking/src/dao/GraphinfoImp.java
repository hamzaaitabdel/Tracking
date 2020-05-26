package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import entities.Graphinfo;

public class GraphinfoImp implements Igraphinfo {

	@Override
	public List<Graphinfo> infos(int max) {
		// TODO Auto-generated method stub
		List<Graphinfo> infos = new ArrayList();
		Connection conn=DBconnect.getConnection();
		int i=0;
		try {
			PreparedStatement st=conn.prepareStatement("select DISTINCT date_add as date , count(id_doc)  from dossier GROUP BY date desc");  
			ResultSet rs=st.executeQuery();
			
			while(rs.next()) {
				if(i>=max)break;
				else {
				Graphinfo f =new Graphinfo();
				String fullDate=rs.getString("date");
				String date[]=fullDate.split("-");
				Month month = Month.of( Integer.parseInt(date[1]));
				f.setDay(Integer.parseInt(date[2]));
				f.setMonth(month.toString());
				f.setOperations(rs.getInt("count(id_doc)"));
				System.out.println(fullDate);
				infos.add(f);
				i++;
				System.out.println(i);
				}
			}
			Graphinfo.count=0;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return infos;
	}
	@Override
	public String[] listToArray(List l) {
		String res[]=new String[l.size()];
		for(int i=0;i<l.size();i++) {
			res[i]=(String) l.get(i);
		}
		return res;
		}

}
