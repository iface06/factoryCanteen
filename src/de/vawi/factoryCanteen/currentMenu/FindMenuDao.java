
package de.vawi.factoryCanteen.currentMenu;

import de.vawi.factoryCanteen.entities.Canteen;
import de.vawi.factoryCanteen.entities.Menu;


public interface FindMenuDao {

    public Menu findCurrentMenuFor(Canteen c);

}
