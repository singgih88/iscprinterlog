package com.iscbrazil.printerlog.pojo;

public class User {

    private String login;
    private String name;
    private String category;
    private int totalPrint;

    public User(String login, String name, String category, int totalPrint) {
        this.login = login;
        this.name = name;
        this.category = category;
        this.totalPrint = totalPrint;
    }

    public User() {
        
    }

    public User(String login) {
        this.login = login;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalPrint() {
        return totalPrint;
    }

    public void setTotalPrint(int totalPrint) {
        this.totalPrint = totalPrint;
    }

    @Override
    public String toString() {
        return "User{" + "login=" + login + ";name=" + name + ";category=" + category + ";totalPrint=" + totalPrint + '}';
    }

    
}
