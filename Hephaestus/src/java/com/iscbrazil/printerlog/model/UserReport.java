package com.iscbrazil.printerlog.model;

/**
 * @version 2011.MAY.03.02
 * @author edilson.ales
 */
public class UserReport {

    private String schoolYear;
    private int printerUser;
    private String printerUserName;
    private String month;
    private Long printsByMonth;

    public UserReport(String schoolYear, int printerUser, String printerUserName, String month, Long printsByMonth) {
        this.schoolYear = schoolYear;
        this.printerUser = printerUser;
        this.printerUserName = printerUserName;
        this.month = month;
        this.printsByMonth = printsByMonth;
    }

    public UserReport() {
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getPrinterUser() {
        return printerUser;
    }

    public void setPrinterUser(int printerUser) {
        this.printerUser = printerUser;
    }

    public String getPrinterUserName() {
        return printerUserName;
    }

    public void setPrinterUserName(String printerUserName) {
        this.printerUserName = printerUserName;
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
