package com.iscbrazil.printerlog.business;

import com.iscbrazil.printerlog.DAO.FactoryDAO;
import com.iscbrazil.printerlog.DAO.PrinterDAO;
import com.iscbrazil.printerlog.model.Printer;
import java.util.List;

/**
 * @version 2011.APR.29.01
 * @author edilson.ales
 */
public class PrinterService {

    private static PrinterService instance;

    private PrinterService() {}

    public static PrinterService getInstance() {
        if(instance == null) {
            instance = new PrinterService();
        }
        return instance;
    }

    public List<Printer> getAllOrdered() {

        FactoryDAO factory = FactoryDAO.getFactoryDAO();
        PrinterDAO printerDAO = factory.getPrinterDAO();
        List<Printer> printers = printerDAO.getAllOrdered();
        factory.shutTx();
        return printers;
    }

    public Printer getByName(String name) {
        
        FactoryDAO factory = FactoryDAO.getFactoryDAO();
        PrinterDAO printerDAO = factory.getPrinterDAO();
        Printer printer = printerDAO.getByName(name);
        factory.shutTx();
        return printer;
    }

    public void save(Printer printer) {
        FactoryDAO factory = FactoryDAO.getFactoryDAO();
        PrinterDAO printerDAO = factory.getPrinterDAO();
        factory.beginTx();
        printerDAO.save(printer);
        factory.shutTx();
    }

    public void addCounter(Long printerId) {
            
        FactoryDAO factory = FactoryDAO.getFactoryDAO();
        PrinterDAO printerDAO = factory.getPrinterDAO();
        factory.beginTx();
        Printer printer = printerDAO.getById(printerId);
        printer.setCounter((printer.getCounter())+ 1);
        factory.shutTx();
    }
}
