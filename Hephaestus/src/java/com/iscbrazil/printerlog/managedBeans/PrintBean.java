package com.iscbrazil.printerlog.managedBeans;

import com.iscbrazil.printerlog.business.PrintService;
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
import javax.faces.component.html.HtmlInputHidden;
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

    //private Long printerUserId;
    private HtmlInputHidden printerUserId;
    private String lastFileUploaded;
    private List<String> schoolYearsSelected;
    private List<String> monthsSelected;
    private List<String> schoolYears;
    private List<String> months;

    public PrintBean() {
        this.schoolYearsSelected = new ArrayList<String>();
        this.monthsSelected = new ArrayList<String>();
        this.printerUserId = new HtmlInputHidden();
    }

    public HtmlInputHidden getPrinterUserId() {
        return printerUserId;
    }

    public void setPrinterUserId(HtmlInputHidden printerUserId) {
        this.printerUserId = printerUserId;
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

//    public void userReports() {
//        PrintService printService = PrintService.getInstance();
//        int printerUserId = 1;
//        String sys = "2007-2008";
//        String month = "May";
//        System.out.println("PrintBean antes do printService, variaveis " + sys + month + printerUserId);
//        printService.getFilteredPrints(sys, month, printerUserId);
//        System.out.println("PrintBean depois do printService");
//    }
    public List<UserReport> getUserReports() {

        int id = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("printerUserId"));

        List<UserReport> userReports = new ArrayList<UserReport>();
        PrintService printService = PrintService.getInstance();
        UserReport userReport;

        for (int i = 0; i < this.schoolYearsSelected.size(); i++) {
            for (int j = 0; j < this.monthsSelected.size(); j++) {
                userReport = new UserReport();
                userReport.setSchoolYear(this.schoolYearsSelected.get(i));
                userReport.setMonth(this.monthsSelected.get(j));
                userReport.setPrintsByMonth(printService.getFilteredPrints(this.schoolYearsSelected.get(i), this.monthsSelected.get(j), id));
                userReports.add(userReport);

            }
        }
        return userReports;
    }
}
