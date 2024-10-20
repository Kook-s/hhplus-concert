package hhplus.concert.infra.balance;

import hhplus.concert.domain.balance.Balance;
import hhplus.concert.domain.balance.BalanceRepository;
import hhplus.concert.infra.balance.entity.BalanceEntity;
import hhplus.concert.support.exception.CustomException;
import hhplus.concert.support.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BalanceRepositoryImpl implements BalanceRepository {

    private final BalanceJpaRepository balanceJpaRepository;

    @Override
    public Balance findBalance(Long userId) {

        return balanceJpaRepository.findByUserId(userId)
                .map(BalanceEntity::of)
                .orElseThrow(() -> new CustomException(ErrorCode.INTERNAL_SERVER_ERROR));
    }

    @Override
    public void save(Balance updateBalance) {
        BalanceEntity entity = BalanceEntity.from(updateBalance);
        balanceJpaRepository.save(entity);
    }
}
