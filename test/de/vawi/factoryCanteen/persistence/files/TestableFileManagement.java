package de.vawi.factoryCanteen.persistence.files;

import de.vawi.fileManagement.VawiFileManager;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Ignore;

@Ignore
public class TestableFileManagement extends VawiFileManager {

    private String dateiInhalt = "";
    StringWriter writer = new StringWriter();

    public TestableFileManagement(String in_name) {
        super(in_name);
    }

    public void setDateiInhalt(String dateiInhalt) {
        this.dateiInhalt = dateiInhalt;
    }

    public String getDateiInhalt() {
        return writer.toString();
    }

    @Override
    protected BufferedReader createBufferedReader() throws FileNotFoundException {
        return new BufferedReader(new StringReader(dateiInhalt));
    }

    @Override
    protected PrintWriter createPrintWriter() throws IOException {
        return new PrintWriter(writer);
    }
}
