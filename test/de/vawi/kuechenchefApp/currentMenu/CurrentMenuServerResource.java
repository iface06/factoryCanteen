

package de.vawi.kuechenchefApp.currentMenu;

import de.vawi.kuechenchefApp.RequestBoundary;
import de.vawi.kuechenchefApp.RequestBoundary;
import de.vawi.kuechenchefApp.currentMenu.CurrentMenuRequest;


class CurrentMenuServerResource implements RequestBoundary<CurrentMenuRequest> {

    private CurrentMenuRequest request;

    @Override
    public CurrentMenuRequest getRequest() {
        return request;
    }

}
