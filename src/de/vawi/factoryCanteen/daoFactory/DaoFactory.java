package de.vawi.factoryCanteen.daoFactory;

import de.vawi.factoryCanteen.createMenu.CreateMenuDao;
import de.vawi.factoryCanteen.findMenu.FindMenuDao;

/**
 *
 * @author Tobias
 */
public abstract class DaoFactory {

    public static DaoFactory INSTANCE;

    public abstract CreateMenuDao makeCreateMenuDao();

    public abstract FindMenuDao makeFindMenuDao();
}
