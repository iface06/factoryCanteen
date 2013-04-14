

package de.vawi.kuechenchefApp.currentMenu;

import de.vawi.kuechenchefApp.ResponseBoundary;
import de.vawi.kuechenchefApp.menues.Menu;


public class CurrentMenuPresenter {
    private final ResponseBoundary<CurrentMenuResponse> response;

    public CurrentMenuPresenter(ResponseBoundary<CurrentMenuResponse> response) {
        this.response = response;
    }

    public Menu getMenu() {
        return response.getResponse().getMenu();
    }

}
