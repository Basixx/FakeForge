package com.romecka.fakeforge.mapper;

import com.romecka.fakeforge.domain.user.User;
import com.romecka.fakeforge.domain.user.UserRequestDto;
import com.romecka.fakeforge.domain.user.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMapper {

    private final LimitMapper limitMapper;

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
                user.getEmailAddress(),
                limitMapper.mapToLimitDto(user.getLimit())
        );
    }

}
