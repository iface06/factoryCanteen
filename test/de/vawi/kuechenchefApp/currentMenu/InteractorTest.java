

package de.vawi.kuechenchefApp.currentMenu;

import de.vawi.kuechenchefApp.RequestBoundary;
import de.vawi.kuechenchefApp.speiseplan.Menu;
import org.junit.*;
import static org.junit.Assert.*;


public class InteractorTest {

    @Test
    public void test(){
        RequestBoundary<CurrentMenuRequest> cmr = new CurrentMenuServerResource();
        CurrentMenuInteractor cmi = new CurrentMenuInteractor(cmr);
        cmi.setDao(new CurrentMenuDao() {

            @Override
            public Menu findCurrentMenu() {
                return new Menu();
            }
        });
        cmi.execute();
        
        CurrentMenuPresenter cmp = new CurrentMenuPresenter(cmi);
        Menu cm = cmp.getMenu();
        
        assertNotNull(cm);
    }

}