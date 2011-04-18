package com.iscbrazil.printerlog.business;

import com.iscbrazil.printerlog.DAO.FactoryDAO;
import com.iscbrazil.printerlog.DAO.PrinterUserDAO;
import com.iscbrazil.printerlog.model.PrinterUser;
import java.util.List;

/**
 * @version 2011.APR.13.01
 * @author edilson.ales
 */
public class PrinterUserService {

    public List<PrinterUser> getAll() {

        FactoryDAO factory = FactoryDAO.getFactoryDAO();
        PrinterUserDAO printerUserDAO = factory.getPrinterUserDAO();
        List<PrinterUser> users = printerUserDAO.getAll();
        factory.shutTx();
        return users;
    }
    

}
