package controllers;

import com.sun.org.apache.xpath.internal.operations.Bool;
import models.City;
import models.Country;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.util.List;

public class CityController extends Controller {

    private FormFactory formFactory;
    private JPAApi db;

    @Inject
    public CityController (FormFactory formFactory, JPAApi db){
        this.formFactory = formFactory;
        this.db = db;
    }

    @Transactional(readOnly = true)
    public Result getCity(int cityId){
        TypedQuery<City> cityTypedQuery = db.em().createQuery(
                "SELECT c " +
                        "FROM City c " +
                        "WHERE cityId = :cityId",
                City.class);
        cityTypedQuery.setParameter("cityId", cityId);
        City city = cityTypedQuery.getSingleResult();

        TypedQuery<Country> countryTypedQuery = db.em().createQuery(
                "SELECT c " +
                        "FROM Country c " +
                        "WHERE countryId = :countryId",
                Country.class);
        countryTypedQuery.setParameter("countryId", city.getCountryId());
        Country country = countryTypedQuery.getSingleResult();

        return ok(views.html.ModelView.city.render(city, country));
    }

    @Transactional(readOnly = true)
    public Result getCityEdit(int cityId){
        TypedQuery<City> cityTypedQuery = db.em().createQuery(
                "SELECT c " +
                        "FROM City c " +
                        "WHERE cityId = :cityId",
                City.class);
        cityTypedQuery.setParameter("cityId", cityId);
        City city = cityTypedQuery.getSingleResult();



        TypedQuery<Country> countryTypedQuery = db.em().createQuery(
                "SELECT co " +
                        "FROM Country co " +
                        "ORDER BY countryId",
                Country.class);
        List<Country> countries = countryTypedQuery.getResultList();

        return ok(views.html.Edit.editcity.render(city, countries));
    }

    @Transactional
    public Result postCityEdit(int cityId){
        TypedQuery<City> cityTypedQuery = db.em().createQuery(
                "SELECT c " +
                        "FROM City c " +
                        "WHERE cityId = :cityId",
                City.class);
        cityTypedQuery.setParameter("cityId", cityId);
        City city = cityTypedQuery.getSingleResult();

        DynamicForm form = formFactory.form().bindFromRequest();
        String cityName = form.get("cityName");
        String countryString = form.get("country");
        int countryId = Integer.parseInt(countryString);
        String capitalCity = form.get("isCapitalCity");
        boolean isCapitalCity = Boolean.parseBoolean(capitalCity);
        String cityPicture = form.get("cityPicture");

        city.setCityName(cityName);
        city.setCountryId(countryId);
        city.setCapitalCity(isCapitalCity);
        city.setCityPicture(cityPicture);
        db.em().persist(city);

        TypedQuery<Country> countryTypedQuery = db.em().createQuery(
                "SELECT c " +
                        "FROM Country c " +
                        "WHERE countryId = :countryId",
                Country.class);
        countryTypedQuery.setParameter("countryId", city.getCountryId());
        Country country = countryTypedQuery.getSingleResult();

        return ok(views.html.ModelView.city.render(city, country));
    }

    @Transactional(readOnly = true)
    public Result getAllCities(){
        TypedQuery<City> cityTypedQuery = db.em().createQuery(
                "SELECT c " +
                        "FROM City c " +
                        "ORDER BY cityId",
                City.class);
        List<City> cities = cityTypedQuery.getResultList();

        return ok(views.html.VIewAll.cities.render(cities));

    }

    @Transactional(readOnly = true)
    public Result getAddCity(){
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

        return ok(views.html.Add.addcity.render(cities, countries));
    }

    @Transactional
    public Result postAddCity(){
        City city = new City();

        DynamicForm form = formFactory.form().bindFromRequest();

        String cityName = form.get("cityName");
        String countryString = form.get("country");
        int countryId = Integer.parseInt(countryString);
        String capitalCity = form.get("isCapitalCity");
        boolean isCapitalCity = Boolean.parseBoolean(capitalCity);
        String cityPicture = form.get("cityPicture");

        city.setCityName(cityName);
        city.setCountryId(countryId);
        city.setCapitalCity(isCapitalCity);
        city.setCityPicture(cityPicture);
        db.em().persist(city);

        TypedQuery<City> cityTypedQuery = db.em().createQuery(
                "SELECT c " +
                        "FROM City c " +
                        "ORDER BY cityId",
                City.class);
        List<City> cities = cityTypedQuery.getResultList();

        return ok(views.html.VIewAll.cities.render(cities));

    }


}
