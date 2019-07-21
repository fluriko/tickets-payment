package sol.in.soul.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import sol.in.soul.controller.model.PaymentExt;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "PAYMENTS")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PAYMENT_ID")
    private Long id;

    @Column(name = "ROUT_NUMBER")
    private String routNumber;

    @Column(name = "DEPARTURES")
    private LocalDateTime departuresDateAndTime;

    @Column(name = "PAYMENT_STATUS")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus = PaymentStatus.IN_PROSES;

    public Payment(Long id) {
        this.id = id;
    }

    public Payment(Long id, String routNumber, LocalDateTime departuresDateAndTime) {
        this.id = id;
        this.routNumber = routNumber;
        this.departuresDateAndTime = departuresDateAndTime;
    }

    public static Payment of(PaymentExt paymentExt) {
        Payment result = new Payment();
        result.setRoutNumber(paymentExt.getRoutNumber());
        result.setDeparturesDateAndTime(LocalDateTime.parse(paymentExt.getDeparturesDateAndTime()));
        return result;
    }
}
