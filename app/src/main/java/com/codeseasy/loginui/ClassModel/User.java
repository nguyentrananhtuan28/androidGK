package com.codeseasy.loginui.ClassModel;

public class User {
    private String UserName;
    private String Pass;
    private String Name;
    private String StudenID;
    public User(String string, String cursorString, String s) {}
    public User(String userName, String pass, String name, String studenID) {
        UserName = userName;
        Pass = pass;
        Name = name;
        StudenID = studenID;
    }

    public User(String userName, String pass) {
        UserName = userName;
        Pass = pass;
    }
    public User(){
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getStudenID() {
        return StudenID;
    }

    public void setStudenID(String studenID) {
        StudenID = studenID;
    }
}
