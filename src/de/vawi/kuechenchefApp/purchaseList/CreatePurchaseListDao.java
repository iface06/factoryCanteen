
package de.vawi.kuechenchefApp.purchaseList;

import de.vawi.kuechenchefApp.entities.*;
import java.util.List;


public interface CreatePurchaseListDao {
    public PreisListenPosition findeAngebotFuerNahrungsmittelVonLieferant(Food nahrungsmittel, Supplier lieferant);
    public List<PreisListenPosition> findeDurchNahrungsmittel(Food nahrungsmittel);

}
