package com.iscbrazil.printerlog.managedBeans;

import com.iscbrazil.printerlog.business.PrinterService;
import com.iscbrazil.printerlog.model.Printer;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * @version 2011.APR.29.01
 * @author edilson.ales
 */
@ManagedBean
@RequestScoped
public class PrinterBean {

    private Printer printerSelected;
    private List<Printer> printers;
    private PrinterService service = new PrinterService();

    public List<Printer> getAllPrinters() {

        if (this.printers == null) {
            this.printers = service.getAll();
        }
        return this.printers;

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
