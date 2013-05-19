/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.vawi.kuechenchefApp.dummies;

import de.vawi.kuechenchefApp.entities.*;

/**
 *
 * @author Tatsch
 */
public class DummySpeise {

    private Dish speise = new Dish();

    public DummySpeise beliebtheit(int beliebtheit) {
        speise.setPopularity(beliebtheit);
        return this;
    }

    public DummySpeise name(String name) {
        speise.setName(name);
        return this;
    }

    public DummySpeise mitZutat(Ingredient zutat) {
        speise.addIngredient(zutat);
        return this;
    }

    public Dish erstelle() {
        return speise;
    }
}
