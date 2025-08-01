package com.selenium.alvaro;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CarritoCompras {

public class CarritoComprasSauce {
	
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
    	  //nuevo comentario
    	          	  
    	  WebElement txt_user = driver.findElement(By.id("user-name"));
    	  txt_user.sendKeys("standard_user");
    	  WebElement txt_pass = driver.findElement(By.id("password"));
    	  txt_pass.sendKeys("secret_sauce");
    	  WebElement btn_login = driver.findElement(By.id("login-button"));
    	  btn_login.click();
    	  
       	  
          // Verificar que estamos en la página de inventario
    	  WebElement TextoIcono = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='header_container']/div/div[2]/div")));
    	  String logo = TextoIcono.getText();
          Assert.assertEquals(logo, "Swag Labs");
          System.out.println("Se valida que exista el icono");

          // Paso 2: Agregar productos
         

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

}
