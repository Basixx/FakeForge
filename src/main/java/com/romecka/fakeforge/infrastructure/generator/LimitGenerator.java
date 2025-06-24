package com.romecka.fakeforge.infrastructure.generator;

import com.romecka.fakeforge.domain.limit.LimitProvider;
import com.romecka.fakeforge.infrastructure.db.limit.Limit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LimitGenerator implements LimitProvider {

    public Limit generateDefaultLimit() {
        return new Limit();
    }

}
