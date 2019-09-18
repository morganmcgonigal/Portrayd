package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CharacterPersonalityType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int personalityTypeId;

    private String personalityTypeName;
    private String personalityTypeDescription;

    public int getPersonalityTypeId() {
        return personalityTypeId;
    }

    public void setPersonalityTypeId(int personalityTypeId) {
        this.personalityTypeId = personalityTypeId;
    }

    public String getPersonalityTypeName() {
        return personalityTypeName;
    }

    public void setPersonalityTypeName(String personalityTypeName) {
        this.personalityTypeName = personalityTypeName;
    }

    public String getPersonalityTypeDescription() {
        return personalityTypeDescription;
    }

    public void setPersonalityTypeDescription(String personalityTypeDescription) {
        this.personalityTypeDescription = personalityTypeDescription;
    }
}
