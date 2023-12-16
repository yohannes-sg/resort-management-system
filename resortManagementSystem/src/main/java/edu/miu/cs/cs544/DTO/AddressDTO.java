package edu.miu.cs.cs544.DTO;

import edu.miu.cs.cs544.domain.AuditData;

public class AddressDTO {

    private Integer id;
    private String street;
    private String city;
    private String postalCode;
    private Long stateId;

    private AuditData auditData;
    private  StateDTO stateDTO;
    private AddressDTO billingAddressDTO;
    private AddressDTO physicalAddressDTO;

    public AddressDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public AuditData getAuditData() {
        return auditData;
    }

    public void setAuditData(AuditData auditData) {
        this.auditData = auditData;
    }

    public StateDTO getStateDTO() {
        return stateDTO;
    }

    public void setStateDTO(StateDTO stateDTO) {
        this.stateDTO = stateDTO;
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

}