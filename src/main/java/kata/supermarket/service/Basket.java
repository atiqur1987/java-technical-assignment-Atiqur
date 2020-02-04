package kata.supermarket.service;

import kata.supermarket.model.Item;
import kata.supermarket.service.discount.SpecialDeal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.emptyList;

public class Basket {
    private final List<Item> items;
    private final List<SpecialDeal> specialDeals;

    public Basket() {
        this(new ArrayList<>(), emptyList());
    }

    public Basket(final List<Item> items, final List<SpecialDeal> specialDeals) {
        this.items = new ArrayList<>();
        this.specialDeals = specialDeals;
    }

    public void add(final Item item) {
        this.items.add(item);
    }

    List<Item> items() {
        return Collections.unmodifiableList(items);
    }

    List<SpecialDeal> deals() {
        return specialDeals;
    }

    public BigDecimal total() {
        return new TotalCalculator().calculate();
    }

    private class TotalCalculator {
        private final List<Item> items;

        TotalCalculator() {
            this.items = items();
        }

        private BigDecimal subtotal() {
            return items.stream().map(Item::price)
                    .reduce(BigDecimal::add)
                    .orElse(BigDecimal.ZERO)
                    .setScale(2, RoundingMode.HALF_UP);
        }

        /**
         * TODO: This could be a good place to apply the results of
         *  the discount calculations.
         *  It is not likely to be the best place to do those calculations.
         *  Think about how Basket could interact with something
         *  which provides that functionality.
         */
        private BigDecimal discounts() {

            return deals().stream()
                    .map(deal -> deal.applyDiscount(items()))
                    .reduce(BigDecimal::add)
                    .orElse(BigDecimal.ZERO)
                    .setScale(2, RoundingMode.HALF_UP);
        }

        private BigDecimal calculate() {
            return subtotal().subtract(discounts());
        }
    }
}
