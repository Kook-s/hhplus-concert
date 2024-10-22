
package hhplus.concert.domain.balance;

import hhplus.concert.support.exception.CustomException;
import hhplus.concert.support.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
public record Balance(
        Long id,
        Long userId,
        Long amount,
        LocalDateTime lastUpdateAt
) {

    public Balance charge(Long amount) {
        return Balance.builder()
                .id(this.id)
                .userId(this.userId)
                .amount(this.amount)
                .lastUpdateAt(LocalDateTime.now())
                .build();
    }

    public Balance useBalance(int useAmount) {
        if(this.amount < useAmount) {
            throw new CustomException(ErrorCode.PAYMENT_FAILED_AMOUNT);
        }

        return Balance.builder()
                .id(id)
                .userId(userId)
                .amount(amount - useAmount)
                .lastUpdateAt(LocalDateTime.now())
                .build();
    }


}
