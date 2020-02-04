package kata.supermarket.model.products;

import kata.supermarket.model.Item;
import kata.supermarket.model.items.ItemByUnit;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class Product {

    private final BigDecimal pricePerUnit;

    @Getter
    private final String barcode;

    public BigDecimal pricePerUnit() {
        return pricePerUnit;
    }

    public Item oneOf() {
        return new ItemByUnit(this);
    }
}
