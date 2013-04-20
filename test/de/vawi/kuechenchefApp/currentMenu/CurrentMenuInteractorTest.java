package de.vawi.kuechenchefApp.currentMenu;

import de.vawi.kuechenchefApp.RequestBoundary;
import de.vawi.kuechenchefApp.entities.*;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class CurrentMenuInteractorTest {

    @Test
    public void test() {
        RequestBoundary<CurrentMenuRequest> cmr = createRequestBoundary();
        CurrentMenuInteractor cmi = new CurrentMenuInteractor(cmr);
        cmi.setDao(createDao());
        cmi.execute();

        assertNotNull(cmi.getResponse().getMenu());
    }

    private RequestBoundary<CurrentMenuRequest> createRequestBoundary() {
        final CurrentMenuRequest request = new CurrentMenuRequest();
        request.setCanteen(Canteen.ESSEN);
        RequestBoundary<CurrentMenuRequest> cmr = new RequestBoundary<CurrentMenuRequest>() {
            @Override
            public CurrentMenuRequest getRequest() {
                return request;
            }
        };

        return cmr;
    }

    private CurrentMenuDao createDao() {
        return new CurrentMenuDao() {
            @Override
            public Menu findCurrentMenuFor(Canteen c) {
                return new Menu();
            }
        };
    }
}