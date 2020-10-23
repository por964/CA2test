package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author claes
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "CityInfo.deleteAllRows", query = "DELETE from CityInfo"),
    @NamedQuery(name = "CityInfo.getAllRows", query = "SELECT c from CityInfo c")})
public class CityInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(length = 4)
    private String zipCode;
    @Column(length = 35)
    private String city;

    @OneToMany(mappedBy = "cityinfo" , cascade =  CascadeType.REMOVE)
    List<Address> addresses;

    public CityInfo() {
        this.addresses = new ArrayList();
    }

    public CityInfo(String zipCode, String city) {
        this.zipCode = zipCode;
        this.city = city;
        this.addresses = new ArrayList<>();
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public void addAddress(Address address) {
        this.addresses.add(address);
        if (address != null) {
            address.setCityinfo(this);
        }
    }

    public List<Address> getAddresses() {
        return addresses;
    }

}
