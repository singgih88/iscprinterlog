package com.iscbrazil.printerlog.dao;

import com.iscbrazil.printerlog.pojo.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

class MysqlUserDAO extends ConnectionPool implements UserDAO {

    @Override
    public void save(User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<User> listPrints() {

        List<User> users = new ArrayList<User>();
        String SQL1 = "SELECT `user_login`, `user_printCounter` FROM `printerlog`.`user` ORDER BY `user_printCounter` DESC;";

        try {
            ResultSet res = super.getResultSet(SQL1);

            while (res.next()) {
                User u = new User();
                u.setLogin(res.getString("user_login"));
                u.setTotalPrint(Integer.parseInt(res.getString("user_printCounter")));
                users.add(u);
            }
            res.close();
            return users;

        } catch (Exception exc) {
            System.out.println("SQL error");
            users.add(null);
            return users;
        } finally {
            try {
                super.closeConnection();
            } catch (SQLException ex) {
                System.out.println("Error closing Mysql connection");
            }
        }


    }

    @Override
    public User find(String login) {

        try {
            if (login == null || login.trim().equalsIgnoreCase("")) {
                return null;
            }

            String SQL1 = "SELECT user_name, user_login, user_category, user_printCounter FROM `user` where `user_login` = '" + login + "'";
            ResultSet res = super.getResultSet(SQL1);

            if (res.next()) {

                User u = new User();
                u.setLogin(res.getString("user_login"));
                u.setTotalPrint(Integer.parseInt(res.getString("user_printCounter")));
                u.setName(res.getString("user_name"));
                u.setCategory(res.getString("user_category"));
                return u;
            }

            return null;

        } catch (SQLException ex) {
            return null; //Message reporting that user doesn't exist
        } catch (NamingException ex) {
            return null; //Message reporting that user doesn't exist
        } catch (ClassNotFoundException ex) {
            return null; //Message reporting that user doesn't exist
        } finally {
            try {
                super.closeConnection();
            } catch (SQLException ex) {
                System.out.println("Error closing Mysql connection");
            }
        }

    }
}
