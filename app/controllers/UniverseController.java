package controllers;

import models.Universe;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.util.List;

public class UniverseController extends Controller {

    private JPAApi db;
    private FormFactory formFactory;

    @Inject
    public UniverseController(FormFactory formFactory, JPAApi db){
        this.formFactory = formFactory;
        this.db = db;
    }

    @Transactional(readOnly = true)
    public Result getUniverse(int universeId){
        TypedQuery<Universe> universeTypedQuery = db.em().createQuery(
                "SELECT u " +
                        "FROM Universe u " +
                        "WHERE universeId = :universeId",
                Universe.class);
        universeTypedQuery.setParameter("universeId", universeId);
        Universe universe = universeTypedQuery.getSingleResult();

        return ok(views.html.ModelView.universe.render(universe));
    }

    @Transactional(readOnly = true)
    public Result getAllUniverses(){
        TypedQuery<Universe> universeTypedQuery = db.em().createQuery(
                "SELECT u " +
                        "FROM Universe u " +
                        "ORDER BY universeId",
                Universe.class);
        List<Universe> universes = universeTypedQuery.getResultList();

        return ok(views.html.VIewAll.universes.render(universes));
    }

//    private String universeName;
//    private String universeDesc;
//    private String universeGenre;
//    private String universeHistory;
//    private String universeNotes;
//    private String universePicture;

    @Transactional (readOnly = true)
    public Result getAddUniverse(){
        TypedQuery<Universe> universeTypedQuery = db.em().createQuery(
                "SELECT  u " +
                        "FROM Universe u " +
                        "ORDER BY universeId",
                Universe.class);
        List<Universe> universes = universeTypedQuery.getResultList();

        return ok(views.html.Add.adduniverse.render(universes));
    }

    @Transactional
    public Result postAddUniverse(){
        Universe universe = new Universe();

        DynamicForm form = formFactory.form().bindFromRequest();
        String universeName = form.get("universeName");
        String universeDesc = form.get("universeDesc");
        String universeGenre = form.get("universeGenre");
        String universeHistory = form.get("universeHistory");
        String universeNotes = form.get("universeNotes");
        String universePicutre = form.get("universePicture");

        universe.setUniverseName(universeName);
        universe.setUniverseDesc(universeDesc);
        universe.setUniverseGenre(universeGenre);
        universe.setUniverseHistory(universeHistory);
        universe.setUniverseNotes(universeNotes);
        universe.setUniversePicture(universePicutre);
        db.em().persist(universe);

        TypedQuery<Universe> universeTypedQuery = db.em().createQuery(
                "SELECT  u " +
                        "FROM Universe u " +
                        "ORDER BY universeId",
                Universe.class);
        List<Universe> universes = universeTypedQuery.getResultList();

        return ok(views.html.VIewAll.universes.render(universes));
    }
}
