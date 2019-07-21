package sol.in.soul.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import sol.in.soul.controller.model.PaymentOnlyStatus;
import sol.in.soul.model.Payment;
import sol.in.soul.service.PaymentService;
import java.time.LocalDateTime;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PaymentController.class)
public class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentService paymentService;

    private ObjectMapper om = new ObjectMapper();

    @Test
    public void getByIdWithCorrectId() throws Exception {
        Payment payment = new Payment(1L, "MK4545", LocalDateTime.now());
        PaymentOnlyStatus paymentOnlyStatus = PaymentOnlyStatus.of(payment);

        when(paymentService.getById(anyLong())).thenReturn(Optional.of(payment));

        String jsonPaymentStatus = om.writeValueAsString(paymentOnlyStatus);

        mockMvc.perform(get("api/payment/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonPaymentStatus));
    }

    @Test
    public void getByIdWithNotCorrectId() throws Exception {
        when(paymentService.getById(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(get("api/payment/5")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound());
    }

    @Test
    public void addWithCorrectInput() throws Exception {
        Payment payment = new Payment(1L, "RD4545", LocalDateTime.now());

        when(paymentService.create(any(Payment.class))).thenReturn(Optional.of(payment));

        String jsonPayment = om.writeValueAsString(payment);

        mockMvc.perform(post("api/payment")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonPayment))
                .andExpect(status().isCreated());
    }
}
