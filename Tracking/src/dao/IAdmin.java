package dao;

import java.util.List;

import entities.Admin;

public interface IAdmin {
   public List<Admin> Admines();

boolean loginAdmin(String mail, String mdp);

Admin getAdmin(String email, String password);
}
