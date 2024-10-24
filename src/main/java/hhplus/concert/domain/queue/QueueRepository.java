package hhplus.concert.domain.queue;

import hhplus.concert.support.type.QueueStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface QueueRepository {

    Queue findQueue(Long userId);
    Queue findQueue(String token);
    Long findActiveCount();
    Long findCurrentRank();
    Long findUserRank(Long queueId);
    Queue save(Queue token);
    void expireToken(Queue expiredToken);

    List<Queue> findExpiredTokens(LocalDateTime now, QueueStatus queueStatus);

    List<Queue> findWaitingTokens(long neededTokens);

    long findByStatus(QueueStatus queueStatus);
}
