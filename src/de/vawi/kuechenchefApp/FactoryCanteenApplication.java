package de.vawi.kuechenchefApp;

import de.vawi.kuechenchefPersistence.FileSystem;

/**
 * Diese Klasse initialisiert und herunterfahren der Anwendung.
 *
 * @author Tobias
 */
public class FactoryCanteenApplication {

    Persistence persistence = new FileSystem();

    public void setUp() {
        persistence.setUp();
    }

    public void tearDown() {
        persistence.tearDown();
    }
}
