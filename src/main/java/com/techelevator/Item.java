package com.techelevator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName Item
 * @Description the class of item
 * @Author Keyi Zhou
 * @Date 2024/4/6 11:54
 **/
@Getter
@Setter
public class Item {

    public static final Integer DEFAULT_QUANTITY = 5;

    /**
     * The slot location in the vending machine containing the product.
     * A1,A2...A4.
     * B1...
     * C1...
     * D1...
     */
    private String slotLocation;

    /**
     * The display name of the vending machine product
     */
    private String productName;

    /**
     * The purchase price for the product.
     */
    private Double price;

    /**
     * The product type for this row.
     */
    private String type;

    /**
     * the quantity of the product
     */
    private Integer quantity = DEFAULT_QUANTITY;


    /**
     * @return the remaining quantity of item.(If there is insufficient stock, return -1)
     * @Author Keyi Zhou
     * @Description If there is insufficient stock, return -1 ;
     * else update and return the remaining quantity of the item
     * @Date 2024/4/6 13:04
     **/
    public Integer saleItem() {
        if (this.quantity < 1) {
            return -1;
        }
        this.quantity = this.quantity - 1;
        return this.quantity;
    }

    public Item(String slotLocation, String productName, Double price, String type) {
        this.productName = productName;
        this.price = price;
        this.type = type;
        this.slotLocation = slotLocation;
    }
}
