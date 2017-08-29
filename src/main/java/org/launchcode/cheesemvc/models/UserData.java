package org.launchcode.cheesemvc.models;

import java.util.ArrayList;


public class UserData {

    static ArrayList<User> userArrayList = new ArrayList<>();

    public static void add(User newUser) {
        userArrayList.add(newUser);
    }

    public static ArrayList<User> getAll() {
        return userArrayList;
    }

//    public User getById(int id) {
//        return 5;
//    }
}
