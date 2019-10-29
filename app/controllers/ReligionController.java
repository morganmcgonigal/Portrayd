package controllers;

import models.Religion;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.util.List;

public class ReligionController extends Controller {

    private FormFactory formFactory;
    private JPAApi db;

    @Inject
    public ReligionController (FormFactory formFactory, JPAApi db){
        this.formFactory = formFactory;
        this.db = db;
    }

    @Transactional(readOnly = true)
    public Result getReligion(int religionId){
        TypedQuery<Religion> religionTypedQuery = db.em().createQuery(
                "SELECT r " +
                        "FROM Religion r " +
                        "WHERE religionId = :religionId",
                Religion.class);
        religionTypedQuery.setParameter("religionId", religionId);
        Religion religion = religionTypedQuery.getSingleResult();

        return ok(views.html.ModelView.religion.render(religion));
    }

    @Transactional(readOnly = true)
    public Result getAllReligions(){
        TypedQuery<Religion> religionTypedQuery = db.em().createQuery(
                "SELECT r " +
                        "FROM Religion r " +
                        "ORDER BY religionId",
                Religion.class);
        List<Religion> religions = religionTypedQuery.getResultList();

        return ok(views.html.VIewAll.religions.render(religions));
    }

    @Transactional (readOnly = true)
    public Result getAddReligion(){
        TypedQuery<Religion> religionTypedQuery = db.em().createQuery(
                "SELECT  r " +
                        "FROM Religion r " +
                        "ORDER BY religionId",
                Religion.class);
        List<Religion> religions = religionTypedQuery.getResultList();

        return ok(views.html.Add.addreligion.render(religions));
    }

//    private String religionName;
//    private String religionHistory;
//    private String religionNotes;
//    private String religionPicture;

    @Transactional
    public Result postAddReligion(){
        Religion religion = new Religion();

        DynamicForm form = formFactory.form().bindFromRequest();
        String religionName = form.get("religionName");
        String religionHistory = form.get("religionHistory");
        String religionNotes = form.get("religionNotes");
        String religionPicture = form.get("religionPicture");

        religion.setReligionName(religionName);
        religion.setReligionHistory(religionHistory);
        religion.setReligionNotes(religionNotes);
        religion.setReligionPicture(religionPicture);
        db.em().persist(religion);

        TypedQuery<Religion> religionTypedQuery = db.em().createQuery(
                "SELECT r " +
                        "FROM Religion r " +
                        "ORDER BY religionId",
                Religion.class);
        List<Religion> religions = religionTypedQuery.getResultList();

        return ok(views.html.VIewAll.religions.render(religions));
    }
}
