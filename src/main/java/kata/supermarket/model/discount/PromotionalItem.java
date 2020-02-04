package kata.supermarket.model.discount;

import kata.supermarket.model.Item;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class PromotionalItem {

    private Item item;
    private BigDecimal discountAmount;
    private boolean discountApplied;
}
