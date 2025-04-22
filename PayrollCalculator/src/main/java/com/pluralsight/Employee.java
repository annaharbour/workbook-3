package com.pluralsight;

import java.math.BigDecimal;

public class Employee {
    private int employeeId;
    private String name;
    private int hoursWorked;
    private BigDecimal payRate;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public BigDecimal getPayRate() {
        return payRate;
    }

    public void setPayRate(BigDecimal payRate) {
        this.payRate = payRate;
    }

    public BigDecimal getGrossPay(){
//        TODO: gross pay calc
        BigDecimal grossPay = new BigDecimal(10.5);
        return grossPay;

    }

}
