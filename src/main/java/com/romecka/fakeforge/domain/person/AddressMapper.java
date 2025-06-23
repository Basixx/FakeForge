package com.romecka.fakeforge.domain.person;

import com.romecka.fakeforge.infrastructure.db.user.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressMapper {

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
