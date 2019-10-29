package controllers;

import models.CharacterHairColor;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.util.List;

public class HairColorController extends Controller {

    private JPAApi db;
    private FormFactory formFactory;

    @Inject
    public HairColorController (FormFactory formFactory, JPAApi db){
        this.formFactory = formFactory;
        this.db = db;
    }

    @Transactional(readOnly = true)
    public Result getHairColor(int hairColorId){
        TypedQuery<CharacterHairColor> hairColorTypedQuery = db.em().createQuery(
                "SELECT hc " +
                        "FROM CharacterHairColor hc " +
                        "WHERE hairColorId = :hairColorId",
                CharacterHairColor.class);
        hairColorTypedQuery.setParameter("hairColorId", hairColorId);
        CharacterHairColor hairColor = hairColorTypedQuery.getSingleResult();

        return ok(views.html.ModelView.haircolor.render(hairColor));
    }

    @Transactional(readOnly = true)
    public Result getAllHairColors (){
        TypedQuery<CharacterHairColor> hairColorTypedQuery = db.em().createQuery(
                "SELECT hc " +
                        "FROM CharacterHairColor hc " +
                        "ORDER BY hairColorId",
                CharacterHairColor.class);
        List<CharacterHairColor> hairColors = hairColorTypedQuery.getResultList();

        return ok(views.html.VIewAll.haircolors.render(hairColors));
    }

    @Transactional(readOnly = true)
    public Result getAddHairColor(){
        TypedQuery<CharacterHairColor> hairColorTypedQuery = db.em().createQuery(
                "SELECT hc " +
                        "FROM CharacterHairColor hc " +
                        "ORDER BY hairColorId",
                CharacterHairColor.class);
        List<CharacterHairColor> hairColors = hairColorTypedQuery.getResultList();

        return ok(views.html.Add.addhaircolor.render(hairColors));
    }

    @Transactional
    public Result postAddHairColor(){
        CharacterHairColor hairColor = new CharacterHairColor();

        DynamicForm form = formFactory.form().bindFromRequest();
        String hairColorName = form.get("hairColorName");
        String hairColorDescription = form.get("hairColorDescription");

        hairColor.setHairColorName(hairColorName);
        hairColor.setHairColorDescription(hairColorDescription);

        db.em().persist(hairColor);

        TypedQuery<CharacterHairColor> hairColorTypedQuery = db.em().createQuery(
                "SELECT hc " +
                        "FROM CharacterHairColor hc " +
                        "ORDER BY hairColorId",
                CharacterHairColor.class);
        List<CharacterHairColor> hairColors = hairColorTypedQuery.getResultList();

        return ok(views.html.VIewAll.haircolors.render(hairColors));
    }
}
