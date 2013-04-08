/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.vawi.kuechenchefApp.export;

import de.vawi.kuechenchefApp.export.Cell;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tatsch
 * @version 30.01.2013
 */
public class Row {

    List<Cell> cells = new ArrayList<Cell>();
    
    /**
     *
     * @param value
     * @return 
     */
    public Row cell(String value) {
        Cell cell = new Cell(value);
        cells.add(cell);
        return this;
    }
    
}
