package kata.supermarket.dao;

import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;

public class DiscountRepository {

    public Set<String> getAllBarcodeOfItemsBuyOneGetOneFree() {
        return new HashSet<>(asList("0000", "0001"));
    }

    public Set<String> getAllBarcodeOfItemsBuyTwoItemsForOnePound() {
        return new HashSet<>(asList("0002", "0003"));
    }

    public Set<String> getAllBarcodeOfItemsBuyThreePayTwo() {
        return new HashSet<>(asList("0004", "0005"));
    }
}
