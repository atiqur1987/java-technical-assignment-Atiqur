package kata.supermarket;

import kata.supermarket.dao.DiscountRepository;
import kata.supermarket.model.Item;
import kata.supermarket.model.discount.DiscountDeal;
import kata.supermarket.model.products.Product;
import kata.supermarket.service.discount.OneItemFreeDiscountService;
import kata.supermarket.service.discount.TwoForOnePoundDiscountService;
import kata.supermarket.utils.DealUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuyThreePayTwoService {

    private DiscountRepository discountRepository;
    private DealUtil dealUtil;
    private DiscountDeal discountDeal;
    private OneItemFreeDiscountService buyThreePayTwoService;

    @BeforeEach
    void init() {
        discountRepository = new DiscountRepository();
        dealUtil = new DealUtil();

        discountDeal = DiscountDeal.builder()
                .barcodeSet(discountRepository.getAllBarcodeOfItemsBuyThreePayTwo())
                .itemQuantityForEntitlement(3)
                .build();

        buyThreePayTwoService = new OneItemFreeDiscountService(
                discountDeal, dealUtil);
    }

    @Test
    void testApplyDiscount() {
        BigDecimal amountToDeduct = buyThreePayTwoService.applyDiscount(givenBasketListItems());
        assertEquals(new BigDecimal("-0.49"), amountToDeduct);
    }

    private List<Item> givenBasketListItems() {
        return asList(aPackOfDigestives(), aPintOfMilk(), aPackOfpringles(), aPintOfMilk(), aPintOfMilk());
    }

    private Item aPintOfMilk() {
        return new Product(new BigDecimal("0.49"), "0005").oneOf();
    }

    private Item aPackOfDigestives() {
        return new Product(new BigDecimal("0.60"), "0002").oneOf();
    }

    private Item aPackOfpringles() {
        return new Product(new BigDecimal("2.30"), "0001").oneOf();
    }
}
