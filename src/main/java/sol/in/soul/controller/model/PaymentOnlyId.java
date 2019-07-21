package sol.in.soul.controller.model;

import lombok.Data;
import sol.in.soul.model.Payment;

@Data
public class PaymentOnlyId {
    private Long id;

    public static PaymentOnlyId of(Payment payment) {
        PaymentOnlyId result = new PaymentOnlyId();
        result.setId(payment.getId());
        return result;
    }
}
