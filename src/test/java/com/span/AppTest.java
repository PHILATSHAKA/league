package com.span;

import static org.junit.Assert.*;
import java.io.FileInputStream;
import org.junit.Before;
import org.apache.commons.io.IOUtils;

import org.junit.Test;

/**
 * Unit test for league App.
 */
public class AppTest {

    @Before                                        
    public void setUp() {
      System.out.println("Set up method");;
    }

    @Test
    public void shouldWork() throws Exception {
        String[] testInputFileName = {"src/main/java/com/span/input-file.txt"};
        String testOutFile = "out-file.txt";
        String testFileName = "test-file.txt";
      
        App.main(testInputFileName);

        FileInputStream fis = new FileInputStream(testOutFile);
        FileInputStream expectedStream = new FileInputStream(testFileName);

       String actualData = IOUtils.toString(fis, "UTF-8");
       String expectedData = IOUtils.toString(expectedStream, "UTF-8");
       
       assertEquals(expectedData, actualData);
        
    }

}
