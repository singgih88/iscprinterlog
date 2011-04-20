package com.iscbrazil.printerlog.DAO;

import com.iscbrazil.printerlog.model.PrinterUser;
import java.util.List;

/**
 * @version 2011.APR.20.01
 * @author edilson.ales
 */
public interface PrinterUserDAO extends GenericDAO<PrinterUser> {

    public PrinterUser getByLogin(String login);

    public List<PrinterUser> getAllOrdered();

}
