package com.techelevator;

import java.util.Map;
import java.util.Scanner;

public class Console {

    private final Scanner scanner = new Scanner(System.in);


    public String promptForString(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    public int promptForInt(String prompt) {
        System.out.println(prompt);
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    public Double promptForDouble(String prompt) {
        System.out.println(prompt);
        while (true) {
            try {
                return Double.valueOf(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a Double number.");
            }
        }
    }

    public  void showMainMenu() {
        System.out.println("\n------------------------ Welcome ------------------------");
        System.out.println("(1) Display Vending Machine Items");
        System.out.println("(2) Purchase");
        System.out.println("(3) Exit");
        System.out.println("--------------------------------------------------------");
    }

    public static void showPurchaseMenu(Double currentBalance) {
        System.out.println("\n---------------------- Purchasing ----------------------");
        System.out.println(String.format("Current Money Provided: $%.2f\n", currentBalance));
        System.out.println("(1) Feed Money");
        System.out.println("(2) Select Product");
        System.out.println("(3) Finish Transaction");
        System.out.println("--------------------------------------------------------");
    }

    public static void showMessageAfterDispensingItem(String itemType) {
        switch (itemType) {
            case "Chip":
                System.out.println("Crunch Crunch, Yum!");
                break;
            case "Candy":
                System.out.println("Munch Munch, Yum!");
                break;
            case "Drink":
                System.out.println("Glug Glug, Yum!");
                break;
            case "Gum":
                System.out.println("Chew Chew, Yum!");
                break;
            default:
                System.out.println("Enjoy!");
        }
    }

    public void displayItem(Map<String, Item> items) {
        if (items == null || items.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Item> entry : items.entrySet()) {
            Item item = entry.getValue();

            String str = String.format("%-3s:   %-20s price: $%.2f      quantity:%d", item.getSlotLocation(), item.getProductName(), item.getPrice(), item.getQuantity());
            if (item.getQuantity().equals(0)) {
                str = str.concat(" ( SOLD OUT ) ");
            }
            System.out.println(str);
        }
    }

    public void change(Double balanceNeedsToChange) {
        Integer remainingBalance = (int) (balanceNeedsToChange * 100);
        Integer quarters = remainingBalance / 25;
        remainingBalance %= 25;
        Integer dimes = remainingBalance / 10;
        remainingBalance %= 10;
        Integer nickels = remainingBalance / 5;
        System.out.println(String.format("Change returned: %d quarters, %d dimes, %d nickels", quarters, dimes, nickels));

    }

    public void report(Map<String, Item> itemMap) {
        if (itemMap == null || itemMap.isEmpty()) {
            System.out.println("**TOTAL SALES** $0");
            return;
        }

        Integer maxQuantity = Item.DEFAULT_QUANTITY;
        Double balance = Double.valueOf(0);

        for (Map.Entry<String, Item> entry : itemMap.entrySet()) {
            Item item = entry.getValue();
            Integer quantityOfSold = maxQuantity - item.getQuantity();
            String str = String.format("%-20s | %d", item.getProductName(), quantityOfSold);
            System.out.println(str);
            balance += quantityOfSold * item.getPrice();
        }

        System.out.println(String.format("\n**TOTAL SALES**  $%.2f", balance));

    }
}
