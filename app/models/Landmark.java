package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Landmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int landmarkId;

    private String landmarkName;
    private int countryId;
    private String landmarkPicture;

    public int getLandmarkId() {
        return landmarkId;
    }

    public void setLandmarkId(int landmarkId) {
        this.landmarkId = landmarkId;
    }

    public String getLandmarkName() {
        return landmarkName;
    }

    public void setLandmarkName(String landmarkName) {
        this.landmarkName = landmarkName;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getLandmarkPicture() {
        return landmarkPicture;
    }

    public void setLandmarkPicture(String landmarkPicture) {
        this.landmarkPicture = landmarkPicture;
    }
}
