package sol.in.soul.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sol.in.soul.dao.PaymentRepository;
import sol.in.soul.model.Payment;
import sol.in.soul.model.PaymentStatus;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Optional<Payment> getById(Long id) {
        log.debug("retrieving payment from db with id: " + id);
        return paymentRepository.findById(id);
    }

    @Override
    public Optional<Payment> create(Payment payment) {
        log.debug("creating payment: " + payment);
        return Optional.of(paymentRepository.save(payment));
    }

    @Override
    public Optional<Payment> update(Payment payment) {
        log.debug("updating payment " + payment);
        return Optional.of(paymentRepository.save(payment));
    }

    @Override
    public Optional<List<Payment>> getAllByPaymentStatus(PaymentStatus paymentStatus) {
        log.debug("retrieving all payments with status " + paymentStatus);
        return paymentRepository.getAllByPaymentStatus(paymentStatus);
    }
}
