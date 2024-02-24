package co.wedevx.digitalbank.automation.ui.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

//build a logic that reads the config file(properties file)
//this Class -is class for reading configurations from properties
public class ConfigReader {
    private static Properties properties;

    //below is static initializer , its not a method, but it helps you
    //initialize values and object
    //the main goal is the properties (line 10) is initialized with an input (line 37) which is values from our digitalbank.properties
    // static initializer run the block only once for the whole project
    //instance initalizer run the block once for every object creation from the class
    static {
        //file path -> directory of your properties file

        //to get path of digital.properties file -> right click on digital.properties
        //Copy Path/Reference -> Path from Content Root > paste path after =
        String filePath = "src/test/resources/properties/digitalbank.properties";

        //FileInputStream this is a class that enables you to read files
        //it throws a checked exception, so we need to tell the program
        //what should happen if file is not found, so we add try and catch
        //load also throws an exception, so we add catch as well
        //but since FileNotFoundException is child exception of IOException, we can delete
        //FileNotFoundException

        //we call somestreams, we need to close them
        FileInputStream input = null;
        try {
            input = new FileInputStream(filePath); //this class will read filePath file
            //we want initialize Properties
            properties = new Properties();
            properties.load(input);
            //we always need to close
        } /*catch (FileNotFoundException e) {
            System.out.println("File not found");
        }*/ catch (IOException e) {
            System.out.println("File not found");
        } finally {
            //for close we added exception to method signature
            //we call somestreams, we need to close them
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("browser");
        //when we run this, we should see Askar, sicne it will be pulled from the file
        //        String filePath = "src/test/java/co/wedevx/digitalbank/automation/ui/utils/ConfigReader.java";
        //and in this file we have/had my_name=Askar
       // System.out.println(properties.get("my_name"));
      //  System.out.println(properties.get("browser"));
    }
//instead of sytem.out.println, we wrapp it to method that we can reuse
    public static String getPropertiesValue(String key){
        return properties.getProperty(key);
    }

}
/*n this code we said, go read file located:         String filePath = "src/test/java/co/wedevx/digitalbank/automation/ui/utils/ConfigReader.java";
properties.load(input);   - take everything you read - input and load to this 'properties' Class*/