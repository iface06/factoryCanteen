package de.vawi.factoryCanteen.persistence.files;

import de.vawi.fileManagement.VawiFileManager;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Diese Klasse ist für das einlesen der Dateien zuständig.
 *
 * @author Tatsch
 * @version 30.01.2013
 */
public class CsvFileReader {

    protected String fileName;
    private FileReader reader;

    /**
     * Setzt Klasse zum lesen einer Datei
     */
    public void setFileReader(FileReader reader) {
        this.reader = reader;
    }

    /**
     * Diese Methode erstellt aus den eingelesenen Zeilen einer CsvFile ein
     * Objekt vom Typ CsvFile.
     *
     * @return eine CsvFile aus den eingelesenen Zeilen
     */
    public CsvFile readFile() {
        List<String> zeilen = readLineFromFile();
        CsvFile datei = erstelleDatei(zeilen);
        return datei;
    }

    private void openFile() throws IOException {
        reader.openInFile();
    }

    private List<String> readAllLines() throws IOException {
        List<String> lines = new ArrayList<>();
        while (!isEndOfFile()) {
            String zeile = readNextLine();
            if (zeile != null) {
                lines.add(zeile);
            }
        }

        return lines;
    }

    private boolean isEndOfFile() {
        return reader.eof();
    }

    private String readNextLine() throws IOException {
        return reader.readLine();
    }

    private void closeFile() throws IOException {
        reader.closeInFile();
    }

    /**
     *
     * @param ex Gibt einen Log aus, wenn die IOException geworfen wird.
     */
    protected void behandelFehlerfall(IOException ex) {
        Logger.getLogger(CsvFileReader.class.getName()).log(Level.SEVERE, null, ex);
    }

    private List<String> readLineFromFile() {
        List<String> lines = new ArrayList<>();
        try {
            openFile();
            lines = readAllLines();
            closeFile();
        } catch (IOException ex) {
            behandelFehlerfall(ex);
        }
        return lines;
    }

    private CsvFile erstelleDatei(final List<String> zeilen) {
        CsvFile datei = new CsvFile() {
            @Override
            public String getDateiname() {
                return fileName;
            }

            @Override
            public Iterator<String> iterator() {
                return zeilen.iterator();
            }
        };
        return datei;
    }
}
