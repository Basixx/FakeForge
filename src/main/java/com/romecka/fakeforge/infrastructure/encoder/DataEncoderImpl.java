package com.romecka.fakeforge.infrastructure.encoder;

import com.romecka.fakeforge.domain.user.DataEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DataEncoderImpl implements DataEncoder {

    private final PasswordEncoder encoder;

    @Override
    public String encode(String data) {
        return encoder.encode(data);
    }

}
