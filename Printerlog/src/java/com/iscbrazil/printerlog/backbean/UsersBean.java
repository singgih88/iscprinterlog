package com.iscbrazil.printerlog.backbean;

import com.iscbrazil.printerlog.dao.Factory;
import com.iscbrazil.printerlog.dao.UserDAO;
import com.iscbrazil.printerlog.pojo.User;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "usersBean")
@SessionScoped
public class UsersBean {

    private List<User> users;
    private User selectedUser;

    public String getEscolhido() {
        return escolhido;
    }

    public void setEscolhido(String escolhido) {
        this.escolhido = escolhido;
    }
    private String escolhido;

    public UsersBean() {
        this.selectedUser = new User();
        this.users = new ArrayList<User>();
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

    public String selectUser() {
        



        //
        //String teste = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("loginUserSelected");

        //this.selectedUser.setName(teste);

        //this.selectedUser = userDAO.find(this.selectedUser.getName());

        return null;
    }
    
}
