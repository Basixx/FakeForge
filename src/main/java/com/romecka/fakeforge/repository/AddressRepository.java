package com.romecka.fakeforge.repository;

import com.romecka.fakeforge.domain.person.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
