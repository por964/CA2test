package dto;

import entities.Phone;

/**
 *
 * @author claes
 */
public class PhoneDTO {

    private int id;
    private int number;
    private String description;

    public PhoneDTO(int id, int number, String description) {
        this.id = id;
        this.number = number;
        this.description = description;
    }
    
    public PhoneDTO(Phone phone) {
        this.id = phone.getId();
        this.number = phone.getNumber();
        this.description = phone.getDescription();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "PhoneDTO{" + "id=" + id + ", number=" + number + ", description=" + description + '}';
    }
    
}
