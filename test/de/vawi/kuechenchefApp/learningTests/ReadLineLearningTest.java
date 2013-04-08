package de.vawi.kuechenchefApp.learningTests;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ReadLineLearningTest {

    @Test
    public void hello() {


        //init variable
        final String row = "1000,\"g\",\"Buttergemuese TK\",,\"5,42\",11";
        List<String> cells = new ArrayList<String>();
        boolean cellStarted = false;
        StringBuffer buffer = new StringBuffer();
        
        //splitString
        for (int i = 0; i < row.length(); i++) {
            char ch = row.charAt(i);
            if (ch == '\"') {
                cellStarted = !cellStarted;
            } else if ((ch == ',' && !cellStarted)) {
                cells.add(buffer.toString());
                buffer = new StringBuffer();
            } else {
                buffer.append(ch);
            }
        }
        cells.add(buffer.toString());
        
        assertEquals(6, cells.size());
    }
}
