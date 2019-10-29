package controllers;

import models.Country;
import models.Race;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import scala.Dynamic;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.util.List;

public class RaceController extends Controller {

    private JPAApi db;
    private FormFactory formFactory;

    @Inject
    public RaceController (FormFactory formFactory, JPAApi db){
        this.formFactory = formFactory;
        this.db = db;
    }

    @Transactional(readOnly = true)
    public Result getRace(int raceId){
        TypedQuery<Race> raceTypedQuery = db.em().createQuery(
                "SELECT r " +
                        "FROM Race r " +
                        "WHERE raceId = :raceId",
                Race.class);
        raceTypedQuery.setParameter("raceId", raceId);
        Race race = raceTypedQuery.getSingleResult();

        return ok(views.html.ModelView.race.render(race));
    }

    @Transactional(readOnly = true)
    public Result getAllRaces(){
        TypedQuery<Race> raceTypedQuery = db.em().createQuery(
                "SELECT r " +
                        "FROM Race r " +
                        "ORDER BY raceId",
                Race.class);
        List<Race> races = raceTypedQuery.getResultList();

        return ok(views.html.VIewAll.races.render(races));
    }

    @Transactional(readOnly = true)
    public Result getAddRace(){
        TypedQuery<Race> raceTypedQuery = db.em().createQuery(
                "SELECT r " +
                        "FROM Race r " +
                        "ORDER BY raceId",
                Race.class);
        List<Race> races = raceTypedQuery.getResultList();

        TypedQuery<Country> countryTypedQuery = db.em().createQuery(
                "SELECT c " +
                        "FROM Country c " +
                        "ORDER BY countryId",
                Country.class);
        List<Country> countries = countryTypedQuery.getResultList();

        return ok(views.html.Add.addrace.render(races, countries));
    }

    @Transactional
    public Result postAddRace(){
        Race race = new Race();

        DynamicForm form = formFactory.form().bindFromRequest();
        String raceName = form.get("raceName");
        String countryName = form.get("country");
        int countryId = Integer.parseInt(countryName);
        String racePicture = form.get("racePicture");

        race.setRaceName(raceName);
        race.setCountryId(countryId);
        race.setRacePicture(racePicture);
        db.em().persist(race);

        TypedQuery<Race> raceTypedQuery = db.em().createQuery(
                "SELECT r " +
                        "FROM Race r " +
                        "ORDER BY raceId",
                Race.class);
        List<Race> races = raceTypedQuery.getResultList();

        return ok(views.html.VIewAll.races.render(races));
    }
}
