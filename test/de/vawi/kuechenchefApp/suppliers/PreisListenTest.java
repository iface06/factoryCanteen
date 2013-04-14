

package de.vawi.kuechenchefApp.lieferanten;

import org.junit.*;
import static org.junit.Assert.*;


public class PreisListenTest {
    
    
    @Test
    public void leseDateien(){
        PreisListen listen = new PreisListen();
        listen.leseDateien("importDateien");
        
        assertTrue(listen.iterator().hasNext());
    }
    

}