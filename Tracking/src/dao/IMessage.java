package dao;

import java.util.List;

import entities.Messenger;

public interface IMessage {

	List<Integer> ListMsgRespo(int idR);

	List<Messenger> ListMsg(int idD);

	void addMsgRespo(Messenger msg);

	Messenger getMsg(String id_mess);

	void deleteDbl3ani(String id_mess, String msg);

}
