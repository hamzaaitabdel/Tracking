package dao;
import java.util.ArrayList;
import java.util.List;

import entities.Graphinfo;
public interface Igraphinfo {

	public List<Graphinfo> infos(int max);

	String[] listToArray(List l);
	
    
}
