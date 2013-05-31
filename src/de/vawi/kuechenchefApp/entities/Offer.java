package de.vawi.kuechenchefApp.entities;

import de.vawi.kuechenchefApp.entities.Dish;
import java.util.Date;

/**
 *
 * @author Tobias
 */
public class Offer {

    private Date date;
    private Dish dish;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }
}
