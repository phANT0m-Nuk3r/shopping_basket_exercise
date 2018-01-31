package model;

import org.jetbrains.annotations.NotNull;
import util.Util;

import java.math.BigDecimal;

/**
 * ToDo: Write javaDocs
 */
public class OrderItem {
    private String productName;
    private Integer quantity;
    private BigDecimal price;

    /**
     * Primary Constructor
     */
    public OrderItem(@NotNull String productName, @NotNull Integer quantity, @NotNull BigDecimal price) throws RuntimeException{
        if(Util.nameIsValid(productName) && Util.quantityIsValid(quantity) && Util.priceIsValid(price)) {
            setPrice(price);
            setProductName(productName);
            setQuantity(quantity);
        } else {
            throw new RuntimeException("Bad argument(s) passed to OrderItem constructor!");
        }
    }

    /**
     * Secondary Constructor
     * @see #OrderItem(String, Integer, BigDecimal)
     */
    public OrderItem(@NotNull String productName, @NotNull BigDecimal price) throws RuntimeException {
        this(productName, 1, price);
    }

    /**
     * Tertiary Constructor
     * @see #OrderItem(String, BigDecimal)
     */
    public OrderItem(@NotNull BigDecimal price) throws RuntimeException {
        this("Unknown", price);
    }


    @NotNull
    protected BigDecimal getIndividualPrice() {
        return this.price;
    }

    private void setPrice(@NotNull BigDecimal price) {
        this.price = price;
    }

    protected String getProductName() {
        return productName;
    }

    private void setProductName(@NotNull String productName) {
        this.productName = productName;
    }

    @NotNull
    protected Integer getQuantity() {
        return this.quantity;
    }

    private void setQuantity(@NotNull Integer quantity) {
        this.quantity = quantity;
    }

    @NotNull
    protected BigDecimal getTotal() {
        return getIndividualPrice().multiply(BigDecimal.valueOf(getQuantity()));
    }


    /**
     * Increment Item Quantity by 1
     */
    protected void addItem() {
        setQuantity(getQuantity() + 1);
    }

    /**
     * Increase Quantity and update price
     */
    protected void addItems(@NotNull BigDecimal latestPrice, @NotNull Integer amountToAdd){
        setPrice(latestPrice);
        setQuantity(getQuantity() + amountToAdd);
    }

    /**
     * Increase Quantity without updating price
     */
    protected void addItems(@NotNull Integer amountToAdd){
        setQuantity(getQuantity() + amountToAdd);
    }

    /**
     * ToDo: Write javaDocs
     */
    protected void removeItem() {
        if(getQuantity() - 1 < 1){
            //removeOrderItem(this);
        }else{
            setQuantity(getQuantity() - 1);
        }
    }

    /**
     * ToDo: Write javaDocs
     */
    protected void removeItem(@NotNull Integer quantity) throws RuntimeException{
        if (Util.quantityIsValid(quantity)){
            if (getQuantity() - quantity > 0){
                setQuantity(getQuantity() - quantity);
            }else{
                System.out.println("Attempt to remove " + quantity + " " + getProductName() + " items failed\n" +
                                   "Reason: current quantity is " + getQuantity() + '\n');
            }
        } else {
            throw new RuntimeException("Bad argument passed to method removeItem in class OrderItem!");
        }
    }
}
