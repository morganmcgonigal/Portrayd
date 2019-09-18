package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CharacterRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int characterRoleId;

    private String characterRoleName;
    private String characterRoleDescription;

    public int getCharacterRoleId() {
        return characterRoleId;
    }

    public void setCharacterRoleId(int characterRoleId) {
        this.characterRoleId = characterRoleId;
    }

    public String getCharacterRoleName() {
        return characterRoleName;
    }

    public void setCharacterRoleName(String characterRoleName) {
        this.characterRoleName = characterRoleName;
    }

    public String getCharacterRoleDescription() {
        return characterRoleDescription;
    }

    public void setCharacterRoleDescription(String characterRoleDescription) {
        this.characterRoleDescription = characterRoleDescription;
    }
}
