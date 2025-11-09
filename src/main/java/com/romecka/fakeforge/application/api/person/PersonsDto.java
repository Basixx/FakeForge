package com.romecka.fakeforge.application.api.person;

import java.util.List;

public record PersonsDto(List<PersonDto> persons, boolean hasNext) {

}
