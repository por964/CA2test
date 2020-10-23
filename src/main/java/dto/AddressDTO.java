package dto;

import entities.Address;

/**
 *
 * @author claes
 */
public class AddressDTO {
    
    private String street;
    private String additInfo;

    public AddressDTO(String street, String additInfo) {
        this.street = street;
        this.additInfo = additInfo;
    }
    
    public AddressDTO(Address adr) {
        this.street = adr.getStreet();
        this.additInfo = adr.getAdditionalInfo();
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAdditInfo() {
        return additInfo;
    }

    public void setAdditInfo(String additInfo) {
        this.additInfo = additInfo;
    }
    
    
    
}
