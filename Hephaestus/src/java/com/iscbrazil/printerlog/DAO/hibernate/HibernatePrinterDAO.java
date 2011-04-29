package com.iscbrazil.printerlog.DAO.hibernate;

import com.iscbrazil.printerlog.DAO.PrinterDAO;
import com.iscbrazil.printerlog.model.Printer;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

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

    @Override
    public List<Printer> getAllOrdered() {
        return this.getSession().createCriteria(Printer.class).addOrder(Order.desc("counter")).list();
    }
}
