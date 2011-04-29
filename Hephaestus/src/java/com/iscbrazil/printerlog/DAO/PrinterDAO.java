package com.iscbrazil.printerlog.DAO;

import com.iscbrazil.printerlog.model.Printer;
import java.util.List;

/**
 * @version 2011.APR.29.01
 * @author edilson.ales
 */
public interface PrinterDAO extends GenericDAO<Printer> {

    public Printer getByName(String name);

    public List<Printer> getAllOrdered();

}
