package com.iscbrazil.printerlog.DAO.hibernate;

import com.iscbrazil.printerlog.DAO.PrinterUserDAO;
import com.iscbrazil.printerlog.model.PrinterUser;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * @version 2011.APR.19.01
 * @author edilson.ales
 */
public class HibernatePrinterUserDAO extends HibernateGenericDAO<PrinterUser> implements PrinterUserDAO {

    public HibernatePrinterUserDAO(Session session) {
        super(session);
    }
    
    @Override
    public PrinterUser getByLogin(String login) {
        Query query = this.getSession().createQuery("from PrinterUser where login = :userLogin");
        query.setParameter("userLogin", login);
        List users = query.list();
        PrinterUser u = new PrinterUser();
        try {
            for (Object aux : users) {
                u = (PrinterUser) aux;
            }
            return u;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }   
    }
}
