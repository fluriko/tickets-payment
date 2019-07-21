package sol.in.soul.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sol.in.soul.controller.model.PaymentExt;
import sol.in.soul.controller.model.PaymentOnlyId;
import sol.in.soul.controller.model.PaymentOnlyStatus;
import sol.in.soul.model.Payment;
import sol.in.soul.service.PaymentService;
import javax.validation.Valid;
import java.net.URI;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @RequestMapping(value = "/api/payment/{id}", method = RequestMethod.GET)
    public ResponseEntity<PaymentOnlyStatus> getById(@PathVariable Long id) {
        log.debug("user now on payment " + id + " page");
        return paymentService.getById(id)
                .map(PaymentOnlyStatus::of)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @RequestMapping(value = "/api/payment", method = RequestMethod.POST)
    public ResponseEntity<PaymentOnlyId> add(@Valid @RequestBody PaymentExt paymentExt) {
        log.debug("user is trying to create payment " + paymentExt);
        return paymentService.create(Payment.of(paymentExt))
                .map(PaymentOnlyId::of)
                .map(p -> ResponseEntity.created(URI.create("/api/payment/id/" + p.getId())).body(p))
                .orElseGet(ResponseEntity.status(HttpStatus.CONFLICT)::build);
    }

    @RequestMapping(value = "/api/payment/id/{id}", method = RequestMethod.GET)
    public ResponseEntity<PaymentOnlyId> showId(@PathVariable Long id) {
        return paymentService.getById(id)
                .map(PaymentOnlyId::of)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }
}
