package hhplus.concert.domain.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public Payment createBill(Long reservationId, Long userId, int amount) {
        Payment payment = Payment.create(reservationId, userId, amount);
        return paymentRepository.save(payment);
    }
}
