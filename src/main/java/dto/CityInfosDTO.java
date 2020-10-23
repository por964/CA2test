package dto;

import entities.CityInfo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author claes
 */
public class CityInfosDTO {
    
    List<CityInfoDTO> allCities= new ArrayList();
    
    public CityInfosDTO(List<CityInfo> cityInfoEntities) {
        cityInfoEntities.forEach((c) -> {
            allCities.add(new CityInfoDTO(c));
        });
    }
    
    public List<CityInfoDTO> getAll() {
        return allCities;
    }


    
    
}
