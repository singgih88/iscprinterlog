package com.iscbrazil.printerlog.DAO.hibernate;

import com.iscbrazil.printerlog.DAO.PrinterDAO;
import com.iscbrazil.printerlog.model.Printer;
import org.hibernate.Session;

/**
 * @version 2011.APR.12.01
 * @author edilson.ales
 */
public class HibernatePrinterDAO extends HibernateGenericDAO<Printer> implements PrinterDAO {

    public HibernatePrinterDAO(Session session) {
        super(session);
    }


}
