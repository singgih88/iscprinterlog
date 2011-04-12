package com.iscbrazil.printerlog.DAO.hibernate;

import com.iscbrazil.printerlog.DAO.PrintDAO;
import com.iscbrazil.printerlog.model.Print;
import org.hibernate.Session;

/**
 * @version 2011.APR.12.01
 * @author edilson.ales
 */
public class HibernatePrintDAO extends HibernateGenericDAO<Print> implements PrintDAO {

    public HibernatePrintDAO(Session session) {
        super(session);
    }


}
