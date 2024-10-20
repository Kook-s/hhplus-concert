package hhplus.concert.infra.balance;

import hhplus.concert.infra.balance.entity.BalanceEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface BalanceJpaRepository extends JpaRepository<BalanceEntity, Long> {
    Optional<BalanceEntity> findByUserId(Long userId);
}
