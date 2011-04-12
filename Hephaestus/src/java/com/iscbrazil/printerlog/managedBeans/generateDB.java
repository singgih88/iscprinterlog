package com.iscbrazil.printerlog.managedBeans;

import com.iscbrazil.printerlog.model.Printer;
import com.iscbrazil.printerlog.util.HibernateUtil;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

/**
 * @version 2011.APR.11.01
 * @author edilson.ales
 */
@ManagedBean
@RequestScoped
public class generateDB {

    public String getPrinters() {
        Session session = HibernateUtil.getSession();
        try {
            List<Printer> printers = session.createCriteria(Printer.class).addOrder(Order.desc("id")).list();
            return "foi fera?";
        } finally {
            session.close();
        }
    }
    
}
