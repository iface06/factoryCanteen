
package de.vawi.kuechenchefApp.purchaseList;

import de.vawi.kuechenchefApp.entities.*;
import java.util.List;


public interface CreatePurchaseListDao {
    public PriceListPosition findeAngebotFuerNahrungsmittelVonLieferant(Food nahrungsmittel, Supplier lieferant);
    public List<PriceListPosition> findeDurchNahrungsmittel(Food nahrungsmittel);

}
