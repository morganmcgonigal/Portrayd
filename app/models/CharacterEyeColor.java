package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CharacterEyeColor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eyeColorId;

    private String eyeColorName;
    private String eyeColorDescription;

    public int getEyeColorId() {
        return eyeColorId;
    }

    public void setEyeColorId(int eyeColorId) {
        this.eyeColorId = eyeColorId;
    }

    public String getEyeColorName() {
        return eyeColorName;
    }

    public void setEyeColorName(String eyeColorName) {
        this.eyeColorName = eyeColorName;
    }

    public String getEyeColorDescription() {
        return eyeColorDescription;
    }

    public void setEyeColorDescription(String eyeColorDescription) {
        this.eyeColorDescription = eyeColorDescription;
    }
}
