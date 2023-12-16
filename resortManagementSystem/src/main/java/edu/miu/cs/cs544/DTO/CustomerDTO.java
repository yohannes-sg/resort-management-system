package edu.miu.cs.cs544.DTO;

import edu.miu.cs.cs544.domain.AuditData;

import java.util.List;

public class CustomerDTO extends UserDTO {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;
    private AddressDTO billingAddressDTO;
    private AddressDTO physicalAddressDTO;

    private List<ReservationDTO> reservationsDTO;

    private AuditData auditData;

    public CustomerDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AddressDTO getBillingAddressDTO() {
        return billingAddressDTO;
    }

    public void setBillingAddressDTO(AddressDTO billingAddressDTO) {
        this.billingAddressDTO = billingAddressDTO;
    }

    public AddressDTO getPhysicalAddressDTO() {
        return physicalAddressDTO;
    }

    public void setPhysicalAddressDTO(AddressDTO physicalAddressDTO) {
        this.physicalAddressDTO = physicalAddressDTO;
    }

    public List<ReservationDTO> getReservationsDTO() {
        return reservationsDTO;
    }

    public void setReservationsDTO(List<ReservationDTO> reservationsDTO) {
        this.reservationsDTO = reservationsDTO;
    }

    public AuditData getAuditData() {
        return auditData;
    }

    public void setAuditData(AuditData auditData) {
        this.auditData = auditData;
    }
}