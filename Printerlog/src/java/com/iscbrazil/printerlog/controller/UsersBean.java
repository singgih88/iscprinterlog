package com.iscbrazil.printerlog.controller;

import com.iscbrazil.printerlog.dao.Factory;
import com.iscbrazil.printerlog.dao.UserDAO;
import com.iscbrazil.printerlog.pojo.User;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "usersBean")
@RequestScoped
public class UsersBean {

    private List<User> users;
    private User selectedUser;
    private UserDAO userDAO;

    public UsersBean() {
        this.selectedUser = new User();
        this.users = new ArrayList<User>();
        this.userDAO = Factory.createUserDAO();
        loadUsers();
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
}
