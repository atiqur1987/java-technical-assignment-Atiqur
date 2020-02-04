package kata.supermarket.service.discount;

import kata.supermarket.dao.DiscountRepository;
import kata.supermarket.model.discount.DiscountDeal;
import lombok.RequiredArgsConstructor;

import java.util.Set;

import static java.util.Collections.unmodifiableSet;

@RequiredArgsConstructor
public class DiscountService {

    private final DiscountRepository discountRepository;

    public DiscountDeal dealBuyOneGetOneFree() {
        return generateDiscountDeal(2, discountRepository.getAllBarcodeOfItemsBuyOneGetOneFree());
    }

    public DiscountDeal dealBuyTwoItemsForOnePound() {
        return generateDiscountDeal(2, discountRepository.getAllBarcodeOfItemsBuyTwoItemsForOnePound());
    }

    public DiscountDeal dealBuyThreePayTwo() {
        return generateDiscountDeal(3, discountRepository.getAllBarcodeOfItemsBuyThreePayTwo());
    }

    private DiscountDeal generateDiscountDeal(final Integer quantity, Set<String> barcodeList) {
        return DiscountDeal.builder()
                .itemQuantityForEntitlement(quantity)
                .barcodeSet(unmodifiableSet(unmodifiableSet(barcodeList)))
                .build();
    }
}
