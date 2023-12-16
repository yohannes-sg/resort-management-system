package edu.miu.cs.cs544.repository;

import edu.miu.cs.cs544.domain.Address;
import edu.miu.cs.cs544.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    // Custom queries or methods if needed
}