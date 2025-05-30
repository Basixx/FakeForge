package com.romecka.fakeforge.mapper;

import com.romecka.fakeforge.domain.person.Address;
import com.romecka.fakeforge.domain.person.AddressDto;
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
