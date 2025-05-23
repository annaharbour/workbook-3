package com.pluralsight;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class FamousQuotesApp {
    public static void main(String[] args) {
        String quotes[] = {"\"The only thing we have to fear is fear itself.\" - Franklin D.Roosevelt",
                "\"The journey of a thousand miles begins with a single step.\" - Lao Tzu",
                "\"Knowledge is power.\" - Francis Bacon",
                "\"Be the change that you wish to see in the world.\" - Mahatma Gandhi",
                "\"You only live once, but if you do it right, once is enough.\" - Elouise Glessinger",
                "\"Don't count any day as lost.\" - Ralph Waldo Emerson",
                "\"Live in the sunshine, swim the sea, drink the wild air.\" - Ralph Waldo Emerson",
                "\"The best and most beautiful things in the world cannot be seen or even touched\"  - Helen Keller",
                "\"If you tell the truth, you don't have to remember anything.\" - Mark Twain ",
                "Be yourself; everyone else is already taken.\" - Oscar Wilde"
        };

        boolean seeOptions = false;
        do {
            System.out.println("Select a number between 1-10 to view the corresponding quote, 11 for a random quote, " +
                    "or 0 to quit");
            Scanner scanner = new Scanner(System.in);
            try {
                int selection = scanner.nextInt();
                scanner.nextLine();
                if (selection == 11) {
                    System.out.println(quotes[new Random().nextInt(quotes.length)]);
                } else if (selection == 0) {
                    System.out.println("Thank you!");
                    seeOptions = true;
                } else {
                    System.out.println(quotes[selection - 1]);
                }
            } catch (ArrayIndexOutOfBoundsException | InputMismatchException e) {
                System.out.println("You selected a number that is out of range");
                seeOptions = true;
                continue;
            }
            boolean nextQuote = false;
            do {
                System.out.println("Would you like to see another quote? Type Y for yes and N for no: ");
                String choice = scanner.nextLine().toLowerCase();
                if (choice.charAt(0) == 'y') {
                    seeOptions = true;
                    nextQuote = false;
                } else if (choice.charAt(0) == 'n') {
                    System.out.println("Thank you!");
                    seeOptions = false;
                    nextQuote = false;
                } else {
                    System.out.println("Invalid response");
                    nextQuote = true;
                }
            } while (nextQuote);
        } while (seeOptions);
    }
}