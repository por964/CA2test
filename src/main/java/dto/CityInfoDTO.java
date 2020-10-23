package dto;

import entities.CityInfo;

/**
 *
 * @author claes
 */
public class CityInfoDTO {
    
    private String zip;
    private String city;

    public CityInfoDTO(String zip, String city) {
        this.zip = zip;
        this.city = city;
    }
    
    public CityInfoDTO(CityInfo cityinfo) {
        this.zip = cityinfo.getZipCode();
        this.city = cityinfo.getCity();
    } 

    @Override
    public String toString() {
        return "ZIP: " + zip + ", city: " + city;
    }

    
    
}
