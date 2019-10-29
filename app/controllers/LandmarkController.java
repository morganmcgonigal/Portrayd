package controllers;

import models.Country;
import models.Landmark;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.util.List;

public class LandmarkController extends Controller {

    private FormFactory formFactory;
    private JPAApi db;

    @Inject
    public LandmarkController (FormFactory formFactory, JPAApi db){
        this.formFactory = formFactory;
        this.db = db;
    }

    @Transactional (readOnly = true)
    public Result getLandmark (int landmarkId){
        TypedQuery<Landmark> landmarkTypedQuery = db.em().createQuery(
                "SELECT l " +
                        "FROM Landmark l " +
                        "WHERE landmarkId = :landmarkId",
                Landmark.class);
        landmarkTypedQuery.setParameter("landmarkId", landmarkId);
        Landmark landmark = landmarkTypedQuery.getSingleResult();

        return ok(views.html.ModelView.landmark.render(landmark));
    }

    @Transactional(readOnly = true)
    public Result getAllLandmarks(){
        TypedQuery<Landmark> landmarkTypedQuery = db.em().createQuery(
                "SELECT l " +
                        "FROM Landmark l " +
                        "ORDER BY landmarkId",
                Landmark.class);
        List<Landmark> landmarks = landmarkTypedQuery.getResultList();

        return ok(views.html.VIewAll.landmarks.render(landmarks));
    }

    @Transactional(readOnly = true)
    public Result getAddLandmark(){
        TypedQuery<Landmark> landmarkTypedQuery = db.em().createQuery(
                "SELECT lm " +
                        "FROM Landmark lm " +
                        "ORDER BY landmarkId",
                Landmark.class);
        List<Landmark> landmarks = landmarkTypedQuery.getResultList();

        TypedQuery<Country> countryTypedQuery = db.em().createQuery(
                "SELECT c " +
                        "FROM Country c " +
                        "ORDER BY countryId",
                Country.class);
        List<Country> countries = countryTypedQuery.getResultList();

        return ok(views.html.Add.addlandmark.render(landmarks, countries));
    }

    @Transactional
    public Result postAddLandmark(){
        Landmark landmark = new Landmark();

        DynamicForm form = formFactory.form().bindFromRequest();
        String landmarkName = form.get("landmarkName");
        String countryName = form.get("country");
        int countryId = Integer.parseInt(countryName);
        String landmarkPicture = form.get("landmarkPicture");

        landmark.setLandmarkName(landmarkName);
        landmark.setCountryId(countryId);
        landmark.setLandmarkPicture(landmarkPicture);
        db.em().persist(landmark);

        TypedQuery<Landmark> landmarkTypedQuery = db.em().createQuery(
                "SELECT lm " +
                        "FROM Landmark lm " +
                        "ORDER BY landmarkId",
                Landmark.class);
        List<Landmark> landmarks = landmarkTypedQuery.getResultList();

        return ok(views.html.VIewAll.landmarks.render(landmarks));
    }
}
