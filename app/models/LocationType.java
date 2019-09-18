package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LocationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int locationTypeId;

    private String locationTypeName;

    public int getLocationTypeId() {
        return locationTypeId;
    }

    public void setLocationTypeId(int locationTypeId) {
        this.locationTypeId = locationTypeId;
    }

    public String getLocationTypeName() {
        return locationTypeName;
    }

    public void setLocationTypeName(String locationTypeName) {
        this.locationTypeName = locationTypeName;
    }
}
