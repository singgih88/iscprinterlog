package com.iscbrazil.printerlog.DAO;

import com.iscbrazil.printerlog.model.Print;

/**
 * @version 2011.APR.28.01
 * @author edilson.ales
 */
public interface PrintDAO extends GenericDAO<Print> {

    public boolean validateFile(String fileName);

    public String getLastFileUploaded();
}
