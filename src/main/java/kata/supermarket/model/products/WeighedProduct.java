package kata.supermarket.model.products;

import kata.supermarket.model.Item;
import kata.supermarket.model.items.ItemByWeight;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class WeighedProduct {

    private final BigDecimal pricePerKilo;

    @Getter
    private final String barcode;

    public BigDecimal pricePerKilo() {
        return pricePerKilo;
    }

    public Item weighing(final BigDecimal kilos) {
        return new ItemByWeight(this, kilos);
    }
}
