package de.vawi.kuechenchefApp.currentMenu;

import de.vawi.kuechenchefApp.interactors.RequestBoundary;
import de.vawi.kuechenchefApp.entities.*;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class CurrentMenuInteractorTest {

    @Test
    public void test() {
        RequestBoundary<FindMenuRequest> cmr = createRequestBoundary();
        FindMenuInteractor cmi = new FindMenuInteractor(cmr);
        cmi.setDao(createDao());
        cmi.execute();

        assertNotNull(cmi.getResponse().getMenu());
    }

    private RequestBoundary<FindMenuRequest> createRequestBoundary() {
        final FindMenuRequest request = new FindMenuRequest();
        request.setCanteen(Canteen.ESSEN);
        RequestBoundary<FindMenuRequest> cmr = new RequestBoundary<FindMenuRequest>() {
            @Override
            public FindMenuRequest passRequest() {
                return request;
            }
        };

        return cmr;
    }

    private FindMenuDao createDao() {
        return new FindMenuDao() {
            @Override
            public Menu findCurrentMenuFor(Canteen c) {
                return new Menu();
            }
        };
    }
}
