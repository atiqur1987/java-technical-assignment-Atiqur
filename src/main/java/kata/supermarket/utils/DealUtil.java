package kata.supermarket.utils;

import kata.supermarket.model.Item;
import kata.supermarket.model.discount.PromotionalItem;
import kata.supermarket.model.items.ItemByUnit;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

public class DealUtil {

    public List<PromotionalItem> extractPromotionalItems(final List<Item> items, final Set<String> discountBarcodeSet) {

        return items.stream()
                .map(item -> new PromotionalItem(item, new BigDecimal(0), false))
                .filter(itemToD -> itemToD.getItem() instanceof ItemByUnit)
                .filter(itemToD -> discountBarcodeSet.contains(itemToD.getItem().barcode()))
                .collect(toList());
    }

    public BigDecimal calculateTotal(final List<PromotionalItem> promotionalItems) {

        return promotionalItems.stream()
                .map(PromotionalItem::getDiscountAmount)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO)
                .setScale(2, RoundingMode.HALF_UP);
    }

    public int calculateActualNumberOfItemDiscounted(
            final int numberOfItemPossibleToDiscount,
            final int setSizeToEntitleDiscount) {
        return (numberOfItemPossibleToDiscount / setSizeToEntitleDiscount) * setSizeToEntitleDiscount;
    }
}
