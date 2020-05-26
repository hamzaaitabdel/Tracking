package dao;

import java.util.List;

import entities.Saved;

public interface ISaved {

	List<Saved> ListSaved(int id);

	void deleteSaved(String id_doc, String id_client);

	void addSaved(String id_doc, String id_client, String nomClient, String typa);

	boolean exist(int id_client, int id_doc);

	int countSaved(int id);

}
