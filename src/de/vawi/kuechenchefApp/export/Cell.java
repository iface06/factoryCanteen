/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.vawi.kuechenchefApp.export;

/**
 *
 * @author Tatsch
 * @version 30.01.2013
 */
public class Cell {

    private String value;
    
    /**
     *
     * Setzt value
     * @param value
     */
    public Cell(String value) {
        this.value = value;
    }

    /**
     * Gibt value zurück
     * @return value
     */
    public String getValue() {
        return value;
    }
}
