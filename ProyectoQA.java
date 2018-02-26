import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class ProyectoQA {
  
  private static WebDriver webDriver;
  
  public static void main(String[] args) {
    
//    System.setProperty("webdriver.chrome.driver","/Users/konradjimenezc/Downloads/chromedriver");
//    System.setProperty("webdriver.chrome.driver","C:\\Users\\Usuario\\Downloads\\chromedriver_win32\\chromedriver.exe");
    System.setProperty("webdriver.chrome.driver","/Users/rapuc/Downloads/chromedriver");
    if(TC1())
      System.out.println("TC1 Aprovado");
    else
      System.err.println("TC1 Fallido");
    if(TC2())
      System.out.println("TC2 Aprovado");
    else
      System.err.println("TC2 Fallido");
    if(TC3())
      System.out.println("TC3 Aprovado");
    else 
      System.err.println("TC3 Fallido");
    if(TC4())
      System.out.println("TC4 Aprovado");
    else
      System.err.println("TC4 Fallido");
    if(TC5())
      System.out.println("TC5 Aprovado");
    else
      System.err.println("TC5 Fallido");
    if(TC7())
      System.out.println("TC7 Aprovado");
    else
      System.err.println("TC7 Fallido");
  }
  
  
  public static boolean TC1(){
    webDriver = new ChromeDriver();
    String baseUrl = "http://demo.nopcommerce.com";
    String expectedTitle = "nopCommerce demo store";
    String actualTitle = "";
    
    webDriver.get(baseUrl);
    actualTitle = webDriver.getTitle();
    
    
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
    webDriver.quit();
    return result;
  }
  
  public static boolean TC2(){
    try{
      String baseUrl = "http://demo.nopcommerce.com/";
      boolean result = true;
      
      webDriver = new ChromeDriver();
      
      //Ir al URL
      webDriver.get(baseUrl);
      
      //Opcion para iniciar el navegador maximizado
      //webDriver.manage().window().maximize();
      
      int[] targetListItems = {0, 4, 8, 12, 13, 14, 15};
      
      for(int i=0;i<7; i++){
        WebElement list = webDriver.findElement(By.className("top-menu"));
        
        if(list.isDisplayed()){
          WebElement element = list.findElements(By.tagName("li")).get(targetListItems[i]);
          
          WebDriverWait wait = new WebDriverWait(webDriver, 10);
          wait.until(ExpectedConditions.elementToBeClickable(element));
          
          element.click();
          webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
          WebElement logo = webDriver.findElement(By.className("header-logo"));
          logo.click();
          webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        }
        
        else{
          WebElement menuToggle = webDriver.findElement(By.className("menu-toggle"));
          menuToggle.click();
          
          WebElement listMobile = webDriver.findElement(By.cssSelector(".top-menu.mobile"));
          WebElement element = listMobile.findElements(By.cssSelector("li")).get(targetListItems[i]);
          
          WebDriverWait wait = new WebDriverWait(webDriver, 10);
          wait.until(ExpectedConditions.elementToBeClickable(element));
          
          element.click();
          webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
          WebElement logo = webDriver.findElement(By.className("header-logo"));
          logo.click();
          webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        }
      }
      
      return result;
    }
    catch(Exception e){
      e.printStackTrace();
      return false;
    }
  }
  
  public static boolean TC3(){
    
    // Verifique que se despliega el mensaje
    String baseUrl = "http://demo.nopcommerce.com/";
    webDriver.get(baseUrl);
    
    boolean emptyWishList = checkEmptyWishList(webDriver);
    searchItem(webDriver,"Fahrenheit 451");
    WebElement withListButton = webDriver.findElement(By.xpath("//input[@class='button-2 add-to-wishlist-button']"));
    withListButton.click();
    
    webDriver.get(baseUrl);
    boolean addedElement =  !checkEmptyWishList(webDriver);
    
    return emptyWishList && addedElement;
  }
  
  
  public static boolean TC4(){
    boolean result = false;
    String baseUrl = "http://demo.nopcommerce.com";
    
    boolean emptyShoppingCartList = checkEmptyShoppingCart(webDriver);
    
    
    WebElement wishListLink = webDriver.findElement(By.className("ico-wishlist"));
    wishListLink.click();
    
    webDriver.findElement(By.name("addtocart")).click(); //checkbox
    WebElement addToCartButton = webDriver.findElement(By.name("addtocartbutton"));
    addToCartButton.click();
    boolean addedElement = !checkEmptyShoppingCart(webDriver);
    
    WebElement continueShoppingButton = webDriver.findElement(By.name("continueshopping"));
    continueShoppingButton.click();    
    
    
    return emptyShoppingCartList && addedElement;
  }
  
  public static boolean TC5(){
    String baseUrl = "http://demo.nopcommerce.com";
    
    webDriver.get(baseUrl);
    WebElement shoppingCartLink = webDriver.findElement(By.id("topcartlink"));
    shoppingCartLink.click();
    
    Select dropdown = new Select(webDriver.findElement(By.className("country-input")));
    dropdown.selectByVisibleText("Costa Rica");
    webDriver.findElement(By.className("zip-input")).sendKeys("35000");
    webDriver.findElement(By.className("estimate-shipping-button")).click();
    webDriver.findElement(By.id("termsofservice")).click();
    webDriver.findElement(By.id("checkout")).click();
    
    String expectedTitle = "Welcome, Please Sign In!";
    String actualTitle = webDriver.findElement(By.className("page-title")).getText();
    
    boolean correctTitle = (actualTitle.contentEquals(expectedTitle))? true : false;
    
    webDriver.findElement(By.id("topcartlink")).click();
    
    webDriver.findElement(By.className("qty-input")).clear();
    webDriver.findElement(By.className("qty-input")).sendKeys("0");
    webDriver.findElement(By.className("update-cart-button")).click();
    
    boolean emptyCart = checkEmptyShoppingCart(webDriver);
    
    webDriver.quit();
    return correctTitle && emptyCart;
  }
  
  public static boolean TC7(){
    String baseUrl = "http://demo.nopcommerce.com/";
    
    webDriver = new ChromeDriver();
    webDriver.get(baseUrl);
    
    boolean emptyWishList = checkEmptyWishList(webDriver);
    
//    File file = new File("C:\\Users\\Usuario\\Downloads\\CI2400 Investigaci�n - Parametros\\Parametros.csv");
    File file = new File("/Users/rapuc/Downloads/Parametros.csv");
    if(!file.exists()){
      System.out.println("Bad file url");
      return false;
    }
    boolean correctPrice = true;
    try{
      BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
      String line;
      
      while((line = bufferedReader.readLine()) != null){
        StringTokenizer stringTokenizer = new StringTokenizer(line,",");
        
        String productName = stringTokenizer.nextToken();
        
        searchItem(webDriver,productName);
        
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(webDriver.findElement(By.className("add-to-wishlist-button"))));
        
        webDriver.findElement(By.className("add-to-wishlist-button")).click();
        webDriver.findElement(By.className("ico-wishlist")).click();
        webDriver.findElement(By.className("qty-input")).clear();
        webDriver.findElement(By.className("qty-input")).sendKeys(stringTokenizer.nextToken());
        webDriver.findElement(By.className("update-wishlist-button")).click();
        
        String expectedPrice = stringTokenizer.nextToken();
        String actualPrice = webDriver.findElement(By.className("product-subtotal")).getText();
        
        if (!actualPrice.contentEquals(expectedPrice)){
          correctPrice = false;
        }
        
        webDriver.findElement(By.name("removefromcart")).click();
        webDriver.findElement(By.className("update-wishlist-button")).click();
        
        System.out.println(productName + " fue procesado de manera " + ((correctPrice)? "correcta" : "incorrecta"));
      }
      bufferedReader.close();
    }catch(IOException e){
      e.printStackTrace();
      webDriver.quit();
      return false;
    }
    
    webDriver.quit();
    return emptyWishList && correctPrice;
  }
  
  
  private static boolean checkEmptyWishList(WebDriver webdriver){
    WebDriverWait wait = new WebDriverWait(webDriver, 10);
    wait.until(ExpectedConditions.elementToBeClickable(webDriver.findElement(By.className("ico-wishlist")))).click();
    WebElement emptyMessage = webDriver.findElement(By.className("no-data"));
    if(emptyMessage != null){
      if(emptyMessage.getText().equals("The wishlist is empty!")){
//        System.out.println("El wishlist está vacío");
        return true;
      }
    }
    return false;
  }
  private static boolean checkEmptyShoppingCart(WebDriver webdriver){
    WebDriverWait wait = new WebDriverWait(webDriver, 10);
    wait.until(ExpectedConditions.elementToBeClickable(webDriver.findElement(By.id("topcartlink")))).click();
    WebElement emptyMessage = webDriver.findElement(By.className("no-data"));
    if(emptyMessage != null){
      if(emptyMessage.getText().equals("Your Shopping Cart is empty!")){
//        System.out.println("El Shopping Cart está vacío");
        return true;
      }
    }
    return false;
  }
  
  private static void searchItem(WebDriver webDriver, String searchTerm){
    WebElement searchBarElement = webDriver.findElement(By.id("small-searchterms"));
    searchBarElement.sendKeys(searchTerm);
    WebElement searchButton = webDriver.findElement(By.xpath("//input[@class='button-1 search-box-button']"));
    searchButton.click();
  }
}