package sol.in.soul.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import sol.in.soul.dao.PaymentRepository;
import sol.in.soul.model.Payment;
import sol.in.soul.model.PaymentStatus;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class PaymentServiceImplTest {

    @TestConfiguration
    static class PaymentServiceImplTestContextConfiguration {
        @Bean
        public PaymentService paymentService() {
            return new PaymentServiceImpl();
        }
    }

    @Autowired
    private PaymentService paymentService;

    @MockBean
    private PaymentRepository paymentRepository;

    private Payment payment;

    @Before
    public void setUp() {
        payment = new Payment(55L, "MK456", LocalDateTime.now());
        Optional<List<Payment>> payments = Optional.of(Collections.singletonList(payment));
        Mockito.when(paymentRepository.getAllByPaymentStatus(payment.getPaymentStatus())).thenReturn(payments);
        Mockito.when(paymentRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(payment));
        Mockito.when(paymentRepository.save(Mockito.any(Payment.class))).thenReturn(payment);
    }

    @Test
    public void getAllByPaymentStatusWhenValidStatus() {
        PaymentStatus status = PaymentStatus.IN_PROSES;
        List<Payment> found = paymentService.getAllByPaymentStatus(status).orElseGet(Collections::emptyList);
        Assert.assertTrue(found.stream().map(p -> p.getPaymentStatus()).allMatch(ps -> ps.equals(status)));
    }

    @Test
    public void getByIdWithValidId() {
        Long id = 55L;
        Payment found = paymentService.getById(id).orElseGet(Payment::new);
        Assert.assertEquals(found.getId(), id);
    }

    @Test
    public void getByIdWithNotValidId() {
        Long id = 88L;
        Payment found = paymentService.getById(id).orElseGet(Payment::new);
        Assert.assertNotEquals(found.getId(), id);
    }

    @Test
    public void createWithCorrectInput() {
        Payment found = paymentService.create(payment).orElseGet(Payment::new);
        Assert.assertEquals(payment, found);
    }

    @Test
    public void updateWithCorrectInput() {
        payment.setPaymentStatus(PaymentStatus.DONE);
        Payment found = paymentService.update(payment).orElseGet(Payment::new);
        Assert.assertEquals(payment, found);
    }
}
