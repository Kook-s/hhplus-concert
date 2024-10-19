package hhplus.concert.infra.balance;

import hhplus.concert.infra.balance.entity.BalanceEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

public interface BalanceJpaRepository extends JpaRepository<BalanceEntity, Long> {

    @Lock(LockModeType.OPTIMISTIC)
    BalanceEntity findByUserId(long userId);
}
