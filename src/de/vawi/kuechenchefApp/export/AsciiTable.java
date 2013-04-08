/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.vawi.kuechenchefApp.export;

import com.bethecoder.ascii_table.ASCIITable;
import com.bethecoder.ascii_table.ASCIITableHeader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tatsch
 * @version 30.01.2013
 */
public class AsciiTable {

    private static final int HEADER_ROW = 1;
    private static final int ALIGNMENT = ASCIITable.ALIGN_LEFT ;
    
    List<Row> rows = new ArrayList<Row>();
    
    public Row row() {
        Row row = new Row();
        rows.add(row);
        return row;
    }

    public String writeTable() {
        ASCIITableHeader[] header = createHeaderArray();
        String[][] data = createDataArray();
        return ASCIITable.getInstance().getTable(header, data);
    }

    private ASCIITableHeader[] createHeaderArray() {
        if(!rows.isEmpty()){
            ASCIITableHeader[] header = new ASCIITableHeader[rows.get(0).cells.size()];
            Row headerRow = rows.get(0);
            int column = 0;
            for (Cell cell : headerRow.cells) {
                header[column] = new ASCIITableHeader(cell.getValue(), ALIGNMENT);
                column++;
            }
            return header;
        }
        throw new TabelleEnthaeltKeineDaten();
    }

    private String[][] createDataArray() {
        if(rows.size() > 1){
            String[][] data = new String[rows.size() - HEADER_ROW][];
            for(int rowNumber = 1; rowNumber < rows.size(); rowNumber++){
                Row current = rows.get(rowNumber);
                String[] rowData = createRowData(current);
                data[rowNumber - HEADER_ROW] = rowData; 
            }
            return data;
        }
        throw new ZeileEnthaeltKeineDaten();
    }

    private String[] createRowData(Row current) {
        String[] rowData = new String[current.cells.size()];
        int column = 0;
        for (Cell cell : current.cells) {
            rowData[column] = cell.getValue();
            column++;
        }
        return rowData;
    }

    private static class TabelleEnthaeltKeineDaten extends RuntimeException {
    }

    private static class ZeileEnthaeltKeineDaten extends RuntimeException {
    }
    
}
