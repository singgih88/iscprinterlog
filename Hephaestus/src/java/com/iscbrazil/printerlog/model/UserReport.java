package com.iscbrazil.printerlog.model;

import java.util.List;

/**
 * @version 2011.APR.26.02
 * @author edilson.ales
 */

public class UserReport {

    private int id;
    private List<String> months;
    private List<String> schoolYear;
    private List<Long> printsMonth;
    private PrinterUser printerUser;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getMonths() {
        return months;
    }

    public void setMonths(List<String> months) {
        this.months = months;
    }

    public PrinterUser getPrinterUser() {
        return printerUser;
    }

    public void setPrinterUser(PrinterUser printerUser) {
        this.printerUser = printerUser;
    }

    public List<Long> getPrintsMonth() {
        return printsMonth;
    }

    public void setPrintsMonth(List<Long> printsMonth) {
        this.printsMonth = printsMonth;
    }

    public List<String> getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(List<String> schoolYear) {
        this.schoolYear = schoolYear;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserReport other = (UserReport) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id;
        return hash;
    }

}
