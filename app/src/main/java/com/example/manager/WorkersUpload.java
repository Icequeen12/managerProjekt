package com.example.manager;

public class WorkersUpload {

    private String name;
    private String lastName;

    public WorkersUpload() {
    }

    public WorkersUpload(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
