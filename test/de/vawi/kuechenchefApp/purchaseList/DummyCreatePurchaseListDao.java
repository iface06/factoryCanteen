package de.vawi.kuechenchefApp.purchaseList;

import de.vawi.kuechenchefApp.entities.*;
import java.util.*;


class DummyCreatePurchaseListDao implements CreatePurchaseListDao {
    
    List<PriceListPosition> postions;

    public DummyCreatePurchaseListDao(List<PriceListPosition> postions) {
        this.postions = postions;
    }

    @Override
    public PriceListPosition findeAngebotFuerNahrungsmittelVonLieferant(Food nahrungsmittel, Supplier lieferant) {
        for (PriceListPosition position : postions) {
            if(position.getFood().equals(nahrungsmittel) && position.getSupplier().equals(lieferant))
                return position;
            
        }
        
        return null;
    }

    @Override
    public List<PriceListPosition> findeDurchNahrungsmittel(Food food) {
        List<PriceListPosition> foundings = new ArrayList<>();
        for (PriceListPosition position : postions) {
            if(position.getFood().equals(food))
                foundings.add(position);
        }
        
        return foundings;
    }

    
}
