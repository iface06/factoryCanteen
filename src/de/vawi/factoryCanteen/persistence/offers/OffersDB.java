package de.vawi.factoryCanteen.persistence.offers;

import de.vawi.factoryCanteen.entities.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

/**
 * Die Klasse Verwaltet die erstellen Menues
 *
 * @author Tobias
 */
public class OffersDB {

    private static OffersDB instance;
    public static final String MENUES_FILE_NAME = "importFiles/offers.ser";

    /**
     * MenuesManager ist als Singelton implementiert, da in der gesamten
     * Anwendung nur ein MenuesManager benötigt wird
     *
     * @return MenuesManager
     */
    public static OffersDB getInstance() {
        if (instance == null) {
            instance = new OffersDB();
        }

        return instance;
    }

    OffersDB() {
    }
    List<Offer> offers = new ArrayList<>();

    public void storeOffers(List<Offer> offers) {
        this.offers.addAll(offers);
    }

    void deserializeOffers() {
        FileInputStream inputFile = null;
        try {
            inputFile = openInputStreamForOffers();
            readOffersFrom(inputFile);
        } catch (ClassNotFoundException ex) {
            handleClassNotFoundException(ex);
        } catch (IOException ex) {
            handleIoException(ex);
        } finally {
            closeInputFile(inputFile);
        }
    }

    public void serializeOffers() {
        FileOutputStream outputFile = null;
        try {
            outputFile = openOutputStreamForOffers();
            writeOffersTo(outputFile);
        } catch (IOException ex) {
            handleIoException(ex);
        } finally {
            closeFile(outputFile);
        }
    }

    protected void closeFile(FileOutputStream outputFile) {
        try {
            outputFile.close();
        } catch (IOException ex) {
            throw new SerializeMenuException(ex);
        }
    }

    protected void writeOffersTo(FileOutputStream outputFile) throws IOException, FileNotFoundException {
        ObjectOutputStream objectWriter = new ObjectOutputStream(outputFile);
        objectWriter.writeObject(offers);
        outputFile.close();
    }

    protected void handleIoException(IOException ex) {
        throw new SerializeMenuException(ex);
    }

    protected FileOutputStream openOutputStreamForOffers() throws FileNotFoundException {
        FileOutputStream outputFile;
        outputFile = new FileOutputStream(MENUES_FILE_NAME);
        return outputFile;
    }

    protected void rest() {
        this.offers = new ArrayList<>();
    }

    public void closeInputFile(FileInputStream inputFile) {
        try {
            inputFile.close();
        } catch (IOException ex) {
            throw new SerializeMenuException(ex);
        }
    }

    private FileInputStream openInputStreamForOffers() throws FileNotFoundException {
        FileInputStream inputFile;
        inputFile = new FileInputStream(MENUES_FILE_NAME);
        return inputFile;
    }

    private void readOffersFrom(FileInputStream inputFile) throws ClassNotFoundException, IOException {
        ObjectInputStream objectReader = new ObjectInputStream(inputFile);
        offers = (List<Offer>) objectReader.readObject();
    }

    protected void handleClassNotFoundException(ClassNotFoundException ex) {
        throw new SerializeMenuException(ex);
    }

    public Date findDateOfLastOffer() {
        Offer lastOffer = null;
        for (Offer offer : offers) {
            if (lastOffer == null || offer.getDate().after(lastOffer.getDate())) {
                lastOffer = offer;
            }
        }

        return lastOffer != null ? lastOffer.getDate() : new DateTime().withDayOfWeek(DateTimeConstants.FRIDAY).toDate();
    }

    public List<Offer> findCurrentMenu(PeriodeConfiguration periode) {
        return new ArrayList<>();

    }

    public class SerializeMenuException extends RuntimeException {

        public SerializeMenuException(Throwable cause) {
            super(cause);
        }
    }
}
