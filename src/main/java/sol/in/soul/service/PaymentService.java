package sol.in.soul.service;

import sol.in.soul.model.Payment;
import sol.in.soul.model.PaymentStatus;
import java.util.List;
import java.util.Optional;

public interface PaymentService {

    Optional<Payment> getById(Long id);

    Optional<Payment> create(Payment payment);

    Optional<Payment> update(Payment payment);

    Optional<List<Payment>> getAllByPaymentStatus(PaymentStatus paymentStatus);
}
