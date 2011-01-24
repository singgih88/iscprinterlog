package com.iscbrazil.printerlog.controller;

import com.iscbrazil.printerlog.dao.Factory;
import com.iscbrazil.printerlog.dao.PrinterDAO;
import com.iscbrazil.printerlog.dao.UserDAO;
import com.iscbrazil.printerlog.pojo.Month;
import com.iscbrazil.printerlog.pojo.Printer;
import com.iscbrazil.printerlog.pojo.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "usersBean")
@SessionScoped
public class UsersBean implements Serializable {

    private UserDAO userDAO;
    private PrinterDAO printerDAO;
    private User selectedUser;
    private Month monthSelected;
    private Printer printerSelected;
    private List<User> users;
    private List<Printer> printers;
    private List<Month> months;
    private List<String> schoolYears;
    private String schoolYearSelected;
    private String sheetsTimeInterval;
    private String sheetsPrinter;
    private String monthValueSelected;
    private String printerIdSelected;

    public UsersBean() {
        this.userDAO = Factory.createUserDAO();
        this.printerDAO = Factory.createPrinterDAO();
        this.users = new ArrayList<User>();
        loadUsers();
        this.schoolYears = new ArrayList<String>();
        loadSchoolYears();
        this.months = new ArrayList<Month>();
        loadMonths();
        this.selectedUser = new User();
        this.printers = new ArrayList<Printer>();
        loadPrinters();
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<String> getSchoolYears() {
        return schoolYears;
    }

    public void setSchoolYears(List<String> schoolYears) {
        this.schoolYears = schoolYears;
    }

    public Month getMonthSelected() {
        return monthSelected;
    }

    public void setMonthSelected(Month monthSelected) {
        this.monthSelected = monthSelected;
    }

    public String getSchoolYearSelected() {
        return schoolYearSelected;
    }

    public void setSchoolYearSelected(String schoolYearSelected) {
        this.schoolYearSelected = schoolYearSelected;
    }

    public List<Month> getMonths() {
        return months;
    }

    public void setMonths(List<Month> months) {
        this.months = months;
    }

    public String getSheetsTimeInterval() {
        return sheetsTimeInterval;
    }

    public void setSheetsTimeInterval(String sheetsTimeInterval) {
        this.sheetsTimeInterval = sheetsTimeInterval;
    }

    public String getMonthValueSelected() {
        return monthValueSelected;
    }

    public void setMonthValueSelected(String monthValueSelected) {
        this.monthValueSelected = monthValueSelected;
    }

    public Printer getPrinterSelected() {
        return printerSelected;
    }

    public void setPrinterSelected(Printer printerSelected) {
        this.printerSelected = printerSelected;
    }

    public List<Printer> getPrinters() {
        return printers;
    }

    public void setPrinters(List<Printer> printers) {
        this.printers = printers;
    }

    public String getPrinterIdSelected() {
        return printerIdSelected;
    }

    public void setPrinterIdSelected(String printerIdSelected) {
        this.printerIdSelected = printerIdSelected;
    }

    public String getSheetsPrinter() {
        return sheetsPrinter;
    }

    public void setSheetsPrinter(String sheetsPrinter) {
        this.sheetsPrinter = sheetsPrinter;
    }

    public List<User> completeUser(String query) {
        this.users = this.userDAO.listAll();

        List<User> suggestions = new ArrayList<User>();

        for (User u : this.users) {
            if (u.getName().startsWith(query)) {
                suggestions.add(u);
            }
        }
        return suggestions;
    }

    private void loadUsers() {
        this.users = this.userDAO.listAll();
    }

    private void loadSchoolYears() {
        this.schoolYears = this.userDAO.listSchoolYears();

    }

    private void loadMonths() {

        this.months = new ArrayList<Month>();

        this.months.add(new Month("--//--", 0));
        this.months.add(new Month("January", 1));
        this.months.add(new Month("February", 2));
        this.months.add(new Month("March", 3));
        this.months.add(new Month("April", 4));
        this.months.add(new Month("May", 5));
        this.months.add(new Month("June", 6));
        this.months.add(new Month("July", 7));
        this.months.add(new Month("August", 8));
        this.months.add(new Month("September", 9));
        this.months.add(new Month("October", 10));
        this.months.add(new Month("November", 11));
        this.months.add(new Month("December", 12));
    }

    private void loadPrinters() {
        this.printers = this.printerDAO.listAll();
    }

    public void dateDetailedData(ActionEvent event) {
        this.monthSelected = new Month();
        for (Month m : this.months) {
            if (this.monthValueSelected.equalsIgnoreCase(String.valueOf(m.getMonthValue())) && !(this.monthValueSelected.equals("0"))) {
                this.monthSelected = m;
            }
        }
        this.sheetsTimeInterval = this.userDAO.getDetailedDataTime(schoolYearSelected, Integer.parseInt(monthValueSelected), selectedUser.getLogin());
    }

    public void  printerDetailedData(ActionEvent event) {
        this.printerSelected = new Printer();
        for (Printer p : this.printers) {
            if(this.printerIdSelected.equalsIgnoreCase(String.valueOf(p.getPrinterID()))) {
                this.printerSelected = p;
            }
        }
        this.sheetsPrinter = this.userDAO.getDetailedDataPrinter(printerSelected.getPrinterName(), selectedUser.getLogin());
    }

}
