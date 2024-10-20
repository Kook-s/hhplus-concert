package hhplus.concert.infra.queue;

import hhplus.concert.domain.queue.Queue;
import hhplus.concert.domain.queue.QueueRepository;
import hhplus.concert.infra.queue.entity.QueueEntity;
import hhplus.concert.support.exception.CustomException;
import hhplus.concert.support.exception.ErrorCode;
import hhplus.concert.support.type.QueueStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class QueueRepositoryImpl implements QueueRepository {

    private final QueuJpaRepository queuJpaRepository;

    @Override
    public Queue findQueue(Long userId) {
        return queuJpaRepository.findByUserIdAndStatusNot(userId, QueueStatus.EXPIRED)
                .map(QueueEntity::of)
                .orElse(null);
    }

    @Override
    public Queue findQueue(String token) {
        return queuJpaRepository.findByToken(token)
                .map(QueueEntity::of)
                .orElseThrow(() -> new CustomException(ErrorCode.TOKEN_NOT_FOUND));
    }

    @Override
    public Long findActiveCount() {
        return queuJpaRepository.countByStatus(QueueStatus.ACTIVE);
    }

    @Override
    public Long findCurrentRank() {
        return queuJpaRepository.countByStatus(QueueStatus.WAITING);
    }

    @Override
    public Long findUserRank(Long queueId) {
        return queuJpaRepository.countByIdLessThanAndStatus(queueId, QueueStatus.WAITING) + 1;
    }

    @Override
    public Queue save(Queue token) {
        return QueueEntity.of(queuJpaRepository.save(new QueueEntity().from(token)));
    }

    @Override
    public void expireToken(Queue token) {
        queuJpaRepository.updateStatusAndExpiredAtById(token.id(), token.status(), token.expiredAt());
    }

    @Override
    public List<Queue> findExpiredTokens(LocalDateTime now, QueueStatus queueStatus) {
        return queuJpaRepository.findExpiredTokens(now, queueStatus)
                .stream()
                .map(QueueEntity::of)
                .toList();
    }

    @Override
    public List<Queue> findWaitingTokens(long limit) {
        Pageable pageable = PageRequest.of(0, (int) limit);
        return queuJpaRepository.findWaitingTokens(pageable).getContent()
                .stream()
                .map(QueueEntity::of)
                .toList();
    }
}
