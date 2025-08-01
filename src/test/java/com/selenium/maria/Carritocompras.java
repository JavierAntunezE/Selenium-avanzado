package com.selenium.maria;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.internal.WebElementToJsonConverter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Carritocompras {
	private WebDriver driver;
	private WebDriverWait wait;

    @Parameters({"browser", "gridUrl"})
    @BeforeClass
    public void setup(String browser, String gridUrl) throws Exception {
    	DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName(browser);
        driver = new RemoteWebDriver(new URL(gridUrl), caps);
        //driver.manage().window().maximize();
        // Puedes agregar Firefox u otro navegador aquí
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void loginTest() throws InterruptedException {
    	  driver.get("https://www.saucedemo.com/");

    	// Paso 1: Login
   WebElement usuario =  driver.findElement(By.id("id=user-name"));
   usuario.sendKeys("standard_user");
   
   WebElement contraseña =  driver.findElement(By.id("id=password"));
   contraseña.sendKeys("secrete_souce");
   
   WebElement login =  driver.findElement(By.id("id=login-button"));
   login.click();

          // Verificar que estamos en la página de inventario
   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='header_container']/div/div[2]/div")));
   System.out.println("Pagina de productos");
         

          // Paso 2: Agregar productos
   
   WebElement producto1 = driver.findElement(By.xpath("(//div[@class='product-image-wrapper'])[1]"));
   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//a[contains(text(),'Add to cart')]")));
   producto1.findElement(By.xpath(".//a[contains(text(),'Add to cart')]")).click();
   System.out.println("Producto 1 agregado.");
   
   
   
   
   
   WebElement producto2 = driver.findElement(By.xpath("(//div[@class='product-image-wrapper'])[2]"));
   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//a[contains(text(),'Add to cart')]")));
   producto2.findElement(By.xpath(".//a[contains(text(),'Add to cart')]")).click();
   System.out.println("Producto 2 agregado.");
   //
          // Verificar el contador del carrito
         

          // Paso 3: Ir al carrito
          

          // Paso 4: Checkout
         

          // Paso 5: Llenar formulario
          

          // Paso 6: Finalizar compra
         
          // Paso 7: Verificar confirmación
    	  
    	  Thread.sleep(1000);
        
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
