package controllers;

import models.CharacterRole;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import javax.persistence.TypedQuery;

public class CharacterRoleController extends Controller {

    private JPAApi db;
    private FormFactory formFactory;

    @Inject

    public CharacterRoleController (JPAApi db, FormFactory formFactory){
        this.db = db;
        this.formFactory = formFactory;
    }

    @Transactional (readOnly = true)
    public Result getRole(int characterRoleId){
        TypedQuery<CharacterRole> characterRoleTypedQuery = db.em().createQuery(
                "SELECT cr " +
                        "FROM CharacterRole cr " +
                        "WHERE characterRoleId = :characterRoleId",
                CharacterRole.class);
        characterRoleTypedQuery.setParameter("characterRoleId", characterRoleId);
        CharacterRole characterRole = characterRoleTypedQuery.getSingleResult();

        return ok(views.html.characterroles.render(characterRole));
    }
}
