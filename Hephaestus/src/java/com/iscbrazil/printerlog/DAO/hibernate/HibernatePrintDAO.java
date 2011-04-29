package com.iscbrazil.printerlog.DAO.hibernate;

import com.iscbrazil.printerlog.DAO.PrintDAO;
import com.iscbrazil.printerlog.model.Print;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * @version 2011.APR.29.01
 * @author edilson.ales
 */
public class HibernatePrintDAO extends HibernateGenericDAO<Print> implements PrintDAO {

    public HibernatePrintDAO(Session session) {
        super(session);
    }
    
    @Override
    public boolean validateFile(String fileName) {
        Query query = this.getSession().createQuery("from Print where fileName = :fileName");
        query.setParameter("fileName", fileName);
        List fileNames = query.list();
        
        if (fileNames.size() > 0) {
            return false;
        } else {
            return true;
        }

    }

    @Override
    public String getLastFileUploaded() {
        Query query = this.getSession().createQuery("select fileName from Print where (select max(printDate) from Print) = printDate");
        query.setMaxResults(1);
        return (String) query.uniqueResult();
    }

    @Override
    public Long getFilteredPrints(int year, String month, int printerUserId) {
        Query query = this.getSession().createQuery("select count(*) from Print where year(printDate) = :year and "
                                                  + "month(printDate) = :month and printerUserId = :printerUserId");
        query.setParameter("year", year);
        query.setParameter("month", month);
        query.setParameter("printerUserId", printerUserId);
        query.setMaxResults(1);
        return (Long) query.uniqueResult();
    }
}
