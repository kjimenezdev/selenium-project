import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
public class ProyectoQA {
  
  private static WebDriver DRIVER;
  
  public static void main(String[] args) {
    
    System.setProperty("webdriver.chrome.driver","/Users/rapuc/Downloads/chromedriver");
    
//    if(TC1())
//      System.out.println("TC1 Aprovado");
//    else
//      System.err.println("TC1 Fallido");
//    
    if(TC4())
      System.out.println("TC4 Aprovado");
    else
      System.err.println("TC4 Fallido");
    
    
  }
  
  
  public static boolean TC4(){
    DRIVER = new ChromeDriver();
    boolean result = false;
    String baseUrl = "http://demo.nopcommerce.com";
    
    DRIVER.get(baseUrl);
    WebElement shoppingCartLink = DRIVER.findElement(By.xpath("//li[@id='topcartlink']"));
    shoppingCartLink.click();
    WebElement emptyMessage = DRIVER.findElement(By.xpath("//div[@class='no-data']"));
    if(emptyMessage != null){
      if(emptyMessage.getText().equals("Your Shopping Cart is empty!")){
        
        result = true;
      }
    }
    
    DRIVER.close();
    return result;
  }
  
  public static boolean TC1(){
    DRIVER = new ChromeDriver();
    String baseUrl = "http://demo.nopcommerce.com";
    String expectedTitle = "nopCommerce demo store";
    String actualTitle = "";
    
    DRIVER.get(baseUrl);
    
    
    actualTitle = DRIVER.getTitle();
    
    
    boolean result = false;
    if(actualTitle.contentEquals(expectedTitle)){
      System.out.println("\tTítulo Correcto");
      result = true;
    } else {
      System.out.println("\tTítulo Incorrecto");
      result = false;
      
    }
    try{
      Thread.sleep(5000);
    }catch(InterruptedException ie){
    }
    
    DRIVER.close();
    return result;
  }
}