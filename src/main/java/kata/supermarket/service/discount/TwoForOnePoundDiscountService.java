package kata.supermarket.service.discount;

import kata.supermarket.model.Item;
import kata.supermarket.model.discount.DiscountDeal;
import kata.supermarket.model.discount.PromotionalItem;
import kata.supermarket.utils.DealUtil;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
public class TwoForOnePoundDiscountService implements SpecialDeal {

    private final static BigDecimal BASE_PRICE = new BigDecimal("0.5");

    private final DiscountDeal discountDeal;
    private final DealUtil dealUtil;

    @Override
    public BigDecimal applyDiscount(final List<Item> items) {

        List<PromotionalItem> promotionalItems = dealUtil.extractPromotionalItems(items, discountDeal.getBarcodeSet());
        int setSizeToEntitleDiscount = discountDeal.getItemQuantityForEntitlement();

        if (promotionalItems.size() >= setSizeToEntitleDiscount) {

            int actualNumberOfItemDiscounted = dealUtil.calculateActualNumberOfItemDiscounted(
                    promotionalItems.size(), setSizeToEntitleDiscount);

            for (int index = 0; index < actualNumberOfItemDiscounted; index++) {
                PromotionalItem item = promotionalItems.get(index);

                if (item.isDiscountApplied() == false) {
                    item.setDiscountAmount((item.getItem().price().subtract(BASE_PRICE)).negate());
                }
                item.setDiscountApplied(true);
            }
        }
        return dealUtil.calculateTotal(promotionalItems);
    }
}
