package controllers;

import javafx.print.PageLayout;
import models.*;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.util.List;

public class NewCharacterController extends Controller {

    private FormFactory formFactory;
    private JPAApi db;

    @Inject
    public NewCharacterController (FormFactory formFactory, JPAApi db){
        this.formFactory = formFactory;
        this.db = db;
    }

    @Transactional(readOnly = true)
    public Result getCharacter(int characterId){
        TypedQuery<NewCharacter> newCharacterTypedQuery = db.em().createQuery(
                "SELECT nc " +
                        "FROM NewCharacter nc " +
                        "WHERE characterId = :characterId",
                NewCharacter.class);
        newCharacterTypedQuery.setParameter("characterId", characterId);
        NewCharacter character = newCharacterTypedQuery.getSingleResult();

        TypedQuery<CharacterRole> characterRoleTypedQuery = db.em().createQuery(
                "SELECT cr " +
                        "FROM CharacterRole cr " +
                        "WHERE characterRoleId = :characterRoleId",
                CharacterRole.class);
        characterRoleTypedQuery.setParameter("characterRoleId", character.getCharacterRoleId());
        CharacterRole role = characterRoleTypedQuery.getSingleResult();

        TypedQuery<CharacterGender> characterGenderTypedQuery = db.em().createQuery(
                "SELECT cg " +
                        "FROM CharacterGender cg " +
                        "WHERE genderId = :genderId",
                CharacterGender.class);
        characterGenderTypedQuery.setParameter("genderId", character.getGenderId());
        CharacterGender gender = characterGenderTypedQuery.getSingleResult();

        TypedQuery<CharacterHairColor> characterHairColorTypedQuery = db.em().createQuery(
                "SELECT hc " +
                        "FROM CharacterHairColor hc " +
                        "WHERE hairColorId = :hairColorId",
                CharacterHairColor.class);
        characterHairColorTypedQuery.setParameter("hairColorId", character.getHairColorId());
        CharacterHairColor hairColor = characterHairColorTypedQuery.getSingleResult();

        TypedQuery<CharacterEyeColor> characterEyeColorTypedQuery = db.em().createQuery(
                "SELECT ec " +
                        "FROM CharacterEyeColor ec " +
                        "WHERE eyeColorId = :eyeColorId",
                CharacterEyeColor.class);
        characterEyeColorTypedQuery.setParameter("eyeColorId", character.getEyeColorId());
        CharacterEyeColor eyeColor = characterEyeColorTypedQuery.getSingleResult();

        TypedQuery<Race> raceTypedQuery = db.em().createQuery(
                "SELECT r " +
                        "FROM Race r " +
                        "WHERE raceId = :raceId",
        Race.class);
        raceTypedQuery.setParameter("raceId", character.getRaceId());
        Race race = raceTypedQuery.getSingleResult();

        TypedQuery<CharacterPersonalityType> personalityTypeTypedQuery = db.em().createQuery(
                "SELECT pt " +
                        "FROM CharacterPersonalityType pt " +
                        "WHERE personalityTypeId = :personalityTypeId",
                CharacterPersonalityType.class);
        personalityTypeTypedQuery.setParameter("personalityTypeId", character.getPersonalityTypeId());
        CharacterPersonalityType personalityType = personalityTypeTypedQuery.getSingleResult();

        TypedQuery<Religion> religionTypedQuery = db.em().createQuery(
                "SELECT r " +
                        "FROM Religion r " +
                        "WHERE religionId = :religionId",
                Religion.class);
        religionTypedQuery.setParameter("religionId", character.getReligionId());
        Religion religion = religionTypedQuery.getSingleResult();

        TypedQuery<Weapon> weaponTypedQuery = db.em().createQuery(
                "SELECT w " +
                        "FROM Weapon w " +
                        "WHERE weaponId = :weaponId",
                Weapon.class);
        weaponTypedQuery.setParameter("weaponId", character.getWeaponId());
        Weapon weapon = weaponTypedQuery.getSingleResult();

        TypedQuery<Location> locationTypedQuery = db.em().createQuery(
                "SELECT l " +
                        "FROM Location l " +
                        "WHERE locationId = :locationId",
                Location.class);
        locationTypedQuery.setParameter("locationId", character.getLocationId());
        Location location = locationTypedQuery.getSingleResult();

        TypedQuery<City> cityTypedQuery = db.em().createQuery(
                "SELECT c " +
                        "FROM City c " +
                        "WHERE cityId = :cityId",
                City.class);
        cityTypedQuery.setParameter("cityId", character.getCityId());
        City city = cityTypedQuery.getSingleResult();

        TypedQuery<Country> countryTypedQuery = db.em().createQuery(
                "SELECT c " +
                        "FROM Country c " +
                        "WHERE countryId = :countryId",
                Country.class);
        countryTypedQuery.setParameter("countryId", character.getCountryId());
        Country country = countryTypedQuery.getSingleResult();

        TypedQuery<Planet> planetTypedQuery = db.em().createQuery(
                "SELECT p " +
                        "FROM Planet p " +
                        "WHERE planetId = :planetId",
                Planet.class);
        planetTypedQuery.setParameter("planetId", character.getPlanetId());
        Planet planet = planetTypedQuery.getSingleResult();

        TypedQuery<Language> languageTypedQuery = db.em().createQuery(
                "SELECT l " +
                        "FROM Language l " +
                        "WHERE languageId = :languageId",
                Language.class);
        languageTypedQuery.setParameter("languageId", character.getLanguageId());
        Language language = languageTypedQuery.getSingleResult();

        return ok(views.html.ModelView.character.render(character, role, gender, hairColor, eyeColor, race, personalityType,
                religion, weapon, location, city, country, planet, language));
    }

    @Transactional(readOnly = true)
    public Result getAllCharacters(){
        TypedQuery<NewCharacter> characterTypedQuery = db.em().createQuery(
                "SELECT c " +
                        "FROM NewCharacter c " +
                        "ORDER BY characterId",
                NewCharacter.class);
        List<NewCharacter> characters = characterTypedQuery.getResultList();

        return ok(views.html.VIewAll.characters.render(characters));
    }

    @Transactional(readOnly = true)
    public Result getAddCharacter(){
        TypedQuery<NewCharacter> characterTypedQuery = db.em().createQuery(
                "SELECT nc " +
                        "FROM NewCharacter nc " +
                        "ORDER BY characterId",
                NewCharacter.class);
        List<NewCharacter> characters = characterTypedQuery.getResultList();

        TypedQuery<CharacterGender> genderTypedQuery = db.em().createQuery(
                "SELECT cg " +
                        "FROM CharacterGender cg " +
                        "ORDER BY genderId",
                CharacterGender.class);
        List<CharacterGender> genders = genderTypedQuery.getResultList();

        TypedQuery<Race> raceTypedQuery = db.em().createQuery(
                "SELECT r " +
                        "FROM Race r " +
                        "ORDER BY raceId",
                Race.class);
        List<Race> races = raceTypedQuery.getResultList();

        TypedQuery<CharacterPersonalityType> personalityTypedQuery = db.em().createQuery(
                "SELECT pt " +
                        "FROM CharacterPersonalityType pt " +
                        "ORDER BY personalityTypeId",
                CharacterPersonalityType.class);
        List<CharacterPersonalityType> personalityTypes = personalityTypedQuery.getResultList();

        TypedQuery<Religion> religionTypedQuery = db.em().createQuery(
                "SELECT r " +
                        "FROM Religion r " +
                        "ORDER BY religionId",
                Religion.class);
        List<Religion> religions = religionTypedQuery.getResultList();

        TypedQuery<CharacterEyeColor> eyeColorTypedQuery = db.em().createQuery(
                "SELECT ec " +
                        "FROM CharacterEyeColor ec " +
                        "ORDER BY eyeColorId",
                CharacterEyeColor.class);
        List<CharacterEyeColor> eyeColors = eyeColorTypedQuery.getResultList();

        TypedQuery<CharacterHairColor> hairColorTypedQuery = db.em().createQuery(
                "SELECT hc " +
                        "FROM CharacterHairColor hc " +
                        "ORDER BY hairColorId",
                CharacterHairColor.class);
        List<CharacterHairColor> hairColors = hairColorTypedQuery.getResultList();

        TypedQuery<CharacterRole> roleTypedQuery = db.em().createQuery(
                "SELECT cr " +
                        "FROM CharacterRole cr " +
                        "ORDER BY characterRoleId",
                CharacterRole.class);
        List<CharacterRole> roles = roleTypedQuery.getResultList();

        TypedQuery<Weapon> weaponTypedQuery = db.em().createQuery(
                "SELECT w " +
                        "FROM Weapon w " +
                        "ORDER BY weaponId",
                Weapon.class);
        List<Weapon> weapons = weaponTypedQuery.getResultList();

        TypedQuery<Location> locationTypedQuery = db.em().createQuery(
                "SELECT l " +
                        "FROM Location l " +
                        "ORDER BY locationId",
                Location.class);
        List<Location> locations = locationTypedQuery.getResultList();

        TypedQuery<City> cityTypedQuery = db.em().createQuery(
                "SELECT c " +
                        "FROM City c " +
                        "ORDER BY cityId",
                City.class);
        List<City> cities = cityTypedQuery.getResultList();

        TypedQuery<Country> countryTypedQuery = db.em().createQuery(
                "SELECT co " +
                        "FROM Country co " +
                        "ORDER BY countryId",
                Country.class);
        List<Country> countries = countryTypedQuery.getResultList();

        TypedQuery<Planet> planetTypedQuery = db.em().createQuery(
                "SELECT p " +
                        "FROM Planet p " +
                        "ORDER BY planetId",
                Planet.class);
        List<Planet> planets = planetTypedQuery.getResultList();

        TypedQuery<Language> languageTypedQuery = db.em().createQuery(
                "SELECT l " +
                        "FROM Language l " +
                        "ORDER BY languageId",
                Language.class);
        List<Language> languages = languageTypedQuery.getResultList();

        return ok(views.html.Add.addcharacter.render(characters, genders, races,
                personalityTypes, religions, eyeColors, hairColors,
                roles, weapons, locations, cities, countries, planets,
                languages));
    }

    @Transactional
    public Result postAddCharacter(){
        NewCharacter newCharacter = new NewCharacter();

        DynamicForm form = formFactory.form().bindFromRequest();
        String characterName = form.get("characterName");
        String roleName = form.get("role");
        int roleId = Integer.parseInt(roleName);
        String characterAlias = form.get("characterAlias");
        String genderName = form.get("gender");
        int genderId = Integer.parseInt(genderName);
        String characterAgeString = form.get("characterAge");
        int characterAge = Integer.parseInt(characterAgeString);
        String characterWeightString = form.get("characterWeight");
        double characterWeight = Double.parseDouble(characterWeightString);
        String characterHeightString = form.get("characterHeight");
        double characterHeight = Double.parseDouble(characterHeightString);
        String hairColorString = form.get("hairColor");
        int hairColor = Integer.parseInt(hairColorString);
        String characterHairStyle = form.get("hairStyle");
        String facialHair = form.get("facialHair");
        String eyeColorString = form.get("eyeColor");
        int eyeColor = Integer.parseInt(eyeColorString);
        String raceString = form.get("race");
        int race = Integer.parseInt(raceString);
        String characterSkinTone = form.get("skinTone");
        String characterBodyType = form.get("bodyType");
        String identifyingMark = form.get("identifyingMark");
        String characterMannerism = form.get("mannerism");
        String characterMotivation = form.get("motivation");
        String characterFlaw = form.get("flaw");
        String characterPrejudice = form.get("prejudice");
        String characterTalent = form.get("talent");
        String characterHobby = form.get("hobby");
        String personalityString = form.get("personalityType");
        int personalityTypeId = Integer.parseInt(personalityString);
        String characterCondition = form.get("condition");
        String religionString = form.get("religion");
        int religion = Integer.parseInt(religionString);
        String characterOccupation = form.get("occupation");
        String characterFavColor = form.get("favColor");
        String characterFavFood = form.get("favFood");
        String characterFavPossession = form.get("favPossession");
        String characterFavPossessionDescription = form.get("favPossessionDescription");
        String weaponString = form.get("weapon");
        int weapon = Integer.parseInt(weaponString);
        String characterFavAnimal = form.get("favAnimal");
        String characterBirthday = form.get("birthday");
        String locationString = form.get("location");
        int location = Integer.parseInt(locationString);
        String cityString = form.get("city");
        int city = Integer.parseInt(cityString);
        String countryString = form.get("country");
        int country = Integer.parseInt(countryString);
        String planetString = form.get("planet");
        int planet = Integer.parseInt(planetString);
        String characterEducation = form.get("education");
        String characterBackground = form.get("background");
        String picture = form.get("characterPicture");
        String note = form.get("note");
        String languageString = form.get("language");
        int language = Integer.parseInt(languageString);

        newCharacter.setCharacterName(characterName);
        newCharacter.setCharacterRoleId(roleId);
        newCharacter.setCharacterAlias(characterAlias);
        newCharacter.setGenderId(genderId);
        newCharacter.setCharacterAge(characterAge);
        newCharacter.setCharacterWeight(characterWeight);
        newCharacter.setCharacterHeightInCM(characterHeight);
        newCharacter.setHairColorId(hairColor);
        newCharacter.setCharacterHairStyle(characterHairStyle);
        newCharacter.setCharacterFacialHair(facialHair);
        newCharacter.setEyeColorId(eyeColor);
        newCharacter.setRaceId(race);
        newCharacter.setCharacterSkinTone(characterSkinTone);
        newCharacter.setCharacterBodyType(characterBodyType);
        newCharacter.setIdentifyingMark(identifyingMark);
        newCharacter.setCharacterMannerism(characterMannerism);
        newCharacter.setCharacterMotivation(characterMotivation);
        newCharacter.setCharacterFlaw(characterFlaw);
        newCharacter.setCharacterPrejudice(characterPrejudice);
        newCharacter.setCharacterTalent(characterTalent);
        newCharacter.setCharacterHobby(characterHobby);
        newCharacter.setPersonalityTypeId(personalityTypeId);
        newCharacter.setCharacterCondition(characterCondition);
        newCharacter.setReligionId(religion);
        newCharacter.setCharacterOccupation(characterOccupation);
        newCharacter.setCharacterFavColor(characterFavColor);
        newCharacter.setCharacterFavFood(characterFavFood);
        newCharacter.setCharacterFavPossession();
        newCharacter.setCharacterFavPossessionDescription(characterFavPossessionDescription);
        newCharacter.setWeaponId(weapon);
        newCharacter.setCharacterFavAnimal(characterFavAnimal);
        newCharacter.setCharacterBirthday(characterBirthday);
        newCharacter.setLocationId(location);
        newCharacter.setCityId(city);
        newCharacter.setCountryId(country);
        newCharacter.setPlanetId(planet);
        newCharacter.setCharacterEducation(characterEducation);
        newCharacter.setCharacterBackground(characterBackground);
        newCharacter.setCharacterPicture(picture);
        newCharacter.setCharacterNote(note);
        newCharacter.setLanguageId(language);
        db.em().persist(newCharacter);


        TypedQuery<NewCharacter> characterTypedQuery = db.em().createQuery(
                "SELECT nc " +
                        "FROM NewCharacter nc " +
                        "ORDER BY characterId",
                NewCharacter.class);
        List<NewCharacter> characters = characterTypedQuery.getResultList();

        TypedQuery<CharacterGender> genderTypedQuery = db.em().createQuery(
                "SELECT cg " +
                        "FROM CharacterGender cg " +
                        "ORDER BY genderId",
                CharacterGender.class);
        List<CharacterGender> genders = genderTypedQuery.getResultList();

        TypedQuery<Race> raceTypedQuery = db.em().createQuery(
                "SELECT r " +
                        "FROM Race r " +
                        "ORDER BY raceId",
                Race.class);
        List<Race> races = raceTypedQuery.getResultList();

        TypedQuery<CharacterPersonalityType> personalityTypedQuery = db.em().createQuery(
                "SELECT pt " +
                        "FROM CharacterPersonalityType pt " +
                        "ORDER BY personalityTypeId",
                CharacterPersonalityType.class);
        List<CharacterPersonalityType> personalityTypes = personalityTypedQuery.getResultList();

        TypedQuery<Religion> religionTypedQuery = db.em().createQuery(
                "SELECT r " +
                        "FROM Religion r " +
                        "ORDER BY religionId",
                Religion.class);
        List<Religion> religions = religionTypedQuery.getResultList();

        TypedQuery<CharacterEyeColor> eyeColorTypedQuery = db.em().createQuery(
                "SELECT ec " +
                        "FROM CharacterEyeColor ec " +
                        "ORDER BY eyeColorId",
                CharacterEyeColor.class);
        List<CharacterEyeColor> eyeColors = eyeColorTypedQuery.getResultList();

        TypedQuery<CharacterHairColor> hairColorTypedQuery = db.em().createQuery(
                "SELECT hc " +
                        "FROM CharacterHairColor hc " +
                        "ORDER BY hairColorId",
                CharacterHairColor.class);
        List<CharacterHairColor> hairColors = hairColorTypedQuery.getResultList();

        TypedQuery<CharacterRole> roleTypedQuery = db.em().createQuery(
                "SELECT cr " +
                        "FROM CharacterRole cr " +
                        "ORDER BY characterRoleId",
                CharacterRole.class);
        List<CharacterRole> roles = roleTypedQuery.getResultList();

        TypedQuery<Weapon> weaponTypedQuery = db.em().createQuery(
                "SELECT w " +
                        "FROM Weapon w " +
                        "ORDER BY weaponId",
                Weapon.class);
        List<Weapon> weapons = weaponTypedQuery.getResultList();

        TypedQuery<Location> locationTypedQuery = db.em().createQuery(
                "SELECT l " +
                        "FROM Location l " +
                        "ORDER BY locationId",
                Location.class);
        List<Location> locations = locationTypedQuery.getResultList();

        TypedQuery<City> cityTypedQuery = db.em().createQuery(
                "SELECT c " +
                        "FROM City c " +
                        "ORDER BY cityId",
                City.class);
        List<City> cities = cityTypedQuery.getResultList();

        TypedQuery<Country> countryTypedQuery = db.em().createQuery(
                "SELECT co " +
                        "FROM Country co " +
                        "ORDER BY countryId",
                Country.class);
        List<Country> countries = countryTypedQuery.getResultList();

        TypedQuery<Planet> planetTypedQuery = db.em().createQuery(
                "SELECT p " +
                        "FROM Planet p " +
                        "ORDER BY planetId",
                Planet.class);
        List<Planet> planets = planetTypedQuery.getResultList();

        TypedQuery<Language> languageTypedQuery = db.em().createQuery(
                "SELECT l " +
                        "FROM Language l " +
                        "ORDER BY languageId",
                Language.class);
        List<Language> languages = languageTypedQuery.getResultList();

        return ok(views.html.VIewAll.characters.render(characters));
    }
}
