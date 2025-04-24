package com.pluralsight;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Store {
    public static void main(String[] args) {
        ArrayList<Product> inventory = getInventory();
        System.out.println("Welcome to the store");
        Scanner scanner = new Scanner(System.in);
        makeChoice(scanner, inventory);
    }

    public static void showMenu() {
        System.out.println("What do you want to do?\n" +
                "\t1- List all products\n" +
                "\t2- Lookup a product by its id\n" +
                "\t3- Find all products within a price range\n" +
                "\t4- Add a new product\n" +
                "\t5- Quit the application\n" +
                "Enter command:");
    }

    public static void makeChoice(Scanner scanner, ArrayList<Product> inventory) {
        int selection;
        do {
            showMenu();
            try {
                selection = scanner.nextInt();
                scanner.nextLine();

                switch (selection) {
                    case 1:
                        listInventory(inventory);
                        break;
                    case 2:
                        lookUpById(scanner, inventory);
                        break;
                    case 3:
                        listInventoryByPriceRange(scanner, inventory);
                        break;
                    case 4:
                        addProduct(scanner, inventory);
                        break;
                    case 5:
                        System.out.println("Thank you for shopping at ShopApp!");
                        break;
                    default:
                        System.out.println("Invalid selection. Please choose from 1 to 5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine(); // clear invalid input
                selection = -1; // keep loop going
            }
        } while (selection != 5);
    }


    public static ArrayList<Product> getInventory() {
        ArrayList<Product> inventory = new ArrayList<Product>();
        inventory.add(new Product("Shoe", 34.34f));
        inventory.add(new Product("Shirt", 12.12f));
        inventory.add(new Product("Hat", 15.99f));
        inventory.add(new Product("Jacket", 89.99f));
        inventory.add(new Product("Pants", 45.50f));
        inventory.add(new Product("Socks", 5.99f));
        return inventory;
    }

    private static void listInventory(ArrayList<Product> inventory) {
        System.out.println("======================");
        System.out.println("\nWe carry the following inventory: \n");
        for (int i = 0; i < inventory.size(); i++) {
            Product product = inventory.get(i);
            System.out.printf("id: %s\n%s\nPrice: $%.2f\n-----------------\n",
                    product.getId(), product.getName(), product.getPrice());
        }
        System.out.println("======================");
    }

    private static void listInventoryByPriceRange(Scanner scanner, ArrayList<Product> inventory) {
        double lowPrice = 0;
        double highPrice = 1000;
        try {
            System.out.println("Enter the low end of your price range:");
            lowPrice = scanner.nextDouble();
            scanner.nextLine();
            System.out.println("Enter the high end of your price range:");
            highPrice = scanner.nextDouble();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            e.printStackTrace();
            System.out.println("Make sure you're entering a valid price!");
            listInventoryByPriceRange(scanner, inventory);
        }
        System.out.println("Products in your price range are...\n");
        for (Product product : inventory) {
            if (product.getPrice() >= lowPrice && product.getPrice() <= highPrice) {

                System.out.printf("id: %s\n%s\nPrice: $%.2f\n-----------------\n",
                        product.getId(), product.getName(), product.getPrice());
            }
        }
        System.out.println("======================");
    }

    private static void lookUpById(Scanner scanner, ArrayList<Product> inventory) {
        System.out.println("Enter the product id: ");
        try {
            String id = scanner.nextLine();
            boolean found = false;
            for (Product product : inventory) {

                if (product.getId().equals(id)) {
                    System.out.println("Product found:\n");
                    System.out.printf("id: %s\n%s\nPrice: $%.2f\n",
                            product.getId(), product.getName(), product.getPrice());
                    System.out.println("======================");

                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("No product found with the given ID.");
            }
        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
        }
    }

    private static void addProduct(Scanner scanner, ArrayList<Product> inventory) {
        System.out.println("What is the product's name?");
        String name = scanner.nextLine();
        System.out.println("What is the product's price?");
        float price = 0;
        try {
            price = scanner.nextFloat();
        } catch (InputMismatchException e) {
            System.out.println("Failed to create product. Incorrect value.");
            return;
        }
        try {
            inventory.add(new Product(name, price));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
