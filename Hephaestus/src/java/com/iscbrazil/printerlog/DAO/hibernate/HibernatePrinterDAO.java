package com.iscbrazil.printerlog.DAO.hibernate;

import com.iscbrazil.printerlog.DAO.PrinterDAO;
import com.iscbrazil.printerlog.model.Printer;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * @version 2011.APR.29.01
 * @author edilson.ales
 */
public class HibernatePrinterDAO extends HibernateGenericDAO<Printer> implements PrinterDAO {

    public HibernatePrinterDAO(Session session) {
        super(session);
    }

    @Override
    public Printer getByName(String name) {
        
        Query query = this.getSession().createQuery("from Printer where name = :printerName");
        query.setParameter("printerName", name);
        query.setMaxResults(1);
        return (Printer) query.uniqueResult();
    }
}
