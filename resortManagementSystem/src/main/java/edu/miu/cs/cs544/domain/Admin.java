package edu.miu.cs.cs544.domain;

import jakarta.persistence.*;
import lombok.Data;
    @Entity
    @Data
    public class Admin extends User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String firstName;

        private String lastName;

        private String email;

        @Embedded
        private AuditData auditData;

        public Admin() {
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

        public AuditData getAuditData() {
            return auditData;
        }

        public void setAuditData(AuditData auditData) {
            this.auditData = auditData;
        }
    }