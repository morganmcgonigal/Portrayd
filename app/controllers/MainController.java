package controllers;

import models.Universe;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.util.List;

public class MainController extends Controller {

    private FormFactory formFactory;
    private JPAApi db;

    @Inject
    public MainController (FormFactory formFactory, JPAApi db){
        this.formFactory = formFactory;
        this.db = db;
    }

    @Transactional
    public Result getIndex(){
        TypedQuery<Universe> universeTypedQuery = db.em().createQuery(
                "SELECT u " +
                        "FROM Universe u " +
                        "ORDER BY universeid",
                Universe.class);
        List<Universe> universes = universeTypedQuery.getResultList();

        return ok(views.html.index.render(universes));
    }
}
