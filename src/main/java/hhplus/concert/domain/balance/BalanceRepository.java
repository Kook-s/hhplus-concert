package hhplus.concert.domain.balance;

public interface BalanceRepository {

    Balance findBalance(Long userId);

    void save(Balance updateBalance);
}
