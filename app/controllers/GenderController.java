package controllers;

import models.CharacterGender;
import models.CharacterRole;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.util.List;

public class GenderController extends Controller {

    private JPAApi db;
    private FormFactory formFactory;

    @Inject
    public GenderController (FormFactory formFactory, JPAApi db){
        this.formFactory = formFactory;
        this.db = db;
    }

    @Transactional(readOnly = true)
    public Result getGender(int genderId){
        TypedQuery<CharacterGender> characterGenderTypedQuery = db.em().createQuery(
                "SELECT g " +
                        "FROM CharacterGender g " +
                        "WHERE genderId = :genderId",
                CharacterGender.class);
        characterGenderTypedQuery.setParameter("genderId", genderId);
        CharacterGender gender = characterGenderTypedQuery.getSingleResult();

        return ok(views.html.ModelView.gender.render(gender));
    }

    @Transactional(readOnly = true)
    public Result getAllGenders(){
        TypedQuery<CharacterGender> genderTypedQuery = db.em().createQuery(
                "SELECT g " +
                        "FROM CharacterGender g " +
                        "ORDER BY genderId",
                CharacterGender.class);
        List<CharacterGender> genders = genderTypedQuery.getResultList();

        return ok(views.html.VIewAll.genders.render(genders));
    }

    @Transactional(readOnly = true)
    public Result getAddGender(){
        TypedQuery<CharacterGender> genderTypedQuery = db.em().createQuery(
                "SELECT g " +
                        "FROM CharacterGender g " +
                        "ORDER BY genderId",
                CharacterGender.class);
        List<CharacterGender> genders = genderTypedQuery.getResultList();

        return ok(views.html.Add.addgender.render(genders));
    }

    @Transactional
    public Result postAddGender(){
        CharacterGender gender = new CharacterGender();

        DynamicForm form = formFactory.form().bindFromRequest();
        String genderName = form.get("genderName");
        String genderDescription = form.get("genderDescription");

        gender.setGenderName(genderName);
        gender.setGenderDescription(genderDescription);
        db.em().persist(gender);

        TypedQuery<CharacterGender> genderTypedQuery = db.em().createQuery(
                "SELECT g " +
                        "FROM CharacterGender g " +
                        "ORDER BY genderId",
                CharacterGender.class);
        List<CharacterGender> genders = genderTypedQuery.getResultList();

        return ok(views.html.VIewAll.genders.render(genders));
    }
}
