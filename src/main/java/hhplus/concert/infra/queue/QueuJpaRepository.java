package hhplus.concert.infra.queue;


import hhplus.concert.infra.queue.entity.QueueEntity;
import hhplus.concert.support.type.QueueStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface QueuJpaRepository extends JpaRepository<QueueEntity, Long> {
    Optional<QueueEntity> findByUserIdAndStatusNot(Long userId, QueueStatus status);
    Optional<QueueEntity> findByToken(String Token);
    Long countByIdLessThanAndStatus(Long id, QueueStatus statuses);

    @Modifying
    @Query("UPDATE QUEUE q SET q.status = :status, q.expiredAt = :expiredAt WHERE q.id = :id")
    void updateStatusAndExpiredAtById(@Param("id") Long id,
                                      @Param("status") QueueStatus status,
                                      @Param("expiredAt") LocalDateTime expiredAt);

    Long countByStatus(QueueStatus queueStatus);

    @Query("SELECT q FROM QUEUE q WHERE q.expiredAt < :now AND q.status = :queueStatus")
    List<QueueEntity> findExpiredTokens(LocalDateTime now, QueueStatus queueStatus);

    @Query(value = "SELECT q FROM QUEUE q WHERE q.status = 'WAITING' ORDER BY q.createdAt ASC")
    Page<QueueEntity> findWaitingTokens(Pageable pageable);
}
