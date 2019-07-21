package sol.in.soul.controller.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import sol.in.soul.model.Payment;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class PaymentExt {

    @NotBlank
    @Size(min = 4, max = 50)
    private String routNumber;

    @NotBlank
    @DateTimeFormat
    private String departuresDateAndTime;

    public static PaymentExt of(Payment payment) {
        PaymentExt result = new PaymentExt();
        result.setRoutNumber(payment.getRoutNumber());
        result.setDeparturesDateAndTime(payment.getDeparturesDateAndTime().toString());
        return result;
    }
}
