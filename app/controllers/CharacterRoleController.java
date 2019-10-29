package controllers;

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

        return ok(views.html.ModelView.characterrole.render(characterRole));
    }

    @Transactional(readOnly = true)
    public Result getRoleEdit(int characterRoleId){
        TypedQuery<CharacterRole> roleTypedQuery = db.em().createQuery(
                "SELECT cr " +
                        "FROM CharacterRole cr " +
                        "WHERE characterRoleId = :characterRoleId",
                CharacterRole.class);
        roleTypedQuery.setParameter("characterRoleId", characterRoleId);
        CharacterRole role = roleTypedQuery.getSingleResult();

        return ok(views.html.Edit.editrole.render(role));
    }

    @Transactional
    public Result postRoleEdit(int characterRoleId){
        TypedQuery<CharacterRole> roleTypedQuery = db.em().createQuery(
                "SELECT cr " +
                        "FROM CharacterRole cr " +
                        "WHERE characterRoleId = :characterRoleId",
                CharacterRole.class);
        roleTypedQuery.setParameter("characterRoleId", characterRoleId);
        CharacterRole role = roleTypedQuery.getSingleResult();

        DynamicForm form = formFactory.form().bindFromRequest();
        String characterRoleName = form.get("roleName");
        String characterRoleDescription = form.get("roleDescription");

        role.setCharacterRoleName(characterRoleName);
        role.setCharacterRoleDescription(characterRoleDescription);
        db.em().persist(role);

        TypedQuery<CharacterRole> rolesTypedQuery = db.em().createQuery(
                "SELECT cr " +
                        "FROM CharacterRole cr " +
                        "ORDER BY characterRoleId",
                CharacterRole.class);
        List<CharacterRole> roles = rolesTypedQuery.getResultList();

        return ok(views.html.VIewAll.characterroles.render(roles));
    }

    @Transactional (readOnly = true)
    public Result getAllRoles(){
        TypedQuery<CharacterRole> roleTypedQuery = db.em().createQuery(
                "SELECT cr " +
                        "FROM CharacterRole cr " +
                        "ORDER BY characterRoleId",
                CharacterRole.class);
        List<CharacterRole> roles = roleTypedQuery.getResultList();

        return ok(views.html.VIewAll.characterroles.render(roles));
    }

    @Transactional (readOnly = true)
    public Result getAddRole(){
        TypedQuery<CharacterRole> roleTypedQuery = db.em().createQuery(
                "SELECT cr " +
                        "FROM CharacterRole cr " +
                        "ORDER BY characterRoleId",
                CharacterRole.class);
        List<CharacterRole> characterRoles = roleTypedQuery.getResultList();

        return ok(views.html.Add.addrole.render(characterRoles));
    }

    @Transactional
    public Result postAddRole(){
        CharacterRole characterRole = new CharacterRole();

        DynamicForm form = formFactory.form().bindFromRequest();

        String roleName = form.get("roleName");
        String roleDescription = form.get("roleDescription");

        characterRole.setCharacterRoleName(roleName);
        characterRole.setCharacterRoleDescription(roleDescription);

        db.em().persist(characterRole);

        TypedQuery<CharacterRole> roleTypedQuery = db.em().createQuery(
                "SELECT cr " +
                        "FROM CharacterRole cr " +
                        "ORDER BY characterRoleId",
                CharacterRole.class);
        List<CharacterRole> roles = roleTypedQuery.getResultList();

        return ok(views.html.VIewAll.characterroles.render(roles));
    }

}
