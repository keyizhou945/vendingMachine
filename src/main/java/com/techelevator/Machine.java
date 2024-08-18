package com.techelevator;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import static com.techelevator.Logger.log;

@Getter
@Setter
public class Machine {

    private Map<String, Item> items = new LinkedHashMap();

    private Double currentBalance = Double.valueOf(0);

    private String filePath;

    /**
     * @return
     * @Author Keyi Zhou
     * @Description initialize items:read the items's info from input file and automatically restocked
     * @Date 2024/4/11 11:37
     **/
    public Machine(String filePath) {
        File dataFile = new File(filePath);
        try (Scanner dataInput = new Scanner(dataFile)) {
            while (dataInput.hasNextLine()) {
                String lineOfInput = dataInput.nextLine();
                String[] itemInfo = lineOfInput.split("\\|");
                Item item = new Item(itemInfo[0], itemInfo[1], Double.valueOf(itemInfo[2]), itemInfo[3]);
                items.put(item.getSlotLocation(), item);
            }
        } catch (FileNotFoundException e) {
            System.err.println("The file does not exist.");
        }
    }

    public void feedMoney(Double amount) {
        if (amount < 0) {
            System.out.println("Cannot be a negative number.");
        }
        currentBalance = currentBalance + amount;
        log("FEED MONEY", amount, currentBalance);
    }

    public void spendMoney(Double amount) {
        if (amount < 0) {
            System.out.println("Cannot be a negative number.");
        }
        currentBalance = currentBalance - amount;
    }

    public void finishTransaction() {
        Double balanceNeedsToChange = currentBalance;
        currentBalance = Double.valueOf(0);
        log("GIVE CHANGE", balanceNeedsToChange, currentBalance);
    }


}
