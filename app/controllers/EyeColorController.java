package controllers;

import models.CharacterEyeColor;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.ModelView.eyecolor;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.util.List;

public class EyeColorController extends Controller {

    private FormFactory formFactory;
    private JPAApi db;

    @Inject
    public EyeColorController (FormFactory formFactory, JPAApi db){
        this.formFactory = formFactory;
        this.db = db;
    }

    @Transactional(readOnly = true)
    public Result getEyeColor(int eyeColorId){
        TypedQuery<CharacterEyeColor> eyeColorTypedQuery = db.em().createQuery(
                "SELECT ec " +
                        "FROM CharacterEyeColor ec " +
                        "WHERE eyeColorId = :eyeColorId",
                CharacterEyeColor.class);
        eyeColorTypedQuery.setParameter("eyeColorId", eyeColorId);
        CharacterEyeColor eyeColor = eyeColorTypedQuery.getSingleResult();

        return ok(views.html.ModelView.eyecolor.render(eyeColor));
    }

    @Transactional(readOnly = true)
    public Result getAllEyeColors(){
        TypedQuery<CharacterEyeColor> eyeColorTypedQuery = db.em().createQuery(
                "SELECT ec " +
                        "FROM CharacterEyeColor ec " +
                        "ORDER BY eyeColorId",
                CharacterEyeColor.class);
        List<CharacterEyeColor> eyeColors = eyeColorTypedQuery.getResultList();

        return ok(views.html.VIewAll.eyecolors.render(eyeColors));
    }

    @Transactional(readOnly = true)
    public Result getAddEyeColor(){
        TypedQuery<CharacterEyeColor> eyeColorTypedQuery = db.em().createQuery(
                "SELECT ec " +
                        "FROM CharacterEyeColor ec " +
                        "ORDER BY eyeColorId",
                CharacterEyeColor.class);
        List<CharacterEyeColor> eyeColors = eyeColorTypedQuery.getResultList();

        return ok(views.html.Add.addeyecolor.render(eyeColors));
    }

    @Transactional
    public Result postAddEyeColor(){
        CharacterEyeColor eyeColor = new CharacterEyeColor();

        DynamicForm form = formFactory.form().bindFromRequest();

        String eyeColorName = form.get("eyeColorName");
        String eyeColorDescription = form.get("eyeColorDescription");

        eyeColor.setEyeColorName(eyeColorName);
        eyeColor.setEyeColorDescription(eyeColorDescription);
        db.em().persist(eyeColor);

        TypedQuery<CharacterEyeColor> eyeColorTypedQuery = db.em().createQuery(
                "SELECT ec " +
                        "FROM CharacterEyeColor ec " +
                        "ORDER BY eyeColorId",
                CharacterEyeColor.class);
        List<CharacterEyeColor> eyeColors = eyeColorTypedQuery.getResultList();

        return ok(views.html.VIewAll.eyecolors.render(eyeColors));
    }

    @Transactional(readOnly = true)
    public Result getEyeColorEdit(int eyeColorId){
        TypedQuery<CharacterEyeColor> eyeColorTypedQuery = db.em().createQuery(
                "SELECT ec " +
                        "FROM CharacterEyeColor ec " +
                        "WHERE eyeColorId = :eyeColorId",
                CharacterEyeColor.class);
        eyeColorTypedQuery.setParameter("eyeColorId", eyeColorId);
        CharacterEyeColor eyeColor = eyeColorTypedQuery.getSingleResult();

        return ok(views.html.Edit.editeyecolor.render(eyeColor));
    }

    @Transactional
    public Result postEyeColorEdit(int eyeColorId){
        TypedQuery<CharacterEyeColor> eyeColorTypedQuery = db.em().createQuery(
                "SELECT ec " +
                        "FROM CharacterEyeColor ec " +
                        "WHERE eyeColorId = :eyeColorId",
                CharacterEyeColor.class);
        eyeColorTypedQuery.setParameter("eyeColorId", eyeColorId);
        CharacterEyeColor eyeColor = eyeColorTypedQuery.getSingleResult();

        DynamicForm form = formFactory.form().bindFromRequest();
        String eyeColorName = form.get("eyeColorName");
        String eyeColorDescription = form.get("description");

        eyeColor.setEyeColorName(eyeColorName);
        eyeColor.setEyeColorDescription(eyeColorDescription);
        db.em().persist(eyeColor);

        return ok(views.html.ModelView.eyecolor.render(eyeColor));
    }
}
