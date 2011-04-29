package com.iscbrazil.printerlog.model;

/**
 * @version 2011.APR.29.03
 * @author edilson.ales
 */
public class UserReport {

    private String schoolYear;
    private PrinterUser printerUser;
    private String month;
    private Long printsByMonth;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public PrinterUser getPrinterUser() {
        return printerUser;
    }

    public void setPrinterUser(PrinterUser printerUser) {
        this.printerUser = printerUser;
    }

    public Long getPrintsByMonth() {
        return printsByMonth;
    }

    public void setPrintsByMonth(Long printsByMonth) {
        this.printsByMonth = printsByMonth;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }
}
