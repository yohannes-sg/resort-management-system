package edu.miu.cs.cs544.domain;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

@Entity
@Table(name = "items")
@Data
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int numberOfOccupants;
	
	private LocalDate checkInDate;

	private LocalDate checkOutDate;

	@ManyToOne
	@JoinColumn(name = "reservation_id")
	private Reservation reservation;

	@Embedded
	private AuditData auditData;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	public Item() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumberOfOccupants() {
		return numberOfOccupants;
	}

	public void setNumberOfOccupants(int occupants) {
		this.numberOfOccupants = numberOfOccupants;
	}

	public LocalDate getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(LocalDate checkInDate) {
		this.checkInDate = checkInDate;
	}

	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public AuditData getAuditData() {
		return auditData;
	}

	public void setAuditData(AuditData auditData) {
		this.auditData = auditData;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
