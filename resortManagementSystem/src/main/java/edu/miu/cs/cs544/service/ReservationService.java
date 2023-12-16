package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.DTO.ReservationDTO;
import edu.miu.cs.cs544.domain.Reservation;
import edu.miu.cs.cs544.repository.ReservationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ModelMapper modelMapper; // Example: Using ModelMapper for mapping

    public ReservationDTO getReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Reservation not found"));
        return convertToDTO(reservation);
    }

    public List<ReservationDTO> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream()
                .map(reservation -> modelMapper.map(reservation, ReservationDTO.class))
                .collect(Collectors.toList());
    }

    public ReservationDTO createReservation(ReservationDTO reservationDTO) {
        Reservation reservation = convertToEntity(reservationDTO);
        // Perform validations or additional logic before saving
        Reservation savedReservation = reservationRepository.save(reservation);
        return convertToDTO(savedReservation);
    }

    public ReservationDTO updateReservation(Long id, ReservationDTO reservationDTO) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if (optionalReservation.isPresent()) {
            Reservation existingReservation = optionalReservation.get();

            // Update existing reservation fields
//            existingReservation.setCheckInDate(reservationDTO.getCheckInDate());
//            existingReservation.setCheckOutDate(reservationDTO.getCheckOutDate());
//            existingReservation.setNumberOfOccupants(reservationDTO.getNumberOfOccupants());
            existingReservation.setStatus(reservationDTO.getStatus()); // Set the status

            // Save the updated reservation
            Reservation updatedReservation = reservationRepository.save(existingReservation);
            return modelMapper.map(updatedReservation, ReservationDTO.class);
        } else {
            // Handle the case when the reservation with the provided ID is not found
            return null;
        }
    }

    public boolean deleteReservation(Long id) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if (optionalReservation.isPresent()) {
            Reservation reservationToDelete = optionalReservation.get();
            reservationRepository.delete(reservationToDelete);
            return true;
        } else {
            // Handle the case when the reservation with the provided ID is not found
            return false;
        }
    }

    private ReservationDTO convertToDTO(Reservation reservation) {
        return modelMapper.map(reservation, ReservationDTO.class);
    }

    private Reservation convertToEntity(ReservationDTO reservationDTO) {
        return modelMapper.map(reservationDTO, Reservation.class);
    }
}