package com.kubejourney.domain;

import org.joda.time.DateTime;

import java.io.Serializable;

public class Employee implements Serializable {

    private String id;

    private String name;

    private DateTime dob;

    private String department;

    public Employee(String id, String name, DateTime dob, String department) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.department = department;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public DateTime getDob() {
        return dob;
    }

    public String getDepartment() {
        return department;
    }
}
