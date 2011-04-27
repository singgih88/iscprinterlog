package com.iscbrazil.printerlog.DAO.hibernate;

import com.iscbrazil.printerlog.DAO.PrinterDAO;
import com.iscbrazil.printerlog.model.Printer;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * @version 2011.APR.27.01
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
        List printers = query.list();
        Printer u = new Printer();
        try {
            for (Object aux : printers) {
                u = (Printer) aux;
            }
            return u;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
