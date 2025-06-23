package com.romecka.fakeforge.domain.user;

import com.romecka.fakeforge.application.api.user.UserRequestDto;
import com.romecka.fakeforge.application.api.user.UserResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserFacade {

    private final UserService userService;

    private final UserMapper userMapper;

    public UserResponseDto registerUser(UserRequestDto userRequestDto) {
        return userMapper.mapToUserResponseDto(
                userService.registerUser(userMapper.mapToUser(userRequestDto))
        );
    }

}
