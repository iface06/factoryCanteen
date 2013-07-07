package de.vawi.factoryCanteen.app.entities;

import de.vawi.factoryCanteen.app.entities.Dish;
import java.io.Serializable;
import java.util.*;

/**
 *
 * @author Tobias
 */
public class Offer implements Serializable {

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

    public DishCategory getDishCategory() {
        return dish.getCategory();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.dish);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Offer other = (Offer) obj;
        if (!Objects.equals(this.dish, other.dish)) {
            return false;
        }
        return true;
    }
}
