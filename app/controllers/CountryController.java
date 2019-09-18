package controllers;

import models.Country;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import javax.persistence.TypedQuery;

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

        return ok(views.html.country.render(country));
    }
}
