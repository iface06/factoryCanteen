package de.vawi.factoryCanteen.currentMenu;

import de.vawi.factoryCanteen.entities.Menu;
import de.vawi.factoryCanteen.entities.Canteen;
import de.vawi.factoryCanteen.currentMenu.FindMenuDao;
import de.vawi.factoryCanteen.currentMenu.FindMenuRequest;
import de.vawi.factoryCanteen.currentMenu.FindMenuInteractor;
import de.vawi.factoryCanteen.interactors.RequestBoundary;
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
