package de.vawi.factoryCanteen.app.createMenu;

import de.vawi.factoryCanteen.app.entities.PeriodeConfiguration;
import de.vawi.factoryCanteen.interactors.RequestBoundary;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.*;

/**
 *
 *
 * @author Tobias
 */
public class CreateMenusInteractorTest {

    private CreateMenusInteractor interactor;
    private MenuCreator creator;

    @Before
    public void before() {
        RequestBoundary<CreateMenusRequest> requestBoundary = createRequestBoundaryMock();
        CreateMenuDao dao = mock(CreateMenuDao.class);
        creator = mock(MenuCreator.class);

        interactor = new CreateMenusInteractor(requestBoundary);
        interactor.setDao(dao);
        interactor.setMenuCreator(creator);
    }

    @Test
    public void testCreatorSetupForRules() {
        interactor.execute();

        ArgumentCaptor<MenuCreationRule> argument = ArgumentCaptor.forClass(MenuCreationRule.class);
        verify(creator, times(4)).addRule(argument.capture());
        List<MenuCreationRule> rules = argument.getAllValues();

        assertEquals(4, rules.size());
        assertEquals(EveryDayDishMenuRule.class, rules.get(0).getClass());
        assertEquals(EveryDayDishMenuRule.class, rules.get(1).getClass());
        assertEquals(FishOnFridayRule.class, rules.get(2).getClass());
        assertEquals(AlternativeDihesRule.class, rules.get(3).getClass());
    }

    @Test
    public void testCreatorSetupForDao() {
        interactor.execute();

        verify(creator).setDao(any(CreateMenuDao.class));
    }

    @Test
    public void testCreatorSetupForPeriode() {
        interactor.execute();
        verify(creator).setPeriode(any(PeriodeConfiguration.class));
    }

    protected RequestBoundary<CreateMenusRequest> createRequestBoundaryMock() {
        CreateMenusRequest request = mock(CreateMenusRequest.class);
        when(request.getPeriode()).thenReturn(new PeriodeConfiguration());
        RequestBoundary<CreateMenusRequest> requestBoundary = mock(RequestBoundary.class);
        when(requestBoundary.passRequest()).thenReturn(request);
        return requestBoundary;
    }
}
