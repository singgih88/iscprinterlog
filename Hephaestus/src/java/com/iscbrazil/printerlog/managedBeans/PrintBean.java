package com.iscbrazil.printerlog.managedBeans;

import com.iscbrazil.printerlog.business.PrintService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 * @version 2011.APR.20.01
 * @author edilson.ales
 */
@ManagedBean
@SessionScoped
public class PrintBean implements Serializable {

    private String lastFileUploaded;
    private String schoolYear;
    private String month;
    private List<String> schoolYears;
    private List<String> months;

    public PrintBean() {
        this.schoolYear = "2007-2008";
        this.month = "";
    }

    public void handleFileUpload(FileUploadEvent event) {
        UploadedFile file = event.getFile();
        this.lastFileUploaded = file.getFileName();
        PrintService printService = new PrintService();
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage();

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(file.getInputstream()));
            String dirtyLine;

            while ((dirtyLine = in.readLine()) != null) {
                if ((msg = printService.processLine(dirtyLine)) != null) {
                    context.addMessage("formMaster:formMenu:growlMsg", msg);
                    return;
                }
            }
            context.addMessage("formMaster:formMenu:growlMsg", new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Well done!",
                    "File " + this.lastFileUploaded + " has been successfully uploaded!"));
        } catch (IOException ex) {
            context.addMessage("formMaster:formMenu:growlMsg", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Problem reading file source", ex.getMessage()));
        }
    }

    public String getLastFileUploaded() {
        return lastFileUploaded;
    }

    public void setLastFileUploaded(String lastFileUploaded) {
        this.lastFileUploaded = lastFileUploaded;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public List<String> getSchoolYears() {
        if (this.schoolYears == null || this.schoolYears.isEmpty()) {
            populateSchoolYears();
        }
        return schoolYears;
    }

    public void setSchoolYears(List<String> schoolYears) {
        this.schoolYears = schoolYears;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public List<String> getMonths() {
        if (this.months == null || this.months.isEmpty()) {
            populateMonths();
        }
        return months;
    }

    public void setMonths(List<String> months) {
        this.months = months;
    }

    protected void populateMonths() {
        this.months = new ArrayList<String>();
        this.months.add("");
        this.months.add("January");
        this.months.add("February");
        this.months.add("March");
        this.months.add("April");
        this.months.add("May");
        this.months.add("June");
        this.months.add("July");
        this.months.add("August");
        this.months.add("September");
        this.months.add("October");
        this.months.add("November");
        this.months.add("December");
    }

    protected void populateSchoolYears() {
        this.schoolYears = new ArrayList<String>();
        Calendar dt = new GregorianCalendar();
        int actual = dt.get(Calendar.YEAR);
        int y1;
        String sYear;
        for (int y2 = 2007; y2 < actual + 1; y2++) {
            y1 = y2 + 1;
            sYear = Integer.toString(y2) + "-" + Integer.toString(y1);
            this.schoolYears.add(sYear);
        }
    }
}
