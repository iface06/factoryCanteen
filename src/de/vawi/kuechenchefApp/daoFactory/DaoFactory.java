package de.vawi.kuechenchefApp.daoFactory;

import de.vawi.kuechenchefApp.createMenu.CreateMenuDao;
import de.vawi.kuechenchefApp.currentMenu.FindMenuDao;

/**
 *
 * @author Tobias
 */
public abstract class DaoFactory {

    public static DaoFactory INSTANCE;

    public abstract CreateMenuDao makeCreateMenuDao();

    public abstract FindMenuDao makeCurrentMenuDao();
}
