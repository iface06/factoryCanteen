
package de.vawi.factoryCanteen.persistence.files;

import java.util.List;


/**
 * Verwendete müssen dieses Interface implementieren
 * @author Tatsch
 * @version 30.01.2013
 * 
 */
public interface CsvFile extends Iterable<String> {

    public String getDateiname();
    
    
}
