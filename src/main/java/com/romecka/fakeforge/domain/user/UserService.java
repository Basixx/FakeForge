package com.romecka.fakeforge.domain.user;

import com.romecka.fakeforge.domain.token.TokenService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

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

    public List<User> getUsers() {
        return users.getUsers();
    }

    public String authenticateAndGenerateToken(UserAuthenticationParams userParams) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userParams.emailAddress(), userParams.apiKey()
                    )
            );
            return tokenService.generateToken(userParams.emailAddress());
        } catch (AuthenticationException e) {
            throw new LoginFailedException();
        }
    }

}
