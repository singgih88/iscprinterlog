package com.iscbrazil.printerlog.model.connectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionPool {

    private Connection con = null;

    protected Connection getConnection() throws ClassNotFoundException, SQLException, NamingException {

        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("printerlog_db");
            con = ds.getConnection();
            return con;
        } catch (SQLException exc) {
            throw new SQLException("An error occured on SQL Statment");
        } catch (NamingException exc) {
            throw new NamingException();
        }
    }

    protected void closeConnection() throws SQLException {
        if (con == null) {
            return;
        }
        con.close();
        con = null;
    }

    protected ResultSet getResultSet(String SQL1) throws SQLException, NamingException, ClassNotFoundException {

        Statement stmt = getConnection().createStatement();
        ResultSet res = stmt.executeQuery(SQL1);
        return res;
    }

}
