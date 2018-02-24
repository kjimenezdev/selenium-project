import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//comment the above line and uncomment below line to use Chrome
import org.openqa.selenium.chrome.ChromeDriver;
public class ProyectoQA {
  
  private static WebDriver DRIVER;
  
  public static void main(String[] args) {
//        // declaration and instantiation of objects/variables
//     System.setProperty("webDRIVER.firefox.marionette","C:\\geckoDRIVER.exe");
//  
//     WebDriver DRIVER = new FirefoxDriver();
    //comment the above 2 lines and uncomment below 2 lines to use Chrome
    System.setProperty("webdriver.chrome.driver","/Users/rapuc/Downloads/chromedriver");
    DRIVER = new ChromeDriver();
    if(TC1())
      System.out.println("ja");
    else
      System.out.println("nein");
    
    
  }
  
  
  public static boolean TC1(){
    String baseUrl = "http://demo.guru99.com/selenium/newtours/";
    String expectedTitle = "Welcome: Mercury Tours";
    String actualTitle = "";
    
    // launch Fire fox and direct it to the Base URL
    DRIVER.get(baseUrl);
    
    // get the actual value of the title
    actualTitle = DRIVER.getTitle();
    
    /*
     * compare the actual title of the page with the expected one and print
     * the result as "Passed" or "Failed"
     */
    boolean result = false;
    if(actualTitle.contentEquals(expectedTitle)){
      result = true;
    } else {
      result = false;
    }
    
    //close Fire fox
    DRIVER.close();
    return result;
  }
}