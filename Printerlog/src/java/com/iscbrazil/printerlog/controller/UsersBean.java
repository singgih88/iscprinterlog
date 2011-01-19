package com.iscbrazil.printerlog.controller;

import com.iscbrazil.printerlog.dao.Factory;
import com.iscbrazil.printerlog.dao.UserDAO;
import com.iscbrazil.printerlog.pojo.Month;
import com.iscbrazil.printerlog.pojo.User;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "usersBean")
@RequestScoped
public class UsersBean {

    private List<User> users;
    private User selectedUser;
    private UserDAO userDAO;
    private List<String> schoolYears;
    private String schoolYearSelected;
    private int monthSelected;
    private List<Month> months;
    private String sheetsTimeInterval;

    public UsersBean() {
        this.selectedUser = new User();
        this.users = new ArrayList<User>();
        this.userDAO = Factory.createUserDAO();
        this.schoolYears = new ArrayList<String>();
        this.months = new ArrayList<Month>();
        loadUsers();
        loadSchoolYears();
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

    public int getMonthSelected() {
        return monthSelected;
    }

    public void setMonthSelected(int monthSelected) {
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

    public List<User> completeUser(String query) {
        this.users = this.userDAO.listAll();

        List<User> suggestions = new ArrayList<User>();

        for(User u : this.users) {
            if(u.getName().startsWith(query)) {
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

    public List<Month> getLoadMonths() {
        
        this.months = new ArrayList<Month>();
        
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

        return this.months;
    }

    public void dateDetailedData(ActionEvent event) {
        this.sheetsTimeInterval = this.userDAO.getDetailedDataTime(schoolYearSelected, monthSelected);
    }
}
