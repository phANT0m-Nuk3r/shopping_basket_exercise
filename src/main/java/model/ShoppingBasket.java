package model;

import org.jetbrains.annotations.NotNull;
import util.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * ToDo: Write javaDocs
 */
public class ShoppingBasket {

    private List<OrderItem> orderItemList;

    /**
     * Primary constructor
     * @param orderItemList non-null List<OrderItem>
     */
    private ShoppingBasket(@NotNull List<OrderItem> orderItemList) {
        setOrderItemList(orderItemList);
    }

    /**
     * Secondary Constructor
     * @see #ShoppingBasket(List)
     */
    public ShoppingBasket() {
        this(new ArrayList<>());
    }


    public List<OrderItem> getOrderItemList() {
        return this.orderItemList;
    }

    private void setOrderItemList(@NotNull List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }


    public boolean addProduct(@NotNull OrderItem orderItem) throws RuntimeException {
        if (productExistsInBasket(orderItem.getProductName(), orderItem.getIndividualPrice())) {
            int index = getIndex(orderItem.getProductName(), orderItem.getIndividualPrice());

            getOrderItemList().get(index).addItems(orderItem.getQuantity());
        }

    }

    public boolean removeProduct(@NotNull String name, @NotNull Integer quantity) throws RuntimeException {
        if(Util.nameIsValid(name) && Util.quantityIsValid(quantity)) {
            boolean value = false;

            Set<Integer> indices = new LinkedHashSet<>();

            for (int i = 0; i < orderItemList.size(); i++) {
                OrderItem orderItem = getOrderItemList().get(i);

                if (orderItem.getProductName().equals(name)) {
                    if (orderItem.getQuantity().compareTo(quantity) > 0) {
                        orderItem.removeItem(quantity);
                        value = true;
                        break;
                    } else {
                        indices.add(i);
                    }
                }
            }

            if (!value) {
                int numRemoved = 0;

                for (Integer i : indices) {
                    if (numRemoved == quantity) {
                        break;
                    }

                    OrderItem orderItem = getOrderItemList().get(i);
                    if (quantity >= numRemoved - orderItem.getQuantity()) {
                        numRemoved += orderItem.getQuantity();
                        getOrderItemList().remove(i.intValue());
                    } else {
                        orderItem.removeItem(quantity - numRemoved);
                    }
                }
            }

            return value;
        } else {
            throw new RuntimeException("bad argument(s) passed to method removeProduct(String, Integer) in class removeProduct!");
        }
    }

    public boolean removeProduct(@NotNull String name){
        boolean value = false;

        for (OrderItem orderItem : getOrderItemList()){
            if()
        }

        return value;
    }

    /**
     * Return whether a product matching search criteria exists within the shopping basket
     * Will throw a RuntimeException if the search criteria is ""
     * @param productName the search criteria
     */
    private boolean productExistsInBasket(@NotNull String productName) throws RuntimeException {
        if (Util.nameIsValid(productName)) {
            boolean value = false;

            for (OrderItem orderItem : getOrderItemList()) {
                if (orderItem.getProductName().equals(productName)) {
                    value = true;
                    break;
                }
            }

            return value;
        }else{
            throw new RuntimeException("Bad argument(s) passed to method productExistsInBasket(String, BigDecimal) in class ShoppingBasket");
        }
    }

    /**
     * Return the index of the first OrderItem matching the search criteria
     * Will throw a RuntimeException if the search criteria is "" or if the search criteria was not fulfilled
     * @param productName the search criteria
     * @see #productExistsInBasket(String)
     */
    @NotNull
    private Integer getIndex(@NotNull String productName) throws RuntimeException {
        if (Util.nameIsValid(productName)) {
            Integer index = null;

            for (int i = 0; i < getOrderItemList().size(); i++){
                if (getOrderItemList().get(i).getProductName().equals(productName)){
                    index = i;
                    break;
                }
            }

            if (null == index){
                throw new RuntimeException("Could not find index of product \'" + productName + "\'");
            }

            return index;
        } else {
            throw new RuntimeException("Bad argument(s) passed to method getIndex(String, BigDecimal) in class \'" + this.getClass() + "\'");
        }
    }
}
