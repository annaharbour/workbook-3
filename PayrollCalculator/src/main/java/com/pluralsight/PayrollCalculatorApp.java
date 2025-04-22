package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

public class PayrollCalculatorApp {

    public static void main(String[] args) {

        try {
            FileReader fileReader = new FileReader("PayrollCalculator/employees.csv");
            BufferedReader bufReader = new BufferedReader(fileReader);
            bufReader.readLine(); // Skip header line
            System.out.println("id|name|hours-worked|pay-rate|gross pay");
            String input;
            while ((input = bufReader.readLine()) != null) {
                String[] fields = input.split("\\|");
                int employeeId = Integer.parseInt(fields[0]);
                String name = fields[1];
                double hoursWorked = Double.parseDouble(fields[2]);
                BigDecimal payRate = new BigDecimal(fields[3]);
                Employee employee = new Employee(employeeId, name, hoursWorked, payRate);
                System.out.printf("%d|%s|%.2f|$%.2f|$%.2f\n", employee.getEmployeeId(), employee.getName(),
                        employee.getHoursWorked(), employee.getPayRate(), employee.getGrossPay());
            }
            bufReader.close();
        } catch (IOException e) {
            System.out.println("Difficulty reading file!");
            System.out.println(e);

        }
    }
}