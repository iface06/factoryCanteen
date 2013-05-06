package de.vawi.kuechenchefApp;

/**
 * Interface für das Einlesen und Schreiben von Daten beim start bzw. stoppen
 * der Anwendung.
 *
 * @author iface06
 */
public interface Persistence {

    public void setUp();

    public void tearDown();
}
