package controllers;

import models.Planet;
import models.Universe;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.util.List;

public class PlanetController extends Controller {

    private JPAApi db;
    private FormFactory formFactory;

    @Inject
    public PlanetController (FormFactory formFactory, JPAApi db){
        this.formFactory = formFactory;
        this.db = db;
    }

    @Transactional(readOnly = true)
    public Result getPlanet(int planetId){
        TypedQuery<Planet> planetTypedQuery = db.em().createQuery(
                "SELECT p " +
                        "FROM Planet p " +
                        "WHERE planetId = :planetId",
                Planet.class);
        planetTypedQuery.setParameter("planetId", planetId);
        Planet planet = planetTypedQuery.getSingleResult();

        return ok(views.html.ModelView.planet.render(planet));
    }

    @Transactional(readOnly = true)
    public Result getAllPlanets (){
        TypedQuery<Planet> planetTypedQuery = db.em().createQuery(
                "SELECT p " +
                        "FROM Planet p " +
                        "ORDER BY planetId",
                Planet.class);
        List<Planet> planets = planetTypedQuery.getResultList();

        return ok(views.html.VIewAll.planets.render(planets));
    }

    @Transactional(readOnly = true)
    public Result getAddPlanet(){
        TypedQuery<Planet> planetTypedQuery = db.em().createQuery(
                "SELECT p " +
                        "FROM Planet p " +
                        "ORDER BY planetId",
                Planet.class);
        List<Planet> planets = planetTypedQuery.getResultList();

        TypedQuery<Universe> universeTypedQuery = db.em().createQuery(
                "SELECT u " +
                        "FROM Universe u " +
                        "ORDER BY universeId",
                Universe.class);
        List<Universe> universes = universeTypedQuery.getResultList();

        return ok(views.html.Add.addplanet.render(planets, universes));
    }

    @Transactional
    public Result postAddPlanet(){
        Planet planet = new Planet();

        DynamicForm form = formFactory.form().bindFromRequest();
        String planetName = form.get("planetName");
        String universeName = form.get("universe");
        int universeId = Integer.parseInt(universeName);
        String planetPicture = form.get("planetPicture");

        planet.setPlanetName(planetName);
        planet.setUniverseId(universeId);
        planet.setPlanetPicture(planetPicture);
        db.em().persist(planet);

        TypedQuery<Planet> planetTypedQuery = db.em().createQuery(
                "SELECT p " +
                        "FROM Planet p " +
                        "ORDER BY planetId",
                Planet.class);
        List<Planet> planets = planetTypedQuery.getResultList();

        return ok(views.html.VIewAll.planets.render(planets));
    }
}
