
package de.vawi.kuechenchefApp.currentMenu;

import de.vawi.kuechenchefApp.entities.*;


public interface FindMenuDao {

    public Menu findCurrentMenuFor(Canteen c);

}
