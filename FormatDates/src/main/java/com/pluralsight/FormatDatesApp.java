package com.pluralsight;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class FormatDatesApp {
    public static String formatStr(String today) {
        String lowercase = today.toLowerCase();
        String capitalized = lowercase.substring(0, 1).toUpperCase() + lowercase.substring(1);
        return capitalized;
    }

    public static void main(String[] args) {
        LocalDateTime today = LocalDateTime.now();

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        System.out.println(today.format(fmt));
        fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println(today.format(fmt));
        System.out.printf("%s %s, %s%n", formatStr(today.getMonth().toString()), today.getDayOfMonth(),
                today.getYear());
        fmt = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");
        System.out.printf("%s, %s%n", formatStr(today.getDayOfWeek().toString()), today.format(fmt));
        LocalTime time = LocalTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("h:mm a");
        fmt = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        System.out.printf("%s on %s", time.format(dtf), today.format(fmt));
//        local time on dd-MMM-yyyy
//        09/05/2021
//        2021-09-05
//        September 5, 2021
//        Sunday, Sep 5, 2021 10:02 (display in GMT time)
//        5:02 on 05-Sep-2021 (display in your local time zone)
    }
}