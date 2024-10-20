package hhplus.concert.interfaces.balance;

import hhplus.concert.application.facade.BalanceFacade;
import hhplus.concert.domain.balance.Balance;
import hhplus.concert.interfaces.balance.dto.BalanceDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BalanceController {

    private final BalanceFacade balanceFacade;

    /**
     * 잔액을 조회한다.
     * @param userId
     * @return
     */
    @GetMapping("/users/{userId}/balance")
    public ResponseEntity<BalanceDto.BalanceResponse> getBalance(@PathVariable Long userId) {
        Balance balance = balanceFacade.getBalance(userId);

        return ResponseEntity.ok(
                BalanceDto.BalanceResponse.builder()
                        .userId(balance.userId())
                        .currentAmount(balance.amount())
                        .build()
        );
    }

    /**
     * 잔액을 충전한다.
     * @param userId
     * @param request
     * @return
     */
    @PostMapping("/users/{userId}/balance")
    public ResponseEntity<BalanceDto.BalanceResponse> chargeBalance(
            @PathVariable Long userId,
            @Valid @RequestBody BalanceDto.BalanceRequest request
    ) {

        Balance balance = balanceFacade.getBalance(userId);
        return ResponseEntity.ok(
                BalanceDto.BalanceResponse.builder()
                        .userId(balance.userId())
                        .currentAmount(balance.amount())
                        .build()
        );
    }
}

