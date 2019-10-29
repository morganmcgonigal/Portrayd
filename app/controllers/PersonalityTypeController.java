package controllers;

import models.CharacterPersonalityType;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.util.List;

public class PersonalityTypeController extends Controller {

    private JPAApi db;
    private FormFactory formFactory;

    @Inject
    public PersonalityTypeController (FormFactory formFactory, JPAApi db){
        this.formFactory = formFactory;
        this.db = db;
    }

    @Transactional(readOnly =  true)
    public Result getPersonality(int personalityTypeId){
        TypedQuery<CharacterPersonalityType> personalityTypeTypedQuery = db.em().createQuery(
                "SELECT p " +
                        "FROM CharacterPersonalityType p " +
                        "WHERE personalityTypeId = :personalityTypeId",
                CharacterPersonalityType.class);
        personalityTypeTypedQuery.setParameter("personalityTypeId", personalityTypeId);
        CharacterPersonalityType personalityType = personalityTypeTypedQuery.getSingleResult();

        return ok(views.html.ModelView.personality.render(personalityType));
    }

    @Transactional(readOnly = true)
    public Result getAllPersonalities() {
        TypedQuery<CharacterPersonalityType> personalityTypeTypedQuery = db.em().createQuery(
                "SELECT p " +
                        "FROM CharacterPersonalityType p " +
                        "ORDER BY personalityTypeId",
                CharacterPersonalityType.class);
        List<CharacterPersonalityType> personalityTypes = personalityTypeTypedQuery.getResultList();

        return ok(views.html.VIewAll.personalities.render(personalityTypes));
    }

    @Transactional (readOnly = true)
    public Result getAddPersonality(){
        TypedQuery<CharacterPersonalityType> personalityTypeTypedQuery = db.em().createQuery(
                "SELECT pt " +
                        "FROM CharacterPersonalityType pt " +
                        "ORDER BY personalityTypeId",
                CharacterPersonalityType.class);
        List<CharacterPersonalityType> personalityTypes = personalityTypeTypedQuery.getResultList();

        return ok(views.html.Add.addpersonality.render(personalityTypes));
    }

    @Transactional
    public Result postAddPersonality() {
        CharacterPersonalityType personalityType = new CharacterPersonalityType();

        DynamicForm form = formFactory.form().bindFromRequest();

        String personalityName = form.get("personalityName");
        String personalityDescription = form.get("personalityDescription");

        personalityType.setPersonalityTypeName(personalityName);
        personalityType.setPersonalityTypeDescription(personalityDescription);
        db.em().persist(personalityType);

        TypedQuery<CharacterPersonalityType> personalityTypeTypedQuery = db.em().createQuery(
                "SELECT pt " +
                        "FROM CharacterPersonalityType pt " +
                        "ORDER BY personalityTypeId",
                CharacterPersonalityType.class);
        List<CharacterPersonalityType> personalityTypes = personalityTypeTypedQuery.getResultList();

        return ok(views.html.VIewAll.personalities.render(personalityTypes));
    }

}
