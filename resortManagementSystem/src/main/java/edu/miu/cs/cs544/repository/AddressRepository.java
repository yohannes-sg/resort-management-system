package edu.miu.cs.cs544.repository;

import edu.miu.cs.cs544.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    // Custom queries or methods if needed
}