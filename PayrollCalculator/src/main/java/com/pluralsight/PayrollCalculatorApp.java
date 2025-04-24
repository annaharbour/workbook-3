package com.pluralsight;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PayrollCalculatorApp {

    public static void main(String[] args) {
//        READ
        System.out.println("Enter the name of the employee file to process, including the extension: ");
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();
        ArrayList<Employee> employees = readEmployees(fileName);
        promptWriteFile(scanner, employees);
    }

    private static ArrayList<Employee> readEmployees(String fileName) {
        ArrayList<Employee> employees = new ArrayList<Employee>();
        try {
            FileReader fileReader = new FileReader("PayrollCalculator/src/main/resources/" + fileName);
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
        return employees;
    }


    private static void promptWriteFile(Scanner scanner, ArrayList<Employee> employees) {
        System.out.println("Enter the name of the payroll file to create:");
        String fileName = scanner.nextLine();
        System.out.println("Choose an extension: 1. json OR 2. csv\n");
        try {
            int jsonOrCsv = scanner.nextInt();
            scanner.nextLine();

            if (jsonOrCsv == 1) {
                fileName += ".json";
                writeJsonFile(employees, fileName);
            } else if (jsonOrCsv == 2){
                fileName += ".csv";
                writeCSV(employees, fileName);
            } else {
                System.out.println("Invalid extension\n");
                promptWriteFile(scanner, employees);
            }
        } catch (InputMismatchException e){
            System.out.println("Invalid extension or filename.");
//            e.printStackTrace();
            promptWriteFile(scanner, employees);
        }

    }


    public static void writeJsonFile(ArrayList<Employee> employees, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("[\n");

            for (int i = 0; i < employees.size(); i++) {
                Employee employee = employees.get(i);
                String jsonObject = String.format(
                        "  {\n" +
                                "    \"employeeId\": %d,\n" +
                                "    \"name\": \"%s\",\n" +
                                "    \"hoursWorked\": %.2f,\n" +
                                "    \"payRate\": %.2f,\n" +
                                "    \"grossPay\": %.2f\n" +
                                "  }",
                        employee.getEmployeeId(),
                        employee.getName().replace("\"", "\\\""),
                        employee.getHoursWorked(),
                        employee.getPayRate().doubleValue(),
                        employee.getGrossPay().doubleValue()
                );

                writer.write(jsonObject);

                // Don't add comma after last object
                if (i < employees.size() - 1) {
                    writer.write(",\n");
                } else {
                    writer.write("\n");
                }
            }

            writer.write("]");
            System.out.println("JSON file written to " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing JSON file!");
            e.printStackTrace();
        }
    }

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

}