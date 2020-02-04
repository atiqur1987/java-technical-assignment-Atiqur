package kata.supermarket.model.discount;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.Set;

@Builder
@Getter
public class DiscountDeal {

    @Singular("barcodeSet")
    private final Set<String> barcodeSet;
    private final Integer itemQuantityForEntitlement;
}
