package com.gtv.hanhee.dagger2.Model;

public class Responsitory {

    String name;

    String fullName;

    String description;

    public Responsitory(String name, String fullName, String description) {
        this.name = name;
        this.fullName = fullName;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDescription() {
        return description;
    }
}
