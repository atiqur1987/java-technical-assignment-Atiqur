package kata.supermarket.service.discount;

import kata.supermarket.model.Item;
import kata.supermarket.model.discount.DiscountDeal;
import kata.supermarket.model.discount.PromotionalItem;
import kata.supermarket.utils.DealUtil;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
public class OneItemFreeDiscountService implements SpecialDeal {

    private final DiscountDeal discountDeal;

    private final DealUtil dealUtil;

    @Override
    public BigDecimal applyDiscount(final List<Item> items) {

        List<PromotionalItem> promotionalItems = dealUtil.extractPromotionalItems(items, discountDeal.getBarcodeSet());
        int setSizeToEntitleDiscount = discountDeal.getItemQuantityForEntitlement();

        if (promotionalItems.size() >= setSizeToEntitleDiscount) {

            int subSetItemDiscountCount = 1;
            int actualNumberOfItemDiscounted = dealUtil.calculateActualNumberOfItemDiscounted(
                    promotionalItems.size(), setSizeToEntitleDiscount);

            for (int index = 0; index < actualNumberOfItemDiscounted; index++) {
                PromotionalItem item = promotionalItems.get(index);

                if (item.isDiscountApplied() == false && subSetItemDiscountCount == setSizeToEntitleDiscount) {
                    item.setDiscountAmount(item.getItem().price().negate());
                    subSetItemDiscountCount = 1;
                } else {
                    subSetItemDiscountCount++;
                }
                item.setDiscountApplied(true);
            }
        }
        return dealUtil.calculateTotal(promotionalItems);
    }
}
