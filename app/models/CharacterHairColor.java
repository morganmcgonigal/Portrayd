package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CharacterHairColor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hairColorId;

    private String hairColorName;
    private String hairColorDescription;

    public int getHairColorId() {
        return hairColorId;
    }

    public void setHairColorId(int hairColorId) {
        this.hairColorId = hairColorId;
    }

    public String getHairColorName() {
        return hairColorName;
    }

    public void setHairColorName(String hairColorName) {
        this.hairColorName = hairColorName;
    }

    public String getHairColorDescription() {
        return hairColorDescription;
    }

    public void setHairColorDescription(String hairColorDescription) {
        this.hairColorDescription = hairColorDescription;
    }
}
