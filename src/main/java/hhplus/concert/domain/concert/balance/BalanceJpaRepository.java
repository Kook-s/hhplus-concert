package hhplus.concert.domain.concert.balance;

import hhplus.concert.domain.concert.balance.entity.BalanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BalanceJpaRepository extends JpaRepository<BalanceEntity, Long> {
    Optional<BalanceEntity> findByUserId(Long userId);
}
