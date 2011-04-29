package com.iscbrazil.printerlog.DAO.hibernate;

import com.iscbrazil.printerlog.DAO.PrinterUserDAO;
import com.iscbrazil.printerlog.model.PrinterUser;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

/**
 * @version 2011.APR.29.01
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
        query.setMaxResults(1);
        return (PrinterUser) query.uniqueResult();
    }

    @Override
    public List<PrinterUser> getAllOrdered() {
        return this.getSession().createCriteria(PrinterUser.class).addOrder(Order.desc("counter")).list();
    }
}
