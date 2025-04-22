package com.pluralsight;

import java.math.BigDecimal;

public class Employee {
    private int employeeId;
    private String name;
    private double hoursWorked;
    private BigDecimal payRate;

    public Employee(int employeeId, String name, double hoursWorked, BigDecimal payRate) {
        this.employeeId = employeeId;
        this.name = name;
        this.hoursWorked = hoursWorked;
        this.payRate = payRate;
    }

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

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public BigDecimal getPayRate() {
        return payRate;
    }

    public void setPayRate(BigDecimal payRate) {
        this.payRate = payRate;
    }

    public BigDecimal getGrossPay() {
//        TODO: gross pay calc
        BigDecimal grossPay = payRate.multiply(new BigDecimal(hoursWorked));
        return grossPay;
    }

}
