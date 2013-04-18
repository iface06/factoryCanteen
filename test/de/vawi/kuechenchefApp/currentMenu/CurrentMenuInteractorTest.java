

package de.vawi.kuechenchefApp.currentMenu;

import de.vawi.kuechenchefApp.currentMenu.CurrentMenuRequest;
import de.vawi.kuechenchefApp.RequestBoundary;
import de.vawi.kuechenchefApp.RequestBoundary;
import de.vawi.kuechenchefApp.currentMenu.CurrentMenuDao;
import de.vawi.kuechenchefApp.currentMenu.CurrentMenuInteractor;
import de.vawi.kuechenchefApp.currentMenu.CurrentMenuPresenter;

import de.vawi.kuechenchefApp.menues.Menu;
import org.junit.*;
import static org.junit.Assert.*;


public class CurrentMenuInteractorTest {

    @Test
    public void test(){
        RequestBoundary<CurrentMenuRequest> cmr = new CurrentMenuServerResource();
        CurrentMenuInteractor cmi = new CurrentMenuInteractor(cmr);
        cmi.setDao(new CurrentMenuDao() {

            @Override
            public Menu findCurrentMenu() {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        cmi.execute();
        
        CurrentMenuPresenter cmp = new CurrentMenuPresenter(cmi);
        Menu cm = cmp.getMenu();
        
        assertNotNull(cm);
    }

}