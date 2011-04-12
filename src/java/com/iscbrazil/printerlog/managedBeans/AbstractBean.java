package com.iscbrazil.printerlog.managedBeans;

import com.iscbrazil.printerlog.util.HibernateUtil;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

@ManagedBean
@ViewScoped
public abstract class AbstractBean {

    public Object objSelected;
    public List<Object> objs;

    public List<Object> getObjects(Class a) {

        Session session = HibernateUtil.getSession();
        try {
            return session.createCriteria(a).addOrder(Order.desc("counter")).list();
        } finally {
            session.close();
        }
    }
}
