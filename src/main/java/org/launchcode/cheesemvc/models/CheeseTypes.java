package org.launchcode.cheesemvc.models;

public enum CheeseTypes {

    HARD ("Hard"),
    SOFT ("Soft"),
    FAKE ("Fake");

    private final String name;

    CheeseTypes(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
