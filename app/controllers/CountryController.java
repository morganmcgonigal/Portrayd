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

        return ok(views.html.ModelView.country.render(country));
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
                "SELECT co " +
                        "FROM Country co " +
                        "ORDER BY countryId",
                Country.class);
        List<Country> countries = countryTypedQuery.getResultList();

        TypedQuery<Planet> planetTypedQuery = db.em().createQuery(
                "SELECT pl " +
                        "FROM Planet pl " +
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

        String countryPicture = form.get("countryPicture");

        String languageName = form.get("language");
        int languageId = Integer.parseInt(languageName);

        country.setCountryName(countryName);
        country.setPlanetId(planetId);
        country.setCountryPicture(countryPicture);
        country.setLanguageId(languageId);
        db.em().persist(country);

        TypedQuery<Country> countryTypedQuery = db.em().createQuery(
                "SELECT co " +
                        "FROM Country co " +
                        "ORDER BY countryId",
                Country.class);
        List<Country> countries = countryTypedQuery.getResultList();

        return ok(views.html.VIewAll.countries.render(countries));
    }
}
