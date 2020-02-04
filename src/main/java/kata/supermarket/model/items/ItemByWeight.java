package kata.supermarket.model.items;

import kata.supermarket.model.Item;
import kata.supermarket.model.products.WeighedProduct;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class ItemByWeight implements Item {

    private final WeighedProduct product;
    private final BigDecimal weightInKilos;

    public BigDecimal price() {
        return product.pricePerKilo().multiply(weightInKilos).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public String barcode() {
        return product.getBarcode();
    }
}
