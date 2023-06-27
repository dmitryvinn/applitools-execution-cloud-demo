package dev.dvinnik.applitools.executioncloud;


import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.BrowserType;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.fluent.Target;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class HomePageTest {

    private static boolean headless;
    private static Configuration config;
    private static EyesRunner runner;

    private WebDriver driver;
    private Eyes eyes;

    @BeforeAll
    public static void setUpConfigAndRunner() {
        headless = Boolean.parseBoolean(System.getenv().getOrDefault("HEADLESS", "false"));

        runner = new ClassicRunner();
        BatchInfo batch = new BatchInfo("Execution Cloud Example");

        config = new Configuration();

        config.setApiKey(System.getenv("APPLITOOLS_API_KEY"));

        config.setBatch(batch);
        config.addBrowser(800, 600, BrowserType.CHROME);

    }

    @BeforeEach
    public void openBrowserAndEyes(TestInfo testInfo) throws MalformedURLException {

        ChromeOptions options = new ChromeOptions().setHeadless(headless);

        driver = new RemoteWebDriver(new URL(Eyes.getExecutionCloudURL()), options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        eyes = new Eyes(runner);
        eyes.setConfiguration(config);

        eyes.open(
                driver,
                "Create Next App",
                testInfo.getDisplayName(),
                new RectangleSize(1024, 768));
    }

    @Test
    public void validateSectionsOnHomePage() {
        driver.get("https://execution-cloud-demo-app.vercel.app/");

        driver.findElement(By.id("docs-title"));
        driver.findElement(By.id("docs-desc"));

        driver.findElement(By.id("learn-title"));
        driver.findElement(By.id("learn-desc"));

        driver.findElement(By.id("examples-title"));
        driver.findElement(By.id("examples-desc"));

        driver.findElement(By.id("deploy-title"));
        driver.findElement(By.id("deploy-desc"));

        // Verify the full main page loaded correctly
        eyes.check(Target.window().fully().withName("Home page").layout());
    }

    @AfterEach
    public void cleanUpTest() {
        eyes.closeAsync();
        driver.quit();
    }
}