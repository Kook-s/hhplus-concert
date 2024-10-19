package hhplus.concert.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void existsUser(Long userId) {
        userRepository.existsUser(userId);
    }
}
