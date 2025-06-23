package com.romecka.fakeforge.domain.user;

import com.romecka.fakeforge.application.api.user.UserRequestDto;
import com.romecka.fakeforge.application.api.user.UserResponseDto;
import com.romecka.fakeforge.infrastructure.db.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMapper {

    public User mapToUser(UserRequestDto userRequestDto) {
        return User.builder()
                .name(userRequestDto.name())
                .lastName(userRequestDto.lastName())
                .emailAddress(userRequestDto.emailAddress())
                .build();
    }

    public UserResponseDto mapToUserResponseDto(User user) {
        return new UserResponseDto(
                user.getName(),
                user.getLastName(),
                user.getEmailAddress()
        );
    }

}
