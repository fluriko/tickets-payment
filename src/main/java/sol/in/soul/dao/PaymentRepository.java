package sol.in.soul.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sol.in.soul.model.Payment;
import sol.in.soul.model.PaymentStatus;
import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<List<Payment>> getAllByPaymentStatus(PaymentStatus paymentStatus);
}
