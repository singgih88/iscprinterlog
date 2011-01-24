package com.iscbrazil.printerlog.controller.pojo;

public class Month {

    public String monthLabel;
    public int monthValue;

    public Month(String monthLabel, int monthValue) {
        this.monthLabel = monthLabel;
        this.monthValue = monthValue;
    }

    public Month() {
    }

    public String getMonthLabel() {
        return monthLabel;
    }

    public void setMonthLabel(String monthLabel) {
        this.monthLabel = monthLabel;
    }

    public int getMonthValue() {
        return monthValue;
    }

    public void setMonthValue(int monthValue) {
        this.monthValue = monthValue;
    }
}
