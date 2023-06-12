package dev.dvinnik.applitools.executioncloud;

import com.applitools.eyes.selenium.fluent.Target;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class ExecutionTest extends ApplitoolsBaseTest {

    @Test
    public void test() {

        // Start the test and set the browser's viewport size
            String webUrlToUse =  "https://todomvc.com/examples/react/#/"; //"http://localhost:3000/";


            driver.get(webUrlToUse);                                // Navigate the browser to the web-site.
        eyes.check(Target.window().fully().withName("Main page").layout());


//            driver.findElement(By.className("Home_card___LpL1"));  // enter the name
//            eyes.checkWindow("After enter name)");                  //visual checkpoint number 2
//

//            driver.findElement(By.tagName("button")).click();      // Click the "Click me!" button.
//            eyes.checkWindow("After click");                       // Visual checkpoint 3

    }
}
