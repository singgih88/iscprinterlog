package com.iscbrazil.printerlog.business;

import com.iscbrazil.printerlog.DAO.FactoryDAO;
import com.iscbrazil.printerlog.DAO.PrinterUserDAO;
import com.iscbrazil.printerlog.model.PrinterUser;
import java.util.List;

/**
 * @version 2011.APR.20.01
 * @author edilson.ales
 */
public class PrinterUserService {

    public List<PrinterUser> getAllOrdered() {

        FactoryDAO factory = FactoryDAO.getFactoryDAO();
        PrinterUserDAO printerUserDAO = factory.getPrinterUserDAO();
        List<PrinterUser> users = printerUserDAO.getAllOrdered();
        factory.shutTx();
        return users;
    }
    
    public PrinterUser getByLogin(String login) {
        
        FactoryDAO factory = FactoryDAO.getFactoryDAO();
        PrinterUserDAO printerUserDAO = factory.getPrinterUserDAO();
        PrinterUser user = printerUserDAO.getByLogin(login);
        factory.shutTx();
        return user;
    }

    public void save(PrinterUser printerUser) {
        FactoryDAO factory = FactoryDAO.getFactoryDAO();
        PrinterUserDAO printerUserDAO = factory.getPrinterUserDAO();
        factory.beginTx();
        printerUserDAO.save(printerUser);
        factory.shutTx();
    }

    public void addCounter(Long printerUserId) {

        FactoryDAO factory = FactoryDAO.getFactoryDAO();
        PrinterUserDAO printerUserDAO = factory.getPrinterUserDAO();
        factory.beginTx();
        PrinterUser user = printerUserDAO.getById(printerUserId);
        user.setCounter((user.getCounter()) + 1);
        factory.shutTx();
    }
}
