import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.concurrent.TimeUnit;

public class ProyectoQA {
  
  private static WebDriver webDriver;
  
  public static void main(String[] args) {
    
//    System.setProperty("webdriver.chrome.driver","/Users/konradjimenezc/Downloads/chromedriver");
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
    if(TC6())      
      System.out.println("TC6 Aprovado");
    else
      System.err.println("TC6 Fallido");
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
    
    String baseUrl = "http://demo.nopcommerce.com/";
    webDriver.get(baseUrl);
    boolean emptyWishList = checkEmptyWishList(webDriver);
    searchItem("Fahrenheit 451",webDriver);
    WebElement addToWishListButton = webDriver.findElement(By.xpath("//input[@class='button-2 add-to-wishlist-button']"));
    addToWishListButton.click();
    webDriver.get(baseUrl);
    
    boolean addedElement = !checkEmptyWishList(webDriver);
    return emptyWishList && addedElement;
  }
  
  
  public static boolean TC4(){
    boolean result = false;
    String baseUrl = "http://demo.nopcommerce.com";
    
    boolean emptyShoppingCartList = checkEmptyShoppingCart(webDriver);
    
    WebElement wishListLink = webDriver.findElement(By.xpath("//a[@class='ico-wishlist']"));
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
    WebElement shoppingCartLink = webDriver.findElement(By.xpath("//li[@id='topcartlink']"));
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
    
    webDriver.findElement(By.xpath("//li[@id='topcartlink']")).click();
    
    webDriver.findElement(By.className("qty-input")).clear();
    webDriver.findElement(By.className("qty-input")).sendKeys("0");
    webDriver.findElement(By.className("update-cart-button")).click();
    
    String expectedMessage = "Your Shopping Cart is empty!";
    String actualMessage = webDriver.findElement(By.className("no-data")).getText();
    
    boolean emptyCart = (actualTitle.contentEquals(expectedTitle))? true : false;
    
    return correctTitle && emptyCart;
  }
  
  public static boolean TC6(){
    webDriver.close();
    return true;
  }
  
  public static boolean TC7(){
    String baseUrl = "http://demo.nopcommerce.com/";
    boolean result = true;
    
    webDriver = new ChromeDriver();
    webDriver.get(baseUrl);
    
    boolean emptyWishList = checkEmptyWishList(webDriver);
    String csvFile = "/Users/rapuc/Downloads/Parametros.csv";
    BufferedReader br = null;
    String line = "";
    String cvsSplitBy = ","
      
      try {
      br = new BufferedReader(new FileReader(csvFile));
      while ((line = br.readLine()) != null) {
        
        // use comma as separator
        String[] data = line.split(cvsSplitBy);
        searchItem(data[0]);
        WebElement addToWishListButton = webDriver.findElement(By.xpath("//input[@class='button-2 add-to-wishlist-button']"));
        addToWishListButton.click();
        WebElement wishListLink = webDriver.findElement(By.xpath("//a[@class='ico-wishlist']"));
        wishListLink.click();
            webDriver.findElement(By.className("qty-input")).clear();
            webDriver.findElement(By.className("qty-input")).sendKeys(data[1]);
            <span class="product-subtotal">$27.00</span>
        
        
      }
      
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (br != null) {
        try {
          br.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    WebElement busqueda = webDriver.findElement(By.id("small-searchterms"));
    busqueda
      
      
      webDriver.close();
    return true;
  }
  
  public static void searchItem(String searchTerm, WebDriver webDriver){
    WebElement searchBarElement = webDriver.findElement(By.id("small-searchterms"));
    searchBarElement.sendKeys(searchTerm);
    WebElement searchButton = webDriver.findElement(By.xpath("//input[@class='button-1 search-box-button']"));
    searchButton.click();
  }
  
  public static boolean checkEmptyWishList(WebDriver webDriver){
    WebElement wishListLink = webDriver.findElement(By.xpath("//a[@class='ico-wishlist']"));
    wishListLink.click();
    WebElement emptyMessage = webDriver.findElement(By.xpath("//div[@class='no-data']"));
    if(emptyMessage != null){
      if(emptyMessage.getText().equals("The wishlist is empty!")){
        System.out.println("La wishlist está vacía");
        return true;
      }
    }else{
      wishListLink = webDriver.findElement(By.xpath("//a[@class='ico-wishlist']"));
      wishListLink.click();
      WebElement table  = webDriver.findElement(By.xpath("//table[@class='cart']"));
      if(table != null){
        System.out.println("Se agregó el elemento a la wishlist");
      } else {
        System.out.println("La wishlist está vacía");
        return true;
      } 
      
    }
    return false;
  }
  
  public static boolean checkEmptyShoppingCart(WebDriver webDriver){
    WebElement shoppingCartLink = webDriver.findElement(By.id("topcartlink"));
    shoppingCartLink.click();
    WebElement emptyMessage = webDriver.findElement(By.xpath("//div[@class='no-data']"));
    if(emptyMessage != null){
      if(emptyMessage.getText().equals("Your Shopping Cart is empty!")){
        System.out.println("El Shopping Cart está vacío");
        return true;
      }
    }else{
      
      WebElement table = webDriver.findElement(By.xpath("//table[@class='cart']"));
      if(table != null){
        System.out.println("Se agregó el elemento al Shopping Cart");
      } else {
        System.out.println("El Shopping Cart cart está vacío");
        return true;
      } 
    }
    return false;
  }
  
}