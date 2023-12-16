package edu.miu.cs.cs544.repository;

import edu.miu.cs.cs544.domain.Address;
import edu.miu.cs.cs544.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByIdAndReservationId(Long itemId, Long reservationId);
    List<Item> findByReservationId(Long reservationId);
    // Custom queries or methods if needed
}