package com.iscbrazil.printerlog.dao;

public class Factory {

    public static UserDAO createUserDAO () {
        return new MysqlUserDAO();
    }

    public static PrinterDAO createPrinterDAO() {
        return new MysqlPrinterDAO();
    }

}
