package de.vawi.factoryCanteen.app.daoFactory;

import de.vawi.factoryCanteen.app.createMenu.CreateMenuDao;
import de.vawi.factoryCanteen.app.findMenu.FindMenuDao;

/**
 *
 * @author Tobias
 */
public abstract class DaoFactory {

    public static DaoFactory INSTANCE;

    public abstract CreateMenuDao makeCreateMenuDao();

    public abstract FindMenuDao makeFindMenuDao();
}
