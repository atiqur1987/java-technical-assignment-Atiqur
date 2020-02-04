package kata.supermarket.model.items;

import kata.supermarket.model.Item;
import kata.supermarket.model.products.Product;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class ItemByUnit implements Item {

    private final Product product;

    public BigDecimal price() {
        return product.pricePerUnit();
    }

    public String barcode() {
        return product.getBarcode();
    }
}
