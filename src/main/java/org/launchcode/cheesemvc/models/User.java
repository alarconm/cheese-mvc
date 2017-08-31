package org.launchcode.cheesemvc.models;

public class User {

    private String username;
    private String email;
    private int userId;
    private static int nextId = 1;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

    public int getUserId() {
        return userId;
    }

    private void setUserId(int userId) {
        this.userId = userId;
    }

    public static int getNextId() {
        return nextId;
    }

    private static void setNextId(int nextId) {
        User.nextId = nextId;
    }

    public User(){
        userId = nextId;
        nextId++;
    }

}