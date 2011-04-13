package com.iscbrazil.printerlog.business;

import com.iscbrazil.printerlog.DAO.FactoryDAO;
import com.iscbrazil.printerlog.DAO.PrinterDAO;
import com.iscbrazil.printerlog.model.Printer;
import java.util.List;

/**
 * @version 2011.APR.13.01
 * @author edilson.ales
 */
public class PrinterService {

    public List<Printer> getAll() {

        FactoryDAO factory = FactoryDAO.getFactoryDAO();
        PrinterDAO printerDAO = factory.getPrinterDAO();
        List<Printer> printers = printerDAO.getAll();
        factory.shutTx();
        return printers;
    }

}
