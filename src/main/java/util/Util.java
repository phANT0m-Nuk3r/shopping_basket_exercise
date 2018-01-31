package util;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public class Util {

    /** Validate prices */
    public static boolean priceIsValid(@NotNull BigDecimal price){
        return price.compareTo(BigDecimal.ZERO) > 0 ;
    }

    /** Validate quantities */
    @Contract(pure = true)
    public static boolean quantityIsValid(@NotNull Integer quantity){
        return quantity.compareTo(0) > 0;
    }

    /** Validate names */
    @Contract(pure = true)
    public static boolean nameIsValid(@NotNull String name){
        return name.length() > 0;
    }

}
