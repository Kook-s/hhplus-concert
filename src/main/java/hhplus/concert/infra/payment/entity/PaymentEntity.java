package hhplus.concert.infra.payment.entity;

import hhplus.concert.domain.payment.Payment;
import hhplus.concert.infra.reservation.entity.ReservationEntity;
import hhplus.concert.infra.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "payment")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESERVATION_ID")
    private ReservationEntity reservation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

    @Column(name = "AMOUNT")
    private int amount;

    @Column(name = "PAYMENT_AT")
    public LocalDateTime paymentAt;


    public static PaymentEntity from(Payment payment) {
        return PaymentEntity.builder()
                .id(payment.id())
                .reservation(ReservationEntity.builder().id(payment.reservationId()).build())
                .user(UserEntity.builder().id(payment.userId()).build())
                .amount(payment.amount())
                .paymentAt(payment.paymentAt())
                .build();
    }

    public static Payment of(PaymentEntity entity) {
        return Payment.builder()
                .id(entity.getId())
                .reservationId(entity.getReservation().getId())
                .userId(entity.getUser().getId())
                .amount(entity.getAmount())
                .paymentAt(entity.getPaymentAt())
                .build();
    }
}
