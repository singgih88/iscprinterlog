package model.factory;

import model.interfaces.UserDAO;
import model.interfaces.PrinterDAO;
import model.mysql.MysqlPrinterDAO;
import model.mysql.MysqlUserDAO;

public class Factory {

    public static UserDAO createUserDAO () {
        return new MysqlUserDAO();
    }

    public static PrinterDAO createPrinterDAO() {
        return new MysqlPrinterDAO();
    }

}
