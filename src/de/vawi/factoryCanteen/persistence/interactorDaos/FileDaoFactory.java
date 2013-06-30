package de.vawi.factoryCanteen.persistence.interactorDaos;

import de.vawi.factoryCanteen.app.daoFactory.DaoFactory;
import de.vawi.factoryCanteen.app.createMenu.CreateMenuDao;
import de.vawi.factoryCanteen.app.findMenu.FindMenuDao;

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
