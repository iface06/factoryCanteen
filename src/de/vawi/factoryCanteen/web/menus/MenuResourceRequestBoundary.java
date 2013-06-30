package de.vawi.factoryCanteen.web.menus;

import de.vawi.factoryCanteen.app.createMenu.CreateMenusRequest;
import de.vawi.factoryCanteen.interactors.RequestBoundary;

/**
 *
 * @author Tobias
 */
public class MenuResourceRequestBoundary<T> implements RequestBoundary<T> {

    private T request;

    protected void setRequest(T request) {
        this.request = request;
    }

    @Override
    public T passRequest() {
        return request;
    }
}
