package TestRunner;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
features= {"/src/test/resources/Features/Home_Page.feature"},
glue= {"StepDefination/HomePage.java"})

public class TestRunner{

}
