package com.romecka.fakeforge.infrastructure.generator;

import com.romecka.fakeforge.domain.limit.LimitProvider;
import com.romecka.fakeforge.infrastructure.db.limit.LimitEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LimitGenerator implements LimitProvider {

    public LimitEntity generateDefaultLimit() {
        return new LimitEntity();
    }

}
