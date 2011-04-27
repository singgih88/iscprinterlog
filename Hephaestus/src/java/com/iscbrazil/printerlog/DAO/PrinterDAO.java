package com.iscbrazil.printerlog.DAO;

import com.iscbrazil.printerlog.model.Printer;

/**
 * @version 2011.APR.27.01
 * @author edilson.ales
 */
public interface PrinterDAO extends GenericDAO<Printer> {

    public Printer getByName(String name);

}
