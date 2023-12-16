package edu.miu.cs.cs544.DTO;

import edu.miu.cs.cs544.domain.ReservationStatus;

import java.util.List;

public class ReservationDTO {

    private Long id;
    private ReservationStatus status;
    private List<ItemDTO> itemsDTO;
    private CustomerDTO customerDTO;

    public ReservationDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public List<ItemDTO> getItemsDTO() {
        return itemsDTO;
    }

    public void setItemsDTO(List<ItemDTO> itemsDTO) {
        this.itemsDTO = itemsDTO;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

}