import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
public class ProyectoQA {
  
  private static WebDriver webDriver;
  
  public static void main(String[] args) {
    
//    System.setProperty("webdriver.chrome.driver","/Users/konradjimenezc/Downloads/chromedriver");
    System.setProperty("webdriver.chrome.driver","/Users/rapuc/Downloads/chromedriver");
    
//  if(TC1())
    // System.out.println("TC1 Aprovado");
    //   else
//      System.err.println("TC1 Fallido");
// 
    // Test 3
    if  (TC3())
      System.out.println("TC3 Aprovado");
    else 
      System.err.println("TC3 Fallido");
    
    if(TC4())
      System.out.println("TC4 Aprovado");
    else
      System.err.println("TC4 Fallido");
    
    
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
    webDriver.close();
    return result;
  }
  
  public static boolean TC3(){
    
    // Verifique que se despliega el mensaje “The wishlist is empty!”
    webDriver = new ChromeDriver();
    String baseUrl = "http://demo.nopcommerce.com/";
    webDriver.get(baseUrl);
    
    boolean emptyWishList = false;
    WebElement wishListLink = webDriver.findElement(By.xpath("//a[@class='ico-wishlist']"));
    wishListLink.click();
    WebElement emptyMessage = webDriver.findElement(By.xpath("//div[@class='no-data']"));
    if(emptyMessage != null){
      if(emptyMessage.getText().equals("The wishlist is empty!")){
        System.out.println("El wishlist está vacío");
        emptyWishList = true;
      }
    }
    
    WebElement searchBarElement = webDriver.findElement(By.id("small-searchterms"));
    searchBarElement.sendKeys("Fahrenheit 451");
    WebElement searchButton = webDriver.findElement(By.xpath("//input[@class='button-1 search-box-button']"));
    searchButton.click();
    
    WebElement withListButton = webDriver.findElement(By.xpath("//input[@class='button-2 add-to-wishlist-button']"));
    withListButton.click();
    
    webDriver.get(baseUrl);
    boolean addedElement = false;
    wishListLink = webDriver.findElement(By.xpath("//a[@class='ico-wishlist']"));
    wishListLink.click();
    WebElement table  = webDriver.findElement(By.xpath("//table[@class='cart']"));
    if(table != null){
      System.out.println("Se agregó el elemento!!");
      addedElement = true;
    } else {
      System.out.println("El wishlist cart está vacío");
    } 
    
    return emptyWishList && addedElement;
  }
  
  
  public static boolean TC4(){
    boolean result = false;
    String baseUrl = "http://demo.nopcommerce.com";
    
    boolean emptyShoppingCartList = false;
    WebElement shoppingCartLink = webDriver.findElement(By.id("topcartlink"));
    shoppingCartLink.click();
    WebElement emptyMessage = webDriver.findElement(By.xpath("//div[@class='no-data']"));
    if(emptyMessage != null){
      if(emptyMessage.getText().equals("Your Shopping Cart is empty!")){
        System.out.println("El Shopping Cart está vacío");
        emptyShoppingCartList = true;
      }
    }
    WebElement wishListLink = webDriver.findElement(By.xpath("//a[@class='ico-wishlist']"));
    wishListLink.click();
    
    webDriver.findElement(By.name("addtocart")).click(); //checkbox
    WebElement addToCartButton = webDriver.findElement(By.name("addtocartbutton"));
    addToCartButton.click();
    boolean addedElement = false;
    WebElement table = webDriver.findElement(By.xpath("//table[@class='cart']"));
    if(table != null){
      System.out.println("Se agregó el elemento!!");
      addedElement = true;
    } else {
      System.out.println("El Shopping Cart cart está vacío");
    } 
    WebElement continueShoppingButton = webDriver.findElement(By.name("continueshopping"));
    continueShoppingButton.click();    
    
    
    return emptyShoppingCartList && addedElement;
  }
  
  
  
}