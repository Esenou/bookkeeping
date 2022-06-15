package com.bookkeeping.kg.model.salary;

import java.sql.Date;

public class SalaryInfo {

    private float salary;
    private Date date;

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
