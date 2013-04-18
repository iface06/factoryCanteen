

package de.vawi.kuechenchefApp.menues;

import de.vawi.kuechenchefApp.*;
import java.util.List;


public class CreateMenusInteractor implements Interactor, ResponseBoundary<Menu>{

    private final RequestBoundary<CreateMenusRequest> requestBoundary;

    public CreateMenusInteractor(RequestBoundary<CreateMenusRequest> requestBoundary) {
        this.requestBoundary = requestBoundary;
    }
    
    @Override
    public void execute() {
    }

    @Override
    public Menu getResponse() {
        return null;
    }

    

    

}
