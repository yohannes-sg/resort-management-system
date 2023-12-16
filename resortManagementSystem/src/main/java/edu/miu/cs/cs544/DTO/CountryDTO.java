package edu.miu.cs.cs544.DTO;

import edu.miu.cs.cs544.domain.AuditData;

public class CountryDTO {

    private Long id;
    private String countryName;
    private AuditData auditData;

    public CountryDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public AuditData getAuditData() {
        return auditData;
    }

    public void setAuditData(AuditData auditData) {
        this.auditData = auditData;
    }
}