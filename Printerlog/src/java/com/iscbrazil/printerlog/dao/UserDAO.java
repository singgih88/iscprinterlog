package com.iscbrazil.printerlog.dao;

import com.iscbrazil.printerlog.pojo.User;
import java.util.List;

public interface UserDAO {

    public void save (User user);
    public void delete (User user);
    //public List<User> listUsersPrints ();
    public List<User> listAll ();
    public User find (String login);
    public List<String> listSchoolYears();
    public String getDetailedDataTime(String schoolYear, int month, String userLogin);
    public String getDetailedDataPrinter(String printerName, String userLogin);

}
