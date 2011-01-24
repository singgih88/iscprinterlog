package com.iscbrazil.printerlog.model.interfaces;

import com.iscbrazil.printerlog.controller.pojo.Printer;
import java.util.List;

public interface PrinterDAO {

    public void save (Printer printer);
    public void delete (Printer printer);
    public List<Printer> listAll ();
    public Printer find (String printerName);

}
