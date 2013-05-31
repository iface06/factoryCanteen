package de.vawi.kuechenchefApp.createMenu;

import de.vawi.kuechenchefApp.entities.*;
import java.util.*;

/**
 *
 * @author Tobias
 */
public class OffersCreator {

    private List<Offer> offers = new ArrayList<>();
    private Periode periode;
    private CreateMenuDao dao;

    public List<Offer> create() {
        if (enoughtDishesAvailable()) {
            Date startDateOfPeriode = periode.nextStartDate();
            for (int i = 0; i < periode.quantityOfRequiredDishes(); i++) {

                offers.add(new Offer());
            }
        }


        return offers;
    }

    private boolean enoughtDishesAvailable() {
        return dao.areEnoughtDishesAvailable();
    }

    public void setPeriode(Periode periode) {
        this.periode = periode;
    }

    public void setDao(CreateMenuDao dao) {
        this.dao = dao;
    }
}
