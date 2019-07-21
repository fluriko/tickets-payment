package sol.in.soul.controller.model;

import lombok.Data;
import sol.in.soul.model.Payment;

@Data
public class PaymentOnlyStatus {
    private String paymentStatus;

    public static PaymentOnlyStatus of(Payment payment) {
        PaymentOnlyStatus result = new PaymentOnlyStatus();
        result.setPaymentStatus(payment.getPaymentStatus().toString());
        return result;
    }
}
