

package de.vawi.kuechenchefApp.dateien;


/**
 * Klasse konvertiert Sting Werte in double bzw. integer
 * @author Tatsch
 * @version 30.01.2013
 */
public class Parse {

    
    /**
     *
     * @param value (Wert als String)
     * @return Gibt den übergebenen String als double zurück
     */
    public static double toDouble(String value){
        try {
            return Double.parseDouble(value.replace(',', '.'));
        } catch (NumberFormatException e) {
            throw new FehlerBeimParsen();
        }
    }

    /**
     *
     * @param value (Wert als String)
     * @return Gibt den übergebenen String als Integer zurück
     */
    public static int toInteger(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new FehlerBeimParsen();
        }
    }
    
    /**
     *  Ausnahme wenn Fehler beim Parsen
     */
    public static class FehlerBeimParsen extends RuntimeException{
        
    }
    
}
