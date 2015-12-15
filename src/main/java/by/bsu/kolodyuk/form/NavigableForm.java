package by.bsu.kolodyuk.form;


import by.bsu.kolodyuk.ScreensConfig;
import by.bsu.kolodyuk.model.Session;

public class NavigableForm {

    protected ScreensConfig screens;
    protected Session session;

    public NavigableForm(ScreensConfig screens, Session session) {
        this.screens = screens;
        this.session = session;
    }
}
