package cburgosdev.java.Utils;

import java.util.Objects;

public class CommonUtils {
    public static Double getMinPrice(Double priceWithCard, Double price) {
        return priceWithCard == null ? price : Math.min(price, priceWithCard);
    }
    public static Double getDiscountRate(Double lastPrice, Double discountRateFromDB, Double lastPriceChanged) {
        if(!Objects.equals(lastPrice, lastPriceChanged)) {
            return ((lastPriceChanged - lastPrice) / lastPriceChanged) * 100;
        } else {
            return discountRateFromDB;
        }
    }
}
