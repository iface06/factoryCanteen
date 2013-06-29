package de.vawi.factoryCanteen.persistence.interactorDaos;

import de.vawi.factoryCanteen.daoFactory.DaoFactory;
import de.vawi.factoryCanteen.createMenu.CreateMenuDao;
import de.vawi.factoryCanteen.currentMenu.FindMenuDao;

/**
 *
 * @author Tobias
 */
public class FileDaoFactory extends DaoFactory {

    @Override
    public CreateMenuDao makeCreateMenuDao() {
        return new CreateMenuesFileDao();
    }

    @Override
    public FindMenuDao makeFindMenuDao() {
        return new FindMenuFileDao();
    }
}
