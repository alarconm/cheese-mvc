package org.launchcode.cheesemvc.models;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {

    @NotNull
    @Size(min = 5, max = 15, message = "Username must be between 5 and 15 characters")
    private String name;

    @NotNull
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    @Email(message = "Please enter a valid email address, or none at all")
    private String email;
    private int userId;
    private static int nextId = 1;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
