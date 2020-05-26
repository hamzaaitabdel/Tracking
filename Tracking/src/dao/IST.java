package dao;

import java.util.List;



import entities.SousTache;

public interface IST {
	
	public void addST(SousTache ST);
	public List<SousTache> ListST(int id);
	public void deleteST(int idT2);
	public int NumST(int id);
	void deleteSTdbsh(int idT2);
	public void deleteDocST(String idDossiere);

}
