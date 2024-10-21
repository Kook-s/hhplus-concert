package hhplus.concert.infra.payment;

import hhplus.concert.domain.payment.Payment;
import hhplus.concert.domain.payment.PaymentRepository;
import hhplus.concert.infra.payment.entity.PaymentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepository {

    private final PaymentJpaRepository paymentJpaRepository;

    @Override
    public Payment save(Payment payment) {
        return PaymentEntity.of(paymentJpaRepository.save(PaymentEntity.from(payment)));
    }
}
