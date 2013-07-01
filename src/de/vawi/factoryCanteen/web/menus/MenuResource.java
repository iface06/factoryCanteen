package de.vawi.factoryCanteen.web.menus;

import com.google.gson.Gson;
import de.vawi.factoryCanteen.app.createMenu.*;
import de.vawi.factoryCanteen.app.findMenu.FindMenuInteractor;
import de.vawi.factoryCanteen.app.findMenu.FindMenuRequest;
import de.vawi.factoryCanteen.app.daoFactory.DaoFactory;
import de.vawi.factoryCanteen.app.entities.*;
import java.util.List;
import org.restlet.representation.Representation;
import org.restlet.resource.*;

/**
 *
 * @author Tatsch
 */
public class MenuResource extends ServerResource {

    @Get("json")
    public String findMenu() {
        MenuResourceRequestBoundary<FindMenuRequest> requestBoundary = createRequestBoundaryForFindMenu();

        FindMenuInteractor interactor = new FindMenuInteractor(requestBoundary);
        interactor.setDao(DaoFactory.INSTANCE.makeFindMenuDao());
        interactor.execute();
        MenuPresenter presenter = new MenuPresenterBuilder().build(interactor.getResponse());
        return new Gson().toJson(presenter);
    }

    @Post("json")
    public String createMenu() {
        MenuResourceRequestBoundary<CreateMenusRequest> requestBoundary = createRequestBoundaryForCreateMenu();
        CreateMenusInteractor interactor = new CreateMenusInteractor(requestBoundary);
        interactor.setDao(DaoFactory.INSTANCE.makeCreateMenuDao());
        interactor.setMenuCreator(new MenuCreator());
        interactor.execute();
        List<Offer> menu = interactor.getResponse();
        MenuPresenter presenter = new MenuPresenterBuilder().build(menu);

        return new Gson().toJson(presenter);
    }

    private CalendarWeek extracCalendarWeekFromReqeust() throws NumberFormatException {
        String week = (String) getRequest().getAttributes().get("calendarWeek");
        String year = (String) getRequest().getAttributes().get("year");
        CalendarWeek calendarWeek = CalendarWeek.fromWeekAndYear(Integer.valueOf(week), Integer.valueOf(year));
        return calendarWeek;
    }

    private MenuResourceRequestBoundary<FindMenuRequest> createRequestBoundaryForFindMenu() throws NumberFormatException {
        MenuResourceRequestBoundary<FindMenuRequest> requestBoundary = new MenuResourceRequestBoundary<>();
        FindMenuRequest request = new FindMenuRequest();
        CalendarWeek calendarWeek = extracCalendarWeekFromReqeust();
        request.setCalendarWeek(calendarWeek);
        requestBoundary.setRequest(request);
        return requestBoundary;
    }

    private MenuResourceRequestBoundary<CreateMenusRequest> createRequestBoundaryForCreateMenu() {
        MenuResourceRequestBoundary<CreateMenusRequest> requestBoundary = new MenuResourceRequestBoundary<>();
        CreateMenusRequest request = new CreateMenusRequest();
        request.setPeriode(new PeriodeConfiguration());
        requestBoundary.setRequest(request);
        return requestBoundary;
    }
}
