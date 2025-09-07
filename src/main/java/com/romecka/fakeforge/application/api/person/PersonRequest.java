package com.romecka.fakeforge.application.api.person;

import com.romecka.fakeforge.domain.person.Gender;
import com.romecka.fakeforge.domain.person.PersonParams;

public record PersonRequest(Integer age, Gender gender) implements PersonParams {

}
