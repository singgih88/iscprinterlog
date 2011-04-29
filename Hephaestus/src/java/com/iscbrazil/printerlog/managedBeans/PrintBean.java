package com.iscbrazil.printerlog.managedBeans;

import com.iscbrazil.printerlog.business.PrintService;
import com.iscbrazil.printerlog.model.PrinterUser;
import com.iscbrazil.printerlog.model.UserReport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 * @version 2011.APR.29.01
 * @author edilson.ales
 */
@ManagedBean
@SessionScoped
public class PrintBean implements Serializable {

    private String lastFileUploaded;
    private List<String> schoolYearsSelected;
    private List<String> monthsSelected;
    private List<String> schoolYears;
    private List<String> months;

    public PrintBean() {
        this.schoolYearsSelected = new ArrayList<String>();
        this.monthsSelected = new ArrayList<String>();
    }

    public void handleFileUpload(FileUploadEvent event) {
        UploadedFile file = event.getFile();
        PrintService printService = PrintService.getInstance();
        FacesContext context = FacesContext.getCurrentInstance();

        if (!printService.validateFile(file.getFileName())) {
            context.addMessage("formMaster:formMenu:growlMsg", new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "File has already been uploaded once", "File isn't going to be uploaded again to avoid data duplicity"));
            return;
        }
        this.lastFileUploaded = file.getFileName();
        FacesMessage msg = new FacesMessage();

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(file.getInputstream()));
            String dirtyLine;

            while ((dirtyLine = in.readLine()) != null) {
                if ((msg = printService.processLine(dirtyLine, file.getFileName())) != null) {
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
        if (this.lastFileUploaded == null) {
            this.lastFileUploaded = PrintService.getInstance().getLastFileUploaded();
        }
        return lastFileUploaded;
    }

    public void setLastFileUploaded(String lastFileUploaded) {
        this.lastFileUploaded = lastFileUploaded;
    }

    public List<String> getSchoolYearsSelected() {
        if (this.schoolYearsSelected != null && this.schoolYearsSelected.contains("All")) {
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
        if (this.monthsSelected != null && this.monthsSelected.contains("All")) {
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
        
        int printerUserId = 1;
        List<UserReport> userReports = new ArrayList<UserReport>();
        PrintService printService = PrintService.getInstance();
        UserReport userReport;

        for (String sys : this.schoolYearsSelected) {
            for(String month : this.monthsSelected) {
                userReport = new UserReport();
                userReport.setSchoolYear(sys);
                userReport.setMonth(month);
                userReport.setPrintsByMonth(printService.getFilteredPrints(sys, month, printerUserId));
                userReports.add(userReport);
            }
        }

        return userReports;
    }
}
