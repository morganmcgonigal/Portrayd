package controllers;

import models.LocationType;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.util.List;

public class LocationTypeController extends Controller {

    private FormFactory formFactory;
    private JPAApi db;

    @Inject
    public LocationTypeController (FormFactory formFactory, JPAApi db){
        this.formFactory = formFactory;
        this.db = db;
    }

    @Transactional
    public Result getLocationType(int locationTypeId){
        TypedQuery<LocationType> locationTypeTypedQuery = db.em().createQuery(
                "SELECT lt " +
                        "FROM LocationType lt " +
                        "WHERE locationTypeId = :locationTypeId",
                LocationType.class);
        locationTypeTypedQuery.setParameter("locationTypeId", locationTypeId);
        LocationType locationType = locationTypeTypedQuery.getSingleResult();

        return ok(views.html.ModelView.locationtype.render(locationType));
    }

    @Transactional(readOnly = true)
    public Result getAllLocationTypes (){
        TypedQuery<LocationType> locationTypeTypedQuery = db.em().createQuery(
                "SELECT lt " +
                        "FROM LocationType lt " +
                        "ORDER BY locationTypeId",
                LocationType.class);
        List<LocationType> locationTypes = locationTypeTypedQuery.getResultList();

        return ok(views.html.VIewAll.locationtypes.render(locationTypes));
    }

    @Transactional(readOnly = true)
    public Result getAddLocationType(){
        TypedQuery<LocationType> locationTypeTypedQuery = db.em().createQuery(
                "SELECT lt " +
                        "FROM LocationType lt " +
                        "ORDER BY locationTypeId",
                LocationType.class);
        List<LocationType> locationTypes = locationTypeTypedQuery.getResultList();

        return ok(views.html.Add.addlocationtype.render(locationTypes));
    }

    @Transactional
    public Result postAddLocationType(){
        LocationType locationType = new LocationType();

        DynamicForm form = formFactory.form().bindFromRequest();

        String locationTypeName = form.get("locationTypeName");

        locationType.setLocationTypeName(locationTypeName);

        db.em().persist(locationType);

        TypedQuery<LocationType> locationTypeTypedQuery = db.em().createQuery(
                "SELECT lt " +
                        "FROM LocationType lt " +
                        "ORDER BY locationTypeId",
                LocationType.class);
        List<LocationType> locationTypes = locationTypeTypedQuery.getResultList();

        return ok(views.html.VIewAll.locationtypes.render(locationTypes));
    }
}
