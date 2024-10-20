package hhplus.concert.domain.balance;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BalanceService {

    private final BalanceRepository balanceRepository;

    public Balance getBalance(Long userId) {
        return balanceRepository.findBalance(userId);
    }

    public Balance chargeBalance(Long userId, Long amount) {
        Balance balance = balanceRepository.findBalance(userId);
        Balance updateBalance = balance.charge(amount);
        balanceRepository.save(updateBalance);
        return updateBalance;
    }

    public void useBalance(Balance balance, int amount) {
        Balance useBalance = balance.useeBalance(amount);
        balanceRepository.save(useBalance);
    }
}
