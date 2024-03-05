package cburgosdev.java.Utils;

public class StringUtils {
    public static Double getPriceFromStringRipley(String priceInput) {
        String priceString = priceInput.trim().replaceAll(",","").replaceAll(" ", "").replaceAll("S/", "");
        return Double.parseDouble(priceString);
    }
}
