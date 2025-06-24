package com.romecka.fakeforge.infrastructure.db.user;

import com.romecka.fakeforge.domain.user.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class UserMapper {

    UserResponseDto mapToUserResponseDto(UserEntity userEntity) {
        return new UserResponseDto(
                userEntity.name(),
                userEntity.lastName(),
                userEntity.emailAddress()
        );
    }

}
