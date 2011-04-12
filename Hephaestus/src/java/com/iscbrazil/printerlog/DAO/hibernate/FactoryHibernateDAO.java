package com.iscbrazil.printerlog.DAO.hibernate;

import com.iscbrazil.printerlog.DAO.FactoryDAO;
import com.iscbrazil.printerlog.DAO.PrintDAO;
import com.iscbrazil.printerlog.DAO.PrinterDAO;
import com.iscbrazil.printerlog.DAO.PrinterUserDAO;
import com.iscbrazil.printerlog.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @version 2011.APR.12.01
 * @author edilson.ales
 */
public class FactoryHibernateDAO extends FactoryDAO {

    private Session session;
    private Transaction tx;

    public FactoryHibernateDAO() {
        this.session = HibernateUtil.getSession();
    }

    @Override
    public void beginTx() {
        this.tx = this.session.beginTransaction();
    }

    @Override
    public void cancelTx() {
        this.tx.rollback();
        this.tx = null;
    }

    @Override
    public void shutTx() {
        if (this.tx != null) {
            this.tx.commit();
        }
        this.session.close();
    }

    @Override
    public PrinterUserDAO getPrinterUserDAO() {
        return new HibernatePrinterUserDAO(this.session);
    }

    @Override
    public PrinterDAO getPrinterDAO() {
        return new HibernatePrinterDAO(this.session);
    }

    @Override
    public PrintDAO getPrintDAO() {
        return new HibernatePrintDAO(this.session);
    }
}
