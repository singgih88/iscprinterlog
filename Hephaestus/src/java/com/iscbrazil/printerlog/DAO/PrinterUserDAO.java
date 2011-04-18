package com.iscbrazil.printerlog.DAO;

import com.iscbrazil.printerlog.model.PrinterUser;

/**
 * @version 2011.APR.12.01
 * @author edilson.ales
 */
public interface PrinterUserDAO extends GenericDAO<PrinterUser> {

    public PrinterUser getByLogin(String login);

}
