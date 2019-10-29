package controllers;

import models.Weapon;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import scala.Dynamic;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.util.List;

public class WeaponController extends Controller {

    private FormFactory formFactory;
    private JPAApi db;

    @Inject
    public WeaponController (FormFactory formFactory, JPAApi db){
        this.formFactory = formFactory;
        this.db = db;
    }

    @Transactional(readOnly = true)
    public Result getWeapon(int weaponId){
        TypedQuery<Weapon> weaponTypedQuery = db.em().createQuery(
                "SELECT w " +
                        "FROM Weapon w " +
                        "WHERE weaponId = :weaponId",
                Weapon.class);
        weaponTypedQuery.setParameter("weaponId", weaponId);
        Weapon weapon = weaponTypedQuery.getSingleResult();

        return ok(views.html.ModelView.weapon.render(weapon));
    }

    @Transactional(readOnly = true)
    public Result getAllWeapons(){
        TypedQuery<Weapon> weaponTypedQuery = db.em().createQuery(
                "SELECT w " +
                        "FROM Weapon w " +
                        "ORDER BY weaponId",
                Weapon.class);
        List<Weapon> weapons = weaponTypedQuery.getResultList();

        return ok(views.html.VIewAll.weapons.render(weapons));
    }

//    private String weaponName;
//    private String weaponDescription;

    @Transactional(readOnly = true)
    public Result getAddWeapon(){
        TypedQuery<Weapon> weaponTypedQuery = db.em().createQuery(
                "SELECT w " +
                        "FROM Weapon w " +
                        "ORDER BY weaponId",
                Weapon.class);
        List<Weapon> weapons = weaponTypedQuery.getResultList();

        return ok(views.html.Add.addweapon.render(weapons));
    }

    @Transactional
    public Result postAddWeapon(){
        Weapon weapon = new Weapon();

        DynamicForm form = formFactory.form().bindFromRequest();
        String weaponName = form.get("weaponName");
        String weaponDescription = form.get("weaponDescription");

        weapon.setWeaponName(weaponName);
        weapon.setWeaponDescription(weaponDescription);
        db.em().persist(weapon);

        TypedQuery<Weapon> weaponTypedQuery = db.em().createQuery(
                "SELECT w " +
                        "FROM Weapon w " +
                        "ORDER BY weaponId",
                Weapon.class);
        List<Weapon> weapons = weaponTypedQuery.getResultList();

        return ok(views.html.VIewAll.weapons.render(weapons));
    }
}
