
package de.vawi.kuechenchefApp.dateien;

import java.io.IOException;

/**
 *
 * @author Tatsch
 * @version 30.01.2013
 */
public interface DateiSchreiberManager {

    public void openOutFile() throws IOException;
        public void writeLine(String in_str);
        public void closeOutFile();
}
