package com.pluralsight;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BedtimeStoriesApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner((System.in));
        System.out.println("Please select a story to read: \n 1. Goldilocks \n 2. Hansel and Gretel \n 3. Mary Had a " +
                "Little Lamb");
        boolean promptUser = false;
        int selection;
        String storyName = "";
        do {
            try {
                selection = scanner.nextInt();
                scanner.nextLine();
                switch (selection) {
                    case 1:
                        storyName = "goldilocks";
                        break;
                    case 2:
                        storyName = "hansel_and_gretel";
                        break;
                    case 3:
                        storyName = "mary_had_a_little_lamb";
                        break;
                    default:
                        promptUser = true;
                        break;
                }

                try {
                    FileInputStream fileInputStream =
                            new FileInputStream("BedtimeStory/stories/" + storyName + ".txt");
                    Scanner storyScanner = new Scanner(fileInputStream);

                    String story;
                    while (storyScanner.hasNextLine()) {
                        story = storyScanner.nextLine();
                        System.out.println(story);
                    }
                    storyScanner.close();
                } catch (FileNotFoundException e) {
                    System.out.println("Story file not found :( Try again!");
                    promptUser = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number 1-3 to read a story!");
                promptUser = true;
            }
        } while (promptUser);


        scanner.close();
    }
}