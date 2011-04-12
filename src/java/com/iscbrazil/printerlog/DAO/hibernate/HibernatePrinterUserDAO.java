package com.iscbrazil.printerlog.DAO.hibernate;

import com.iscbrazil.printerlog.DAO.PrinterUserDAO;
import com.iscbrazil.printerlog.model.PrinterUser;
import org.hibernate.Session;

/**
 * @version 2011.APR.12.01
 * @author edilson.ales
 */
public class HibernatePrinterUserDAO extends HibernateGenericDAO<PrinterUser> implements PrinterUserDAO {

    public HibernatePrinterUserDAO(Session session) {
        super(session);
    }


}
