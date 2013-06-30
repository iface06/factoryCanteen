package de.vawi.factoryCanteen.app.findMenu;

import de.vawi.factoryCanteen.app.entities.*;
import de.vawi.factoryCanteen.interactors.RequestBoundary;
import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.mockito.ArgumentCaptor;

public class FindMenuInteractorTest {

    private CalendarWeek givenWeek;
    private FindMenuInteractor interactor;
    private FindMenuDao dao;

    @Before
    public void before() {
        givenWeek = CalendarWeek.current();
        RequestBoundary<FindMenuRequest> cmr = createRequestBoundaryMock(givenWeek);
        dao = createDaoMock();
        interactor = new FindMenuInteractor(cmr);
        interactor.setDao(dao);
    }

    @Test
    public void testDaoCall() {
        interactor.execute();

        ArgumentCaptor<CalendarWeek> argument = ArgumentCaptor.forClass(CalendarWeek.class);
        verify(dao).findMenuForWeek(argument.capture());

        assertEquals(givenWeek, argument.getValue());

    }

    @Test
    public void testResponse() {
        interactor.execute();
        assertFalse(interactor.getResponse().getOffers().isEmpty());
    }

    private List<Offer> createOffers() {
        List<Offer> offers = new ArrayList<>();
        offers.add(new Offer());
        return offers;
    }

    protected RequestBoundary<FindMenuRequest> createRequestBoundaryMock(CalendarWeek week) {
        RequestBoundary<FindMenuRequest> cmr = mock(RequestBoundary.class);
        FindMenuRequest request = mock(FindMenuRequest.class);
        when(request.getCalendarWeek()).thenReturn(week);
        when(cmr.passRequest()).thenReturn(request);
        return cmr;
    }

    protected FindMenuDao createDaoMock() {
        FindMenuDao dao = mock(FindMenuDao.class);
        when(dao.findMenuForWeek(any(CalendarWeek.class))).thenReturn(createOffers());
        return dao;
    }
}
