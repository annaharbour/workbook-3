package com.pluralsight;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class PayrollCalculatorApp {

    public static void writeCSV(ArrayList<Employee> employees, String fileName
    ) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
//            CSV HEADERS
            String[] headers = {"id", "name", "hours-worked", "pay-rate", "gross pay"};
            bufferedWriter.write(String.join(",", headers));
            bufferedWriter.newLine();
//            ADD EMPLOYEES TO FIELDS
            for (Employee employee : employees) {
                String line = String.format(
                        "%d,%s,%.2f,%.2f,%.2f",
                        employee.getEmployeeId(),
                        employee.getName(),
                        employee.getHoursWorked(),
                        employee.getPayRate().doubleValue(),
                        employee.getGrossPay().doubleValue()
                );
                bufferedWriter.write(line);
                bufferedWriter.newLine();

            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Error processing employee");
            e.getStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("Enter the name of the employee file to process: ");
        Scanner scanner = new Scanner(System.in);
        String read = scanner.nextLine();
        System.out.println("Enter the name of the payroll file to create:");
        String fileName = scanner.nextLine();
//        TODO: error handling for improper input
        System.out.println("Choose an extension: 1. json OR 2. csv");
        int jsonOrCsv = scanner.nextInt();
        scanner.nextLine();
        fileName += jsonOrCsv == 1 ? ".json" : ".csv";
        ArrayList<Employee> employees = new ArrayList<Employee>();
        try {
            FileReader fileReader = new FileReader("PayrollCalculator/src/main/resources/" + read);
            BufferedReader bufReader = new BufferedReader(fileReader);
            bufReader.readLine(); // Skip header line
//            System.out.println("id|name|hours-worked|pay-rate|gross pay");
            String input;
            while ((input = bufReader.readLine()) != null) {
                String[] fields = input.split("\\|");
                int employeeId = Integer.parseInt(fields[0]);
                String name = fields[1];
                double hoursWorked = Double.parseDouble(fields[2]);
                BigDecimal payRate = new BigDecimal(fields[3]);
                Employee employee = new Employee(employeeId, name, hoursWorked, payRate);
                employees.add(employee);
//                System.out.printf("%d|%s|%.2f|$%.2f|$%.2f\n", employee.getEmployeeId(), employee.getName(),
//                        employee.getHoursWorked(), employee.getPayRate(), employee.getGrossPay());
            }
            bufReader.close();
        } catch (IOException e) {
            System.out.println("Difficulty reading file!");
            System.out.println(e);
        }

        writeCSV(employees, fileName);
    }
}