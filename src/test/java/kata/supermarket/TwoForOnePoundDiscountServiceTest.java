package kata.supermarket;

import kata.supermarket.dao.DiscountRepository;
import kata.supermarket.model.Item;
import kata.supermarket.model.discount.DiscountDeal;
import kata.supermarket.model.products.Product;
import kata.supermarket.service.discount.TwoForOnePoundDiscountService;
import kata.supermarket.utils.DealUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TwoForOnePoundDiscountServiceTest {

    private DiscountRepository discountRepository;
    private DealUtil dealUtil;
    private DiscountDeal discountDeal;
    private TwoForOnePoundDiscountService twoForOnePoundDiscountService;

    @BeforeEach
    void init() {
        discountRepository = new DiscountRepository();
        dealUtil = new DealUtil();

        discountDeal = DiscountDeal.builder()
                .barcodeSet(discountRepository.getAllBarcodeOfItemsBuyTwoItemsForOnePound())
                .itemQuantityForEntitlement(2)
                .build();

        twoForOnePoundDiscountService = new TwoForOnePoundDiscountService(
                discountDeal, dealUtil);
    }

    @Test
    void testApplyDiscount() {
        BigDecimal amountToDeduct = twoForOnePoundDiscountService.applyDiscount(givenBasketListItems());
        assertEquals(new BigDecimal("-0.20"), amountToDeduct);
    }

    private List<Item> givenBasketListItems() {
        return asList(aPackOfDigestives(), aPintOfMilk(), aPackOfDigestives(), aPackOfpringles(), aPackOfDigestives());
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
