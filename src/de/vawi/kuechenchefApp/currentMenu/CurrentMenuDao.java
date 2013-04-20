
package de.vawi.kuechenchefApp.currentMenu;

import de.vawi.kuechenchefApp.entities.*;


public interface CurrentMenuDao {

    public Menu findCurrentMenuFor(Canteen c);

}
