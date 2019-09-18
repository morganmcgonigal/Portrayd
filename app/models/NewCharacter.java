package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class NewCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int characterId;

    private String characterName;
    private int characterRoleId;
    private String characterAlias;
    private int genderId;
    private int characterAge;
    private double characterWeight;
    private double characterHeightInCM;
    private int hairColorId;
    private String characterHairStyle;
    private String characterFacialHair;
    private int eyeColorId;
    private int raceId;
    private String characterSkinTone;
    private String characterBodyType;
    private String identifyingMark;
    private String characterMannerism;
    private String characterMotivation;
    private String characterFlaw;
    private String characterPrejudice;
    private String characterTalent;
    private String characterHobby;
    private int personalityTypeId;
    private String characterCondition;
    private int religionId;
    private String characterOccupation;
    private String characterFavColor;
    private String characterFavFood;
    private String characterFavPossession;
    private String characterFavPosessionDescription;
    private int weaponId;
    private String characterFavAnimal;
    private String characterBirthday;
    private int locationId;
    private int cityId;
    private int countryId;
    private int planetId;
    private String characterEducation;
    private String characterBackground;
    private String characterPicture;
    private String characterNote;
    private int languageId;

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public int getCharacterRoleId() {
        return characterRoleId;
    }

    public void setCharacterRoleId(int characterRoleId) {
        this.characterRoleId = characterRoleId;
    }

    public String getCharacterAlias() {
        return characterAlias;
    }

    public void setCharacterAlias(String characterAlias) {
        this.characterAlias = characterAlias;
    }

    public int getGenderId() {
        return genderId;
    }

    public void setGenderId(int genderId) {
        this.genderId = genderId;
    }

    public int getCharacterAge() {
        return characterAge;
    }

    public void setCharacterAge(int characterAge) {
        this.characterAge = characterAge;
    }

    public double getCharacterWeight() {
        return characterWeight;
    }

    public void setCharacterWeight(double characterWeight) {
        this.characterWeight = characterWeight;
    }

    public double getCharacterHeightInCM() {
        return characterHeightInCM;
    }

    public void setCharacterHeightInCM(double characterHeightInCM) {
        this.characterHeightInCM = characterHeightInCM;
    }

    public int getHairColorId() {
        return hairColorId;
    }

    public void setHairColorId(int hairColorId) {
        this.hairColorId = hairColorId;
    }

    public String getCharacterHairStyle() {
        return characterHairStyle;
    }

    public void setCharacterHairStyle(String characterHairStyle) {
        this.characterHairStyle = characterHairStyle;
    }

    public String getCharacterFacialHair() {
        return characterFacialHair;
    }

    public void setCharacterFacialHair(String characterFacialHair) {
        this.characterFacialHair = characterFacialHair;
    }

    public int getEyeColorId() {
        return eyeColorId;
    }

    public void setEyeColorId(int eyeColorId) {
        this.eyeColorId = eyeColorId;
    }

    public int getRaceId() {
        return raceId;
    }

    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    public String getCharacterSkinTone() {
        return characterSkinTone;
    }

    public void setCharacterSkinTone(String characterSkinTone) {
        this.characterSkinTone = characterSkinTone;
    }

    public String getCharacterBodyType() {
        return characterBodyType;
    }

    public void setCharacterBodyType(String characterBodyType) {
        this.characterBodyType = characterBodyType;
    }

    public String getIdentifyingMark() {
        return identifyingMark;
    }

    public void setIdentifyingMark(String identifyingMark) {
        this.identifyingMark = identifyingMark;
    }

    public String getCharacterMannerism() {
        return characterMannerism;
    }

    public void setCharacterMannerism(String characterMannerism) {
        this.characterMannerism = characterMannerism;
    }

    public String getCharacterMotivation() {
        return characterMotivation;
    }

    public void setCharacterMotivation(String characterMotivation) {
        this.characterMotivation = characterMotivation;
    }

    public String getCharacterFlaw() {
        return characterFlaw;
    }

    public void setCharacterFlaw(String characterFlaw) {
        this.characterFlaw = characterFlaw;
    }

    public String getCharacterPrejudice() {
        return characterPrejudice;
    }

    public void setCharacterPrejudice(String characterPrejudice) {
        this.characterPrejudice = characterPrejudice;
    }

    public String getCharacterTalent() {
        return characterTalent;
    }

    public void setCharacterTalent(String characterTalent) {
        this.characterTalent = characterTalent;
    }

    public String getCharacterHobby() {
        return characterHobby;
    }

    public void setCharacterHobby(String characterHobby) {
        this.characterHobby = characterHobby;
    }

    public int getPersonalityTypeId() {
        return personalityTypeId;
    }

    public void setPersonalityTypeId(int personalityTypeId) {
        this.personalityTypeId = personalityTypeId;
    }

    public String getCharacterCondition() {
        return characterCondition;
    }

    public void setCharacterCondition(String characterCondition) {
        this.characterCondition = characterCondition;
    }

    public int getReligionId() {
        return religionId;
    }

    public void setReligionId(int religionId) {
        this.religionId = religionId;
    }

    public String getCharacterOccupation() {
        return characterOccupation;
    }

    public void setCharacterOccupation(String characterOccupation) {
        this.characterOccupation = characterOccupation;
    }

    public String getCharacterFavColor() {
        return characterFavColor;
    }

    public void setCharacterFavColor(String characterFavColor) {
        this.characterFavColor = characterFavColor;
    }

    public String getCharacterFavFood() {
        return characterFavFood;
    }

    public void setCharacterFavFood(String characterFavFood) {
        this.characterFavFood = characterFavFood;
    }

    public String getCharacterFavPossession() {
        return characterFavPossession;
    }

    public void setCharacterFavPossession(String characterFavPossession) {
        this.characterFavPossession = characterFavPossession;
    }

    public String getCharacterFavPosessionDescription() {
        return characterFavPosessionDescription;
    }

    public void setCharacterFavPosessionDescription(String characterFavPosessionDescription) {
        this.characterFavPosessionDescription = characterFavPosessionDescription;
    }

    public int getWeaponId() {
        return weaponId;
    }

    public void setWeaponId(int weaponId) {
        this.weaponId = weaponId;
    }

    public String getCharacterFavAnimal() {
        return characterFavAnimal;
    }

    public void setCharacterFavAnimal(String characterFavAnimal) {
        this.characterFavAnimal = characterFavAnimal;
    }

    public String getCharacterBirthday() {
        return characterBirthday;
    }

    public void setCharacterBirthday(String characterBirthday) {
        this.characterBirthday = characterBirthday;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public int getPlanetId() {
        return planetId;
    }

    public void setPlanetId(int planetId) {
        this.planetId = planetId;
    }

    public String getCharacterEducation() {
        return characterEducation;
    }

    public void setCharacterEducation(String characterEducation) {
        this.characterEducation = characterEducation;
    }

    public String getCharacterBackground() {
        return characterBackground;
    }

    public void setCharacterBackground(String characterBackground) {
        this.characterBackground = characterBackground;
    }

    public String getCharacterPicture() {
        return characterPicture;
    }

    public void setCharacterPicture(String characterPicture) {
        this.characterPicture = characterPicture;
    }

    public String getCharacterNote() {
        return characterNote;
    }

    public void setCharacterNote(String characterNote) {
        this.characterNote = characterNote;
    }

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }
}
