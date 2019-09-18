package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int locationId;

    private String locationName;
    private int locationTypeId;
    private String locationHistory;
    private int universeId;
    private String locationPicture;

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public int getLocationTypeId() {
        return locationTypeId;
    }

    public void setLocationTypeId(int locationTypeId) {
        this.locationTypeId = locationTypeId;
    }

    public String getLocationHistory() {
        return locationHistory;
    }

    public void setLocationHistory(String locationHistory) {
        this.locationHistory = locationHistory;
    }

    public int getUniverseId() {
        return universeId;
    }

    public void setUniverseId(int universeId) {
        this.universeId = universeId;
    }

    public String getLocationPicture() {
        return locationPicture;
    }

    public void setLocationPicture(String locationPicture) {
        this.locationPicture = locationPicture;
    }
}
