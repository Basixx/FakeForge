package com.romecka.fakeforge.infrastructure.db.user;

import com.romecka.fakeforge.domain.user.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class UserMapper {

    UserResponseDto mapToUserResponseDto(User user) {
        return new UserResponseDto(
                user.name(),
                user.lastName(),
                user.emailAddress()
        );
    }

}
