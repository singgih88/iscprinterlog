package com.iscbrazil.printerlog.backbean;

import com.iscbrazil.printerlog.dao.Factory;
import com.iscbrazil.printerlog.dao.UserDAO;
import com.iscbrazil.printerlog.pojo.User;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "usersBean")
@RequestScoped
public class UsersBean {

    private List<User> users;
    private List<User> users2;
    private User selectedUser;
    private User selectedUser2;

    public UsersBean() {
        this.selectedUser = new User();
        this.selectedUser2 = new User();
        this.users = new ArrayList<User>();
        this.users2 = new ArrayList<User>();
    }

    public List<User> getUsers2() {
        return users2;
    }

    public void setUsers2(List<User> users2) {
        this.users2 = users2;
    }

    public User getSelectedUser2() {
        return selectedUser2;
    }

    public void setSelectedUser2(User selectedUser2) {
        this.selectedUser2 = selectedUser2;
    }

    public User getSelectedUser() {
        UserDAO userDAO = Factory.createUserDAO();
        String loginUserSelected = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("loginUserSelected");
        this.selectedUser = userDAO.find(loginUserSelected);
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public List<User> getUsers() {
        UserDAO userDAO = Factory.createUserDAO();
        this.users = userDAO.listPrints();
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<User> completeUser(String query) {
        UserDAO userDAO = Factory.createUserDAO();
        this.users2 = userDAO.listAll();
        
        List<User> suggestions = new ArrayList<User>();

        for(User u : this.users2) {
            if(u.getLogin().startsWith(query)) {
                suggestions.add(u);
            }
        }

        return suggestions;
    }
}
