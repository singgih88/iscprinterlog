package com.iscbrazil.printerlog.dao;

public class Factory {

    public static UserDAO createUserDAO () {
        return new MysqlUserDAO();
    }

}
