package sol.in.soul.service;

import org.springframework.stereotype.Component;
import sol.in.soul.model.Payment;
import sol.in.soul.model.PaymentStatus;
import sol.in.soul.util.RandomNumberGenerator;

@Component
public class PaymentGatewayService {
    public static void setRandomPaymentStatus(Payment payment) {
        int randomNumber = RandomNumberGenerator.generate(PaymentStatus.values().length);
        PaymentStatus randomPaymentStatus = PaymentStatus.values()[randomNumber];
        payment.setPaymentStatus(randomPaymentStatus);
    }
}
