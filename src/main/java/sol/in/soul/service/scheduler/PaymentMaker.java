package sol.in.soul.service.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sol.in.soul.model.Payment;
import sol.in.soul.model.PaymentStatus;
import sol.in.soul.service.PaymentGatewayService;
import sol.in.soul.service.PaymentService;
import sol.in.soul.service.scheduler.exception.NoPaymentLeftException;

@Component
@Slf4j
public class PaymentMaker {

    @Autowired
    private PaymentService paymentService;

    @Scheduled(initialDelay = 60000, fixedDelay = 60000)
    public void makePayment() {
        try {
            Payment payment = paymentService
                    .getAllByPaymentStatus(PaymentStatus.IN_PROSES)
                    .map(l -> l.get(0))
                    .orElseThrow(NoPaymentLeftException::new);
            log.info("started making payment " + payment);
            PaymentGatewayService.setRandomPaymentStatus(payment);
            paymentService.update(payment);
            log.info("result " + payment);
        } catch (Exception e) {
            log.error("No payments left!" + e);
        }
    }
}

