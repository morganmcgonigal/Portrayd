package controllers;

import models.Country;
import models.Language;
import models.Planet;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import scala.Proxy;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.util.List;

public class CountryController extends Controller {

    private JPAApi db;
    private FormFactory formFactory;

    @Inject
    public CountryController(FormFactory formFactory, JPAApi db){
        this.db = db;
        this.formFactory = formFactory;
    }

    @Transactional(readOnly = true)
    public Result getCountry(int countryId){
        TypedQuery<Country> countryTypedQuery = db.em().createQuery(
                "SELECT c " +
                        "FROM Country c " +
                        "WHERE countryId = :countryId",
                Country.class);
        countryTypedQuery.setParameter("countryId", countryId);
        Country country = countryTypedQuery.getSingleResult();

        TypedQuery<Planet> planetTypedQuery = db.em().createQuery(
                "SELECT p " +
                        "FROM Planet p " +
                        "WHERE planetId = :planetId",
                Planet.class);
        planetTypedQuery.setParameter("planetId", country.getPlanetId());
        Planet planet = planetTypedQuery.getSingleResult();

        TypedQuery<Language> languageTypedQuery = db.em().createQuery(
                "SELECT l " +
                        "FROM Language l " +
                        "WHERE languageId = :languageId",
                Language.class);
        languageTypedQuery.setParameter("languageId", country.getLanguageId());
        Language language = languageTypedQuery.getSingleResult();

        return ok(views.html.ModelView.country.render(country, planet, language));
    }

    @Transactional(readOnly = true)
    public Result getAllCountries(){
        TypedQuery<Country> countryTypedQuery = db.em().createQuery(
                "SELECT c " +
                        "FROM Country c " +
                        "ORDER BY countryId",
                Country.class);
        List<Country> countries = countryTypedQuery.getResultList();

        return ok (views.html.VIewAll.countries.render(countries));
    }

    @Transactional(readOnly = true)
    public Result getAddCountry(){
        TypedQuery<Country> countryTypedQuery = db.em().createQuery(
                "SELECT c " +
                        "FROM Country c " +
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

        return ok(views.html.Add.addcountry.render(countries, planets, languages));
    }

    @Transactional
    public Result postAddCountry(){
        Country country = new Country();

        DynamicForm form = formFactory.form().bindFromRequest();
        String countryName = form.get("countryName");
        String planetName = form.get("planet");
        int planetId = Integer.parseInt(planetName);
        String languageName = form.get("language");
        int languageId = Integer.parseInt(languageName);
        String picture = form.get("picture");

        country.setCountryName(countryName);
        country.setPlanetId(planetId);
        country.setLanguageId(languageId);
        country.setCountryPicture(picture);
        db.em().persist(country);

        TypedQuery<Planet> planetTypedQuery = db.em().createQuery(
                "SELECT p " +
                        "FROM Planet p " +
                        "WHERE planetId = :planetId",
                Planet.class);
        planetTypedQuery.setParameter("planetId", country.getPlanetId());
        Planet planet = planetTypedQuery.getSingleResult();

        TypedQuery<Language> languageTypedQuery = db.em().createQuery(
                "SELECT l " +
                        "FROM Language l " +
                        "WHERE languageId = :languageId",
                Language.class);
        languageTypedQuery.setParameter("languageId", country.getLanguageId());
        Language language = languageTypedQuery.getSingleResult();


        return ok(views.html.ModelView.country.render(country, planet, language));
    }

    @Transactional(readOnly = true)
    public Result getCountryEdit(int countryId){
        TypedQuery<Country> countryTypedQuery = db.em().createQuery(
                "SELECT c " +
                        "FROM Country c " +
                        "WHERE countryId = :countryId",
                Country.class);
        countryTypedQuery.setParameter("countryId", countryId);
        Country country = countryTypedQuery.getSingleResult();

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

        return ok(views.html.Edit.editcountry.render(country, planets, languages));
    }

    @Transactional
    public Result postCountryEdit(int countryId){
        TypedQuery<Country> countryTypedQuery = db.em().createQuery(
                "SELECT c " +
                        "FROM Country c " +
                        "WHERE countryId = :countryId",
                Country.class);
        countryTypedQuery.setParameter("countryId", countryId);
        Country country = countryTypedQuery.getSingleResult();

        DynamicForm form = formFactory.form().bindFromRequest();
        String countryName = form.get("countryName");
        String planetName = form.get("planet");
        int planetId = Integer.parseInt(planetName);
        String languageName = form.get("language");
        int languageId = Integer.parseInt(languageName);
        String countryPicture = form.get("picture");

        country.setCountryName(countryName);
        country.setPlanetId(planetId);
        country.setLanguageId(languageId);
        country.setCountryPicture(countryPicture);
        db.em().persist(country);

        TypedQuery<Planet> planetTypedQuery = db.em().createQuery(
                "SELECT p " +
                        "FROM Planet p " +
                        "WHERE planetId = :planetId",
                Planet.class);
        planetTypedQuery.setParameter("planetId", country.getPlanetId());
        Planet planet = planetTypedQuery.getSingleResult();

        TypedQuery<Language> languageTypedQuery = db.em().createQuery(
                "SELECT l " +
                        "FROM Language l " +
                        "WHERE languageId = :languageId",
                Language.class);
        languageTypedQuery.setParameter("languageId", country.getLanguageId());
        Language language = languageTypedQuery.getSingleResult();

        return ok(views.html.ModelView.country.render(country, planet, language));
    }
}
