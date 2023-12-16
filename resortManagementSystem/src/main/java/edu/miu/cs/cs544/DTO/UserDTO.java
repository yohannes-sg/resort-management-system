package edu.miu.cs.cs544.DTO;

import edu.miu.cs.cs544.domain.AuditData;
import edu.miu.cs.cs544.domain.Customer;
import edu.miu.cs.cs544.domain.UserType;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;

public abstract class UserDTO {

    private Long id;

    private String userName;

    private String userPass;

    private Boolean active;

    private CustomerDTO customerDTO;

    private UserType type;

    private AuditData auditData;

    public UserDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public AuditData getAuditData() {
        return auditData;
    }

    public void setAuditData(AuditData auditData) {
        this.auditData = auditData;
    }
}