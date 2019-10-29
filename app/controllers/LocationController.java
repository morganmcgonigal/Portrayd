package controllers;

import models.City;
import models.Location;
import models.LocationType;
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

public class LocationController extends Controller {

    private JPAApi db;
    private FormFactory formFactory;

    @Inject
    public LocationController (FormFactory formFactory, JPAApi db){
        this.formFactory = formFactory;
        this.db = db;
    }

    @Transactional
    public Result getLocation(int locationId){
        TypedQuery<Location> locationTypedQuery = db.em().createQuery(
                "SELECT l " +
                        "FROM Location l " +
                        "WHERE locationId = :locationId",
                Location.class);
        locationTypedQuery.setParameter("locationId", locationId);
        Location location = locationTypedQuery.getSingleResult();

        return ok(views.html.ModelView.location.render(location));
    }

    @Transactional(readOnly = true)
    public Result getAllLocations(){
        TypedQuery<Location> locationTypedQuery = db.em().createQuery(
                "SELECT l " +
                        "FROM Location l " +
                        "ORDER BY locationId",
                Location.class);
        List<Location> locations = locationTypedQuery.getResultList();

        return ok(views.html.VIewAll.locations.render(locations));
    }

    @Transactional(readOnly = true)
    public Result getAddLocation(){
        TypedQuery<Location> locationTypedQuery = db.em().createQuery(
                "SELECT lo " +
                        "FROM Location lo " +
                        "ORDER BY locationId",
                Location.class);
        List<Location> locations = locationTypedQuery.getResultList();

        TypedQuery<LocationType> locationTypeTypedQuery = db.em().createQuery(
                "SELECT lt " +
                        "FROM LocationType lt " +
                        "ORDER BY locationTypeId",
                LocationType.class);
        List<LocationType> locationTypes = locationTypeTypedQuery.getResultList();

        TypedQuery<Universe> universeTypedQuery = db.em().createQuery(
                "SELECT u " +
                        "FROM Universe u " +
                        "ORDER BY universeId",
                Universe.class);
        List<Universe> universes = universeTypedQuery.getResultList();

        return ok(views.html.Add.addlocation.render(locations, universes, locationTypes));
    }

    @Transactional
    public Result postAddLocation(){
        Location location = new Location();

        DynamicForm form = formFactory.form().bindFromRequest();

        String locationName = form.get("locationName");
        String locationTypeName = form.get("locationType");
        int locationTypeId = Integer.parseInt(locationTypeName);
        String locationHistory = form.get("locationHistory");
        String universeName = form.get("universe");
        int universeId = Integer.parseInt(universeName);
        String locationPicture = form.get("locationPicture");

        location.setLocationName(locationName);
        location.setLocationTypeId(locationTypeId);
        location.setLocationHistory(locationHistory);
        location.setUniverseId(universeId);
        location.setLocationPicture(locationPicture);

        db.em().persist(location);

        TypedQuery<Location> locationTypedQuery = db.em().createQuery(
                "SELECT lo " +
                        "FROM Location lo " +
                        "ORDER BY locationId",
                Location.class);
        List<Location> locations = locationTypedQuery.getResultList();

        return ok(views.html.VIewAll.locations.render(locations));

    }
}
