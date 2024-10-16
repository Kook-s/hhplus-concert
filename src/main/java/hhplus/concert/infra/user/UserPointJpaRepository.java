package hhplus.concert.infra.user;

import hhplus.concert.infra.user.entity.UserPointEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPointJpaRepository extends JpaRepository<UserPointEntity, Long> {
}
