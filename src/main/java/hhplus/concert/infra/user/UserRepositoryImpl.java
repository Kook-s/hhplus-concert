package hhplus.concert.infra.user;

import hhplus.concert.domain.user.UserRepository;
import hhplus.concert.support.exception.CustomException;
import hhplus.concert.support.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public void existsUser(Long userId) {
        boolean existsById = userJpaRepository.existsById(userId);

        if (!existsById) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }
    }
}
