package dao;

import java.util.List;

import entities.Ended;
import entities.Tache;

public interface IEnded {

	void add(Tache nouvelleTache);

	List<Ended> ListTachat(int idR);

	int countEnded(int id_respo);

	

}
