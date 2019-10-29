package controllers;

import models.Language;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.util.List;

public class LanguageController extends Controller {

    private JPAApi db;
    private FormFactory formFactory;

    @Inject
    public LanguageController(FormFactory formFactory, JPAApi db) {
        this.db = db;
        this.formFactory = formFactory;
    }

    @Transactional(readOnly = true)
    public Result getLanguage(int languageId) {
        TypedQuery<Language> languageTypedQuery = db.em().createQuery(
                "SELECT l " +
                        "FROM Language l " +
                        "WHERE languageId = :languageId",
                Language.class);
        languageTypedQuery.setParameter("languageId", languageId);
        Language language = languageTypedQuery.getSingleResult();

        return ok(views.html.ModelView.language.render(language));
    }

    @Transactional(readOnly = true)
    public Result getAllLanguages() {
        TypedQuery<Language> languageTypedQuery = db.em().createQuery(
                "SELECT l " +
                        "FROM Language l " +
                        "ORDER BY languageId",
                Language.class);
        List<Language> languages = languageTypedQuery.getResultList();

        return ok(views.html.VIewAll.languages.render(languages));
    }

    @Transactional(readOnly = true)
    public Result getAddLanguage() {
        TypedQuery<Language> languageTypedQuery = db.em().createQuery(
                "SELECT l " +
                        "FROM Language l " +
                        "ORDER BY languageId",
                Language.class);
        List<Language> languages = languageTypedQuery.getResultList();

        return ok(views.html.Add.addlanguage.render(languages));
    }

    @Transactional
    public Result postAddLanguage() {
        Language language = new Language();

        DynamicForm form = formFactory.form().bindFromRequest();

        String languageName = form.get("languageName");
        String languageDescription = form.get("languageDescription");
        String languagePicture = form.get("languagePicture");

        language.setLanguageName(languageName);
        language.setLanguageDescription(languageDescription);
        language.setLanguagePicture(languagePicture);
        db.em().persist(language);

        TypedQuery<Language> languageTypedQuery = db.em().createQuery(
                "SELECT l " +
                        "FROM Language l " +
                        "ORDER BY languageId",
                Language.class);
        List<Language> languages = languageTypedQuery.getResultList();

        return ok(views.html.VIewAll.languages.render(languages));
    }
}
