package com.romecka.fakeforge.domain.user;

import com.romecka.fakeforge.domain.token.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class UserService {

    private final Users users;

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    public User registerUser(UserParams user) {
        return users.registerUser(user);
    }

    public List<User> getUsers(int page, int size) {
        return users.getUsers(page, size);
    }

    public String authenticateAndGenerateToken(UserAuthenticationParams userParams) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userParams.emailAddress(), userParams.password()
                    )
            );
            return tokenService.generateToken(userParams.emailAddress());
        } catch (AuthenticationException e) {
            throw new LoginFailedException();
        }
    }

}
