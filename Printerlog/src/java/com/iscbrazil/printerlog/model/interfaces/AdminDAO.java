package model.interfaces;

import controller.pojo.Admin;
import java.util.List;

public interface AdminDAO {

    public void save (Admin admin);
    public void delete (Admin admin);
    public List<Admin> listAll ();
    public Admin find (String adminName);
}
