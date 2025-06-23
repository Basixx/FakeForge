package com.romecka.fakeforge.infrastructure.db.person;

import com.romecka.fakeforge.domain.person.AddressDto;
import com.romecka.fakeforge.infrastructure.db.user.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class AddressMapper {

    public AddressDto mapToAddressDto(Address address) {
        return new AddressDto(
                address.getStreet(),
                address.getBuildingNumber(),
                address.getApartmentNumber(),
                address.getPostalCode(),
                address.getCity()
        );
    }

}
