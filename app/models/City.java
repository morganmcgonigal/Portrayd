package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cityId;

    private String cityName;
    private int countryId;
    private boolean isCapitalCity;
    private String cityPicture;

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public boolean isCapitalCity() {
        return isCapitalCity;
    }

    public void setCapitalCity(boolean capitalCity) {
        isCapitalCity = capitalCity;
    }

    public String getCityPicture() {
        return cityPicture;
    }

    public void setCityPicture(String cityPicture) {
        this.cityPicture = cityPicture;
    }
}
