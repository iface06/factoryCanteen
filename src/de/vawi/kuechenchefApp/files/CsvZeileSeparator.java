package de.vawi.kuechenchefApp.dateien;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/**
 * 
 * Seperiert Zeilen aus .csv Dateien
 * 
 * @author Tatsch
 * @version 29.01.2013
 */
public class CsvZeileSeparator {

    List<String> cells;
    boolean cellStarted = false;
    StringBuffer buffer = new StringBuffer();

    /**
     *
     * @param zeile
     * @return Gibt Zellen einer Zeile aus einer .csv Datei zurück
     */
    public List<String> separiere(String zeile) {
        cells = new ArrayList<>();
        for (int index = 0; index < zeile.length(); index++) {
            char ch = zeile.charAt(index);
            if (isStartOrEndSymbol(ch)) {
                cellStarted = !cellStarted;
            } else if ((isSeparatorSymbol(ch) && !cellStarted)) {
                addBufferedCharsToCells();
            } else {
                buffer.append(ch);
            }
        }
        addBufferedCharsToCells();
        
        return cells;
    }

    private boolean isStartOrEndSymbol(char ch) {
        return ch == '\"';
    }

    private boolean isSeparatorSymbol(char ch) {
        return ch == ',';
    }

    private void addBufferedCharsToCells() {
        cells.add(buffer.toString());
        buffer = new StringBuffer();
    }
}
