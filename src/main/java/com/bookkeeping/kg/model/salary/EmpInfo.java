package com.bookkeeping.kg.model.salary;

public class EmpInfo {
    private String surname;

    public String getSurname() {
        return surname;
    }

    private float Salary;

    public float getSalary() {
        return Salary;
    }

    public void setSalary(float salary) {
        Salary = salary;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}
