package com.techelevator;

import static com.techelevator.Logger.log;

public class Application {

	private final static Console console = new Console();

	private static final String FILE_PATH_OF_VENDING_MACHINE = "vendingmachine.csv";

	private static final String INVALID_VALUE_MESSAGE = "Invalid value. Please try again.";

    private static final String PRESS_ENTER_MESSAGE = "Press Enter to continue.";


	public static void main(String[] args) {
		Machine machine = new Machine(FILE_PATH_OF_VENDING_MACHINE);

		boolean running = true;
		while (running) {
			console.showMainMenu();
			Integer choice = console.promptForInt("Please entry your choice :");

            /*
             choice:
             1- displayItem
             2-  purchase
             3- exit
            **/
			switch (choice) {
				case 1:
					console.displayItem(machine.getItems());
					console.promptForString(PRESS_ENTER_MESSAGE);
					break;
				case 2:
					purchase(machine);
					break;
				case 3:
					running = false;
					break;
				case 4:
					console.report(machine.getItems());
					console.promptForString(PRESS_ENTER_MESSAGE);
					break;
				default:
					System.out.println(INVALID_VALUE_MESSAGE);
					console.promptForString(PRESS_ENTER_MESSAGE);
			}
		}
	}

	private static void purchase(Machine machine) {
		boolean running = true;

		while (running) {

			console.showPurchaseMenu(machine.getCurrentBalance());
			Integer choice = console.promptForInt("Please entry your choice :");

            /*
             choice:
             1- feed money
             2- Select Product
             3- Finish Transaction
            **/
			switch (choice) {
				case 1:
					Double amount = console.promptForDouble("Please enter amount to feed : ");
					machine.feedMoney(amount);
					console.promptForString(PRESS_ENTER_MESSAGE);
					break;
				case 2:
					selectProduct(machine);
					break;
				case 3:
					console.change(machine.getCurrentBalance());
					machine.finishTransaction();
					running = false;
					console.promptForString(PRESS_ENTER_MESSAGE);
					break;
				default:
					System.out.println(INVALID_VALUE_MESSAGE);
					console.promptForString(PRESS_ENTER_MESSAGE);

			}
		}
	}

	private static void selectProduct(Machine machine) {

		console.displayItem(machine.getItems());
		String choiceSlotLocation = console.promptForString("Please enter your choice: ").toUpperCase();

		Item item = machine.getItems().get(choiceSlotLocation);

		if (item == null) {
			System.out.println("Invalid product code!");
			console.promptForString(PRESS_ENTER_MESSAGE);
			return;
		}
		if (item.getPrice() > machine.getCurrentBalance()) {
			System.out.println("Insufficient funds!");
			console.promptForString(PRESS_ENTER_MESSAGE);
			return;
		}
		if (item.getQuantity() <= 0) {
			System.out.println("Product is sold out!");
			console.promptForString(PRESS_ENTER_MESSAGE);
			return;
		}

		item.saleItem();
		machine.spendMoney(item.getPrice());
		console.showMessageAfterDispensingItem(item.getType());
        log(item.getType() + " " + item.getSlotLocation(), item.getPrice(), machine.getCurrentBalance());

	}


}
