package hhplus.concert.application.facade;

import hhplus.concert.domain.balance.Balance;
import hhplus.concert.domain.balance.BalanceService;
import hhplus.concert.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BalanceFacade {

    private final UserService userService;
    private final BalanceService balanceService;

    // 잔행 조회
    public Balance getBalance(Long userId) {
        userService.existsUser(userId);
        return balanceService.getBalance(userId);
    }

    // 잔핵 사용 및 충전
    public Balance chargeBalance(Long userId, Long amount) {
        userService.existsUser(userId);
        return balanceService.chargeBalance(userId, amount);
    }
}
