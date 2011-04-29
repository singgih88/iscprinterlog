package com.iscbrazil.printerlog.DAO;

import com.iscbrazil.printerlog.model.Print;

/**
 * @version 2011.APR.29.01
 * @author edilson.ales
 */
public interface PrintDAO extends GenericDAO<Print> {

    public boolean validateFile(String fileName);

    public String getLastFileUploaded();

    public Long getFilteredPrints(int year, String month, int printerUserId);
}
