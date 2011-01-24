package com.iscbrazil.printerlog.model.factory;

import com.iscbrazil.printerlog.model.interfaces.UserDAO;
import com.iscbrazil.printerlog.model.interfaces.PrinterDAO;
import com.iscbrazil.printerlog.model.mysql.MysqlPrinterDAO;
import com.iscbrazil.printerlog.model.mysql.MysqlUserDAO;

public class Factory {

    public static UserDAO createUserDAO () {
        return new MysqlUserDAO();
    }

    public static PrinterDAO createPrinterDAO() {
        return new MysqlPrinterDAO();
    }

}
