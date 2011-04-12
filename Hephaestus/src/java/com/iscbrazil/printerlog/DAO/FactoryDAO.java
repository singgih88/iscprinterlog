package com.iscbrazil.printerlog.DAO;

import com.iscbrazil.printerlog.DAO.hibernate.FactoryHibernateDAO;

/**
 * @version 2011.APR.12.01
 * @author edilson.ales
 */
public abstract class FactoryDAO {

    public static FactoryDAO getFactoryDAO() {
        return new FactoryHibernateDAO();
    }

    public abstract void beginTx();

    public abstract void cancelTx();

    public abstract void shutTx();

    public abstract PrinterUserDAO getPrinterUserDAO();

    public abstract PrinterDAO getPrinterDAO();

    public abstract PrintDAO getPrintDAO();
}
