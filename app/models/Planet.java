package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Planet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int planetId;

    private String planetName;
    private int universeId;
    private String planetPicture;

    public int getPlanetId() {
        return planetId;
    }

    public void setPlanetId(int planetId) {
        this.planetId = planetId;
    }

    public String getPlanetName() {
        return planetName;
    }

    public void setPlanetName(String planetName) {
        this.planetName = planetName;
    }

    public int getUniverseId() {
        return universeId;
    }

    public void setUniverseId(int universeId) {
        this.universeId = universeId;
    }

    public String getPlanetPicture() {
        return planetPicture;
    }

    public void setPlanetPicture(String planetPicture) {
        this.planetPicture = planetPicture;
    }
}
