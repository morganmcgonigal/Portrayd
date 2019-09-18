package controllers;

import models.City;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import javax.persistence.TypedQuery;

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

        return ok(views.html.city.render(city));
    }


}
