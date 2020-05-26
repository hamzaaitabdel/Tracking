package dao;
import java.util.List;

import entities.AdditionalDoc;
public interface IadditionalDoc {

	public List<AdditionalDoc> additionalDoc();
	public void removeRequest(int id);
	public void removeRequestDossier(int id);
	void addAdditionalDoc(AdditionalDoc doc);
	AdditionalDoc getadditionalDoc(int id);
	
}
