package hhplus.concert.domain.model;

import hhplus.concert.domain.balance.Balance;
import hhplus.concert.support.exception.CustomException;
import hhplus.concert.support.exception.ErrorCode;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BalanceTest {

    @Test
    void 잔액_사용_성공() {
        // given
        Balance balance = Balance.builder()
                .id(1L)
                .userId(1L)
                .amount(10000L)  // 잔액 10,000원
                .lastUpdateAt(LocalDateTime.now())
                .build();

        // when
        Balance updatedBalance = balance.useBalance(5000);  // 5,000원 사용

        // then
        assertThat(updatedBalance.amount()).isEqualTo(5000L);  // 남은 잔액 5,000원
        assertThat(updatedBalance.lastUpdateAt()).isAfter(balance.lastUpdateAt());  // 시간 업데이트 확인
    }

    @Test
    void 잔액_사용_실패_잔액부족() {
        // given
        Balance balance = Balance.builder()
                .id(1L)
                .userId(1L)
                .amount(3000L)  // 잔액 3,000원
                .lastUpdateAt(LocalDateTime.now())
                .build();

        // when / then
        assertThatThrownBy(() -> balance.useBalance(5000))  // 5,000원 사용 시도
                .isInstanceOf(CustomException.class)
                .hasMessage(ErrorCode.PAYMENT_FAILED_AMOUNT.getMessage());
    }

}