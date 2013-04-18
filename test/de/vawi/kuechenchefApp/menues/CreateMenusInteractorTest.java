
package de.vawi.kuechenchefApp.menues;

import de.vawi.kuechenchefApp.RequestBoundary;
import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CreateMenusInteractorTest {

    private List<Menu> storedMenues;
    
    @Before
    public void before(){
        storedMenues = new ArrayList<>();
    }
    
    @Test
    public void testSomeMethod() {
        CreateMenusRequest request = mock(CreateMenusRequest.class);
        RequestBoundary<CreateMenusRequest> requestBoundary = mock(RequestBoundary.class);
        when(requestBoundary.getRequest()).thenReturn(request);
        CreateMenusInteractor interactor = new CreateMenusInteractor(requestBoundary);
        interactor.execute();
        
        assertEquals(6, storedMenues.size());
    }
    
}