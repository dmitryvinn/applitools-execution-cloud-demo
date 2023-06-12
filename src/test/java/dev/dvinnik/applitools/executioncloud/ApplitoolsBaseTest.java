package dev.dvinnik.applitools.executioncloud;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.selenium.BrowserType;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.fluent.Target;
import com.applitools.eyes.visualgrid.services.RunnerOptions;
import com.applitools.eyes.visualgrid.services.VisualGridRunner;

import dev.dvinnik.applitools.executioncloud.utils.PropertyKey;
import dev.dvinnik.applitools.executioncloud.utils.TestPropertiesUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import testzeus.base.SFPageBase;

public class ApplitoolsBaseTest {

    private static final String APPLITOOLS_API_KEY = TestPropertiesUtil.getInstance()
            .getProperty(PropertyKey.APPLITOOLS_API_KEY);

    private static Configuration config;
    private static VisualGridRunner runner;
    protected static WebDriver driver;

    protected Eyes eyes;

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void teardown() {
        eyes.closeAsync();
        driver.quit();

    }

    @BeforeAll
    public static void setUpConfigAndRunner() {
        runner = new VisualGridRunner(new RunnerOptions().testConcurrency(1));

        config = new Configuration();
        config.setApiKey(APPLITOOLS_API_KEY);

        final BatchInfo batch = new BatchInfo("Test ");
        config.setBatch(batch);



        config.addBrowser(1024, 768, BrowserType.CHROME);
    }


    @BeforeEach
    public void openBrowserAndEyes(TestInfo testInfo) {
        eyes = new Eyes(runner);
        eyes.setConfiguration(config);

        eyes.open(
                driver,
                "TestZeus Demo",
                testInfo.getDisplayName());
    }
}