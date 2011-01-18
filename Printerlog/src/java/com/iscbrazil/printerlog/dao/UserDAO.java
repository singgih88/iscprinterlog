package com.iscbrazil.printerlog.dao;

import com.iscbrazil.printerlog.pojo.User;
import java.util.List;

public interface UserDAO {

    public void save (User user);
    public void delete (User user);
    public List<User> listPrints ();
    public List<User> listAll ();
    public User find (String login);

}
