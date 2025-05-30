package com.romecka.fakeforge.facade;

import com.romecka.fakeforge.domain.dto.UserRequestDto;
import com.romecka.fakeforge.domain.dto.UserResponseDto;
import com.romecka.fakeforge.mapper.UserMapper;
import com.romecka.fakeforge.service.UserService;
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
