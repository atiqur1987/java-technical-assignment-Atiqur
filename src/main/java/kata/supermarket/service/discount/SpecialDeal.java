package kata.supermarket.service.discount;

import kata.supermarket.model.Item;

import java.math.BigDecimal;
import java.util.List;

public interface SpecialDeal {

    BigDecimal applyDiscount(final List<Item> items);
}
