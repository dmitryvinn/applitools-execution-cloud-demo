# Example of Using Execution Cloud with Selenium WebDriver, JUnit and Java

This repo has a short example of how to use the Applitool's Execution Cloud to test a web app with Selenium WebDriver, JUnit and Java.

## Development

It's recommended that you use an IDE like [Intellij IDEA](https://www.jetbrains.com/idea/) since it has support for Maven (build systems),
Java (programming language) and JUnit (testing framework).

## Deployment

After you build the project, you will need to set your Applitools API key and then simply run a Maven command:

```shell
export APPLITOOLS_API_KEY=[YOUR KEY]
mvn test
```