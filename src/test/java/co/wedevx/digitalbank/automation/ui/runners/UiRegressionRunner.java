package co.wedevx.digitalbank.automation.ui.runners;


import org.junit.platform.suite.api.*;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite                                //consolidate multiple tests into one suite
@IncludeEngines("cucumber")           //choose what type of engine, what type of tests cucumber/junit? here we chose cucumber
@SelectClasspathResource("ui/features")  //in which folder in resources folder do you have your feature files? her I have my feature files in features folder so it will scan the features folder to find feature files
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "co.wedevx.digitalbank.automation.ui.steps")  //import this final constant  from junit.platform.engineas a key and make its value as path to steps

//@ExcludeTags("IGNORE")
public class UiRegressionRunner {

}
