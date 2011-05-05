package com.iscbrazil.printerlog.managedBeans;

import com.iscbrazil.printerlog.business.PrintService;
import com.iscbrazil.printerlog.business.PrinterUserService;
import com.iscbrazil.printerlog.model.UserReport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * @version 2011.MAY.04.01
 * @author edilson.ales
 */
@ManagedBean
@SessionScoped
public class PrintBean implements Serializable {

    private List<String> schoolYearsSelected;
    private List<String> monthsSelected;
    private List<String> schoolYears;
    private List<String> months;
    private List<UserReport> userReports;

    public PrintBean() {
        this.monthsSelected = new ArrayList<String>();
        this.schoolYearsSelected = new ArrayList<String>();
        this.userReports = new ArrayList<UserReport>();
    }

    public List<String> getSchoolYearsSelected() {
        if (this.schoolYearsSelected.contains("All")) {
            this.schoolYearsSelected.clear();
            this.schoolYearsSelected.addAll(this.getSchoolYears());
            this.schoolYearsSelected.remove(0);
        }
        return schoolYearsSelected;
    }

    public void setSchoolYearsSelected(List<String> schoolYearsSelected) {
        this.schoolYearsSelected = schoolYearsSelected;
    }

    public List<String> getSchoolYears() {
        if (this.schoolYears == null || this.schoolYears.isEmpty()) {
            this.schoolYears = PrintService.getInstance().populateschoolYears(new ArrayList<String>());
        }
        return schoolYears;
    }

    public void setSchoolYears(List<String> schoolYears) {
        this.schoolYears = schoolYears;
    }

    public List<String> getMonthsSelected() {
        if (this.monthsSelected.contains("All")) {
            this.monthsSelected.clear();
            this.monthsSelected.addAll(this.getMonths());
            this.monthsSelected.remove(0);
        }
        return monthsSelected;
    }

    public void setMonthsSelected(List<String> monthsSelected) {
        this.monthsSelected = monthsSelected;
    }

    public List<String> getMonths() {
        if (this.months == null || this.months.isEmpty()) {
            this.months = PrintService.getInstance().populateMonths(new ArrayList<String>());
        }
        return months;
    }

    public void setMonths(List<String> months) {
        this.months = months;
    }

    public List<UserReport> getUserReports() {

        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("printerUserId");
        this.userReports.clear();
        PrintService printService = PrintService.getInstance();
        String name = PrinterUserService.getInstance().getById(Long.valueOf(id)).getName();
        if((getSchoolYearsSelected()) == null || (getSchoolYearsSelected()).isEmpty()) {
            this.schoolYearsSelected.add("All");
        }
        if((getMonthsSelected()) == null || (getMonthsSelected()).isEmpty()) {
            this.monthsSelected.add("All");
        }
        for (String sys : getSchoolYearsSelected()) {
            for (String m : getMonthsSelected()) {
                this.userReports.add(new UserReport(sys, Integer.valueOf(id), name, m, printService.getFilteredPrints(sys, m, Integer.valueOf(id))));
            }
        }
        return userReports;
    }

    public void setUserReports(List<UserReport> userReports) {
        this.userReports = userReports;
    }
}
