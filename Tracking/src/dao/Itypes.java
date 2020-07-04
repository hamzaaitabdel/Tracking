package dao;

import java.util.ArrayList;
import java.util.List;
import entities.Type;

public interface Itypes {
	public List<Type> getTypes();
	public int[] IdRespo(String type);
}
