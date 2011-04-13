package com.iscbrazil.printerlog.managedBeans;

import com.iscbrazil.printerlog.business.PrinterUserService;
import com.iscbrazil.printerlog.model.PrinterUser;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * @version 2011.APR.13.01
 * @author edilson.ales
 */
@ManagedBean
@RequestScoped
public class PrinterUserBean {

    private List<PrinterUser> users;
    private PrinterUser userSelected;
    private String userComplete;
    private PrinterUserService service = new PrinterUserService();

    public List<PrinterUser> getPrinterUsers() {

        if (this.users == null) {
            this.users = this.service.getAll();
        }
        return this.users;
    }

    public List<String> completePrinterUsers(String query) {
        
        List<String> results = new ArrayList<String>();
        
        for (PrinterUser u : this.users) {
            if (u.getName().toLowerCase().startsWith(query)) {
                results.add(u.getName());
            }
        }
        return results;
    }

    public void bindUserSelected() {
        for (PrinterUser u : this.users) {
            if (u.getName().equalsIgnoreCase(this.userComplete)) {
                this.userSelected = u;
            }
        }
    }

    public List<PrinterUser> getUsers() {
        return users;
    }

    public void setUsers(List<PrinterUser> users) {
        this.users = users;
    }

    public PrinterUser getUserSelected() {
        return userSelected;
    }

    public void setUserSelected(PrinterUser user) {
        this.userSelected = user;
    }

    public String getUserComplete() {
        return userComplete;
    }

    public void setUserComplete(String userComplete) {
        this.userComplete = userComplete;
    }
}
