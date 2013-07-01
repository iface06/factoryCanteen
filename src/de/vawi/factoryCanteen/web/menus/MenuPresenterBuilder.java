package de.vawi.factoryCanteen.web.menus;

import de.vawi.factoryCanteen.app.entities.CalendarWeek;
import de.vawi.factoryCanteen.app.entities.Offer;
import de.vawi.factoryCanteen.app.entities.PeriodeConfiguration;
import java.util.*;

/**
 *
 * @author Tobias
 */
class MenuPresenterBuilder {

    MenuPresenter presenter = new MenuPresenter();
    private Map<Date, List<Offer>> groupdDishes;
    private List<MenuRow> rows;

    public MenuPresenterBuilder() {
    }

    public MenuPresenter build(List<Offer> offers) {
        createRowsForMenu();
        groupOffersByDate(offers);
        populateOffersIntoMenu();
        populateCalendarWeekIntoMenu(offers);
        populateWeekdaysIntoMenu();


        return presenter;
    }

    public void sortOffersByPopularity(List<Offer> offers) {
        Collections.sort(offers, new Comparator<Offer>() {
            @Override
            public int compare(Offer o1, Offer o2) {
                return o1.getDish().getPopularity().compareTo(o2.getDish().getPopularity());
            }
        });
    }

    public List<MenuRow> createRowsForMenu() {
        rows = new ArrayList<>();
        for (int i = 0; i < new PeriodeConfiguration().getNumberOfDishesPerDay(); i++) {
            rows.add(new MenuRow());
        }
        presenter.setMenuRows(rows);
        return rows;
    }

    public void populateOffersIntoMenu() {
        for (Date date : groupdDishes.keySet()) {
            List<Offer> offersToday = groupdDishes.get(date);
            sortOffersByPopularity(offersToday);
            if (offersToday.size() == new PeriodeConfiguration().getNumberOfDishesPerDay()) {
                for (int i = 0; i < offersToday.size(); i++) {
                    rows.get(i).add(offersToday.get(i));
                }
            }
        }
    }

    public void populateCalendarWeekIntoMenu(List<Offer> offers) {
        if (!offers.isEmpty()) {
            presenter.setCalendarWeek(CalendarWeek.fromDate(offers.get(0).getDate()));
        }
    }

    public void groupOffersByDate(List<Offer> offers) {
        groupdDishes = new GroupOffersByDate().apply(offers);
    }

    private void populateWeekdaysIntoMenu() {
        presenter.setWeekdays(new ArrayList<>(groupdDishes.keySet()));
    }
}
