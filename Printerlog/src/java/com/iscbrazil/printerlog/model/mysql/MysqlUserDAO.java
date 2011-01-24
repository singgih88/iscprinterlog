package model.mysql;

import model.connectionPool.ConnectionPool;
import model.interfaces.UserDAO;
import controller.pojo.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class MysqlUserDAO extends ConnectionPool implements UserDAO {

    private final String UserLogin = "user_login";
    private final String UserPrintCounter = "user_printCounter";
    private final String UserName = "user_name";
    private final String UserCategory = "user_category";
    private final String UserID = "user_id";

    @Override
    public void save(User user) {
        //SQL1 = "SELECT DISTINCT YEAR(`print_date`) FROM `print`;";
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<User> listAll() {

        List<User> users = new ArrayList<User>();
        String SQL1 = "SELECT `user_id`, `user_login`, `user_printCounter`, `user_name`, `user_category` FROM `printerlog`.`user` ORDER BY `user_printCounter` DESC;";

        try {
            ResultSet res = super.getResultSet(SQL1);

            while (res.next()) {
                User u = new User();
                u.setId(Integer.parseInt(res.getString(UserID)));
                u.setName(res.getString(UserName));
                u.setLogin(res.getString(UserLogin));
                u.setCategory(res.getString(UserCategory));
                u.setTotalPrint(Integer.parseInt(res.getString(UserPrintCounter)));
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
                u.setLogin(res.getString(UserLogin));
                u.setTotalPrint(Integer.parseInt(res.getString(UserPrintCounter)));
                u.setName(res.getString(UserName));
                u.setCategory(res.getString(UserCategory));
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

    @Override
    public List<String> listSchoolYears() {
        try {

            String SQL1 = "SELECT DISTINCT `print_schoolYear` FROM `print`";

            ResultSet res = super.getResultSet(SQL1);
            List<String> schoolYears = new ArrayList<String>();

            while (res.next()) {
                schoolYears.add(res.getString("print_schoolYear"));
            }
            return schoolYears;

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

    @Override
    public String getDetailedDataTime(String schoolYear, int month, String userLogin) {

        if (schoolYear == null || schoolYear.trim().equalsIgnoreCase("")) 
            return null;
        

        String SQL1 = "";

        if (month <= 0 || month > 12) {
            SQL1 = "select count(`print_page`) as sheets from print where `print_schoolYear` = '" + schoolYear + 
                   "' and print_login = (SELECT `user_id` FROM `user` WHERE `user_login` = '" + userLogin + "')";
        } else {
            SQL1 = "select count(`print_page`) as sheets from print where month(`print_date`) = "
                    + month + " and `print_schoolYear` = '" + schoolYear
                    + "' and print_login = (SELECT `user_id` FROM `user` WHERE `user_login` = '" + userLogin + "')";
        }

        try {

            ResultSet res = super.getResultSet(SQL1);
            String sheets = "";
            if(res.next()) {
                sheets = res.getString("sheets");
                return sheets;
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

    @Override
    public String getDetailedDataPrinter(String printerName, String userLogin) {
        if(printerName.trim().equalsIgnoreCase("") || printerName == null)
            return null;

        String SQL1 = "select count(`print_page`) as sheets from `print` where "
                      + "`print_printer` = (SELECT `printer_id` FROM `printer` WHERE `printer_name` = '" + printerName + "') and "
                      + "`print_login` = (SELECT `user_id` FROM `user` WHERE `user_login` = '" + userLogin + "')";

        try {
            ResultSet res = super.getResultSet(SQL1);
            String sheets = "";
            if(res.next()) {
                sheets = res.getString("sheets");
                return sheets;
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
