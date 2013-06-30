package de.vawi.factoryCanteen.web.menus;

import de.vawi.factoryCanteen.interactors.RequestBoundary;
import de.vawi.factoryCanteen.app.findMenu.FindMenuInteractor;
import de.vawi.factoryCanteen.app.findMenu.FindMenuRequest;
import de.vawi.factoryCanteen.app.findMenu.FindMenuResponse;
import de.vawi.factoryCanteen.app.daoFactory.DaoFactory;
import de.vawi.factoryCanteen.app.entities.CalendarWeek;
import de.vawi.factoryCanteen.app.entities.Canteen;
import de.vawi.factoryCanteen.persistence.interactorDaos.FindMenuFileDao;
import org.restlet.resource.*;

/**
 *
 * @author Tatsch
 */
public class MenuResource extends ServerResource implements RequestBoundary<FindMenuRequest> {

    FindMenuRequest request = new FindMenuRequest();
    FindMenuInteractor interactor = new FindMenuInteractor(this);
    FindMenuResponse response;

    @Override
    public FindMenuRequest passRequest() {
        return request;
    }

    @Get("json")
    public FindMenuResponse currentMenu() {
        initRequestForCurrentMenu();
        initInteractorForCurrentMenu();
        executeInteractor();
        return response;
    }

    private void initRequestForCurrentMenu() {
        CalendarWeek calendarWeek = extracCalendarWeekFromReqeust();
        request.setCalendarWeek(calendarWeek);

    }

    private void initInteractorForCurrentMenu() {
        interactor.setDao(DaoFactory.INSTANCE.makeFindMenuDao());
    }

    private void executeInteractor() {
        interactor.execute();
        response = interactor.getResponse();
    }

    public CalendarWeek extracCalendarWeekFromReqeust() throws NumberFormatException {
        String week = (String) getRequest().getAttributes().get("calendarWeek");
        String year = (String) getRequest().getAttributes().get("year");
        CalendarWeek calendarWeek = CalendarWeek.fromWeekAndYear(Integer.valueOf(week), Integer.valueOf(year));
        return calendarWeek;
    }

    public Canteen extractCanteenFromRequest() {
        String canteen = (String) getRequest().getAttributes().get("canteen");
        Canteen c;
        if (canteen.equals("Essen")) {
            c = Canteen.ESSEN;
        } else if (canteen.equals("Muehlheim")) {
            c = Canteen.MUELHEIM_AN_DER_RUHR;
        } else {
            throw new GivenCanteenDoesNotExist();
        }
        return c;
    }

    private static class GivenCanteenDoesNotExist extends RuntimeException {
    }
}
