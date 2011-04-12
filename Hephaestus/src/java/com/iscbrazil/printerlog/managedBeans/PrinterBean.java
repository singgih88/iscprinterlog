package com.iscbrazil.printerlog.managedBeans;

import com.iscbrazil.printerlog.model.Printer;
import com.iscbrazil.printerlog.util.HibernateUtil;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

/**
 * @version 2011.APR.11.01
 * @author edilson.ales
 */
@ManagedBean
@RequestScoped
public class PrinterBean {

    @ManagedProperty(value="#{printerUserBean}")
    private PrinterUserBean printerUserBean;
    private Printer printerSelected;
    private List<Printer> printers;

    public List<Printer> getIndexedPrinters() {
        Session session = HibernateUtil.getSession();
        try {
            if(this.printers == null) {
                this.printers = session.createCriteria(Printer.class).addOrder(Order.desc("counter")).list();
            }
            return this.printers;
        } finally {
            session.close();
        }
    }

    public PrinterUserBean getPrinterUserBean() {
        return printerUserBean;
    }

    public void setPrinterUserBean(PrinterUserBean printerUserBean) {
        this.printerUserBean = printerUserBean;
    }

    public Printer getPrinterSelected() {
        return printerSelected;
    }

    public void setPrinterSelected(Printer printerSelected) {
        this.printerSelected = printerSelected;
    }

    public List<Printer> getPrinters() {
        return printers;
    }

    public void setPrinters(List<Printer> printers) {
        this.printers = printers;
    }

}
