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
    public static String MENUES_FILE_NAME;

    /**
     * MenuesManager ist als Singelton implementiert, da in der gesamten
     * Anwendung nur ein MenuesManager benötigt wird
     *
     * @return MenuesManager
     */
    public static OffersDB getInstance() {
        if (instance == null) {
            instance = new OffersDB("importFiles/offers.ser");
        }

        return instance;
    }

    OffersDB(String path) {
        MENUES_FILE_NAME = path;
    }
    List<Offer> offers = new ArrayList<>();

    public void storeOffers(List<Offer> offers) {
        this.offers.addAll(offers);
    }

    public void deserializeOffers() {
        File file = new File(MENUES_FILE_NAME);
        if (file.isFile() && file.length() > 0) {
            readOffersFromFile(file);
        }
    }

    protected void readOffersFromFile(File file) {
        FileInputStream inputFile = null;
        try {
            inputFile = new FileInputStream(file);
            ObjectInputStream objectReader = new ObjectInputStream(inputFile);
            offers = (List<Offer>) objectReader.readObject();
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
            throw new SerializeOffersException(ex);
        }
    }

    protected void writeOffersTo(FileOutputStream outputFile) throws IOException, FileNotFoundException {
        ObjectOutputStream objectWriter = new ObjectOutputStream(outputFile);
        objectWriter.writeObject(offers);
        outputFile.close();
    }

    protected void handleIoException(IOException ex) {
        throw new SerializeOffersException(ex);
    }

    protected FileOutputStream openOutputStreamForOffers() throws FileNotFoundException, IOException {
        FileOutputStream outputFile;
        File file = new File(MENUES_FILE_NAME);
        file.createNewFile();
        outputFile = new FileOutputStream(file);
        return outputFile;
    }

    protected void rest() {
        this.offers = new ArrayList<>();
    }

    public void closeInputFile(FileInputStream inputFile) {
        try {
            inputFile.close();
        } catch (IOException ex) {
            throw new SerializeOffersException(ex);
        }
    }

    protected void handleClassNotFoundException(ClassNotFoundException ex) {
        throw new SerializeOffersException(ex);
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

    public List<Offer> findOffersForCalendarWeek(CalendarWeek week) {
        List<Offer> offersForWeek = new ArrayList<>();
        List<Date> weekDays = week.extractWorkingDaysOfWeek();
        for (Offer offer : offers) {
            if (weekDays.contains(offer.getDate())) {
                offersForWeek.add(offer);
            }
        }
        return offersForWeek;

    }

    public class SerializeOffersException extends RuntimeException {

        public SerializeOffersException(Throwable cause) {
            super(cause);
        }
    }
}
