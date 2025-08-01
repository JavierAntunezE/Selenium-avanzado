package com.selenium.distribuido;

import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class CarritoComprasSauce {
	private WebDriver driver;
	private WebDriverWait wait;

	@Parameters({ "browser", "gridUrl" })
	@BeforeClass
	public void setup(String browser, String gridUrl) throws Exception {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setBrowserName(browser);
		driver = new RemoteWebDriver(new URL(gridUrl), caps);
		// driver.manage().window().maximize();
		// Puedes agregar Firefox u otro navegador aquí
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		System.out.println("Configurgacion finalizada");
	}

	@Test
	public void loginTest() throws InterruptedException {
		
		try {
			
		
		//driver.get("https://www.saucedemo.com/");

    	// Paso 1: Login
          driver.get("https://www.saucedemo.com/");
          wait.until(ExpectedConditions.titleContains("Swag Labs"));
          Assert.assertEquals(driver.getTitle(), "Swag Labs");
          System.out.println("Pagina de Login");

          wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name"))).sendKeys("standard_user");
          driver.findElement(By.id("password")).sendKeys("secret_sauce");
          driver.findElement(By.id("login-button")).click();

          // Verificar que estamos en la página de inventario
          wait.until(ExpectedConditions.urlContains("inventory"));
          Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
          System.out.println("Página de inventario");

          // Paso 2: Agregar productos
          wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-sauce-labs-backpack"))).click();
          driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
          System.out.println("Se agrega un producto");

          // Verificar el contador del carrito
          WebElement badge = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_badge")));
          Assert.assertEquals(badge.getText(), "2");

          // Paso 3: Ir al carrito
          driver.findElement(By.className("shopping_cart_link")).click();
          wait.until(ExpectedConditions.urlContains("cart"));
          List<WebElement> items = driver.findElements(By.className("cart_item"));
          Assert.assertEquals(items.size(), 2);
          System.out.println("Ir al carrito de compras");

          // Paso 4: Checkout
          driver.findElement(By.id("checkout")).click();
          wait.until(ExpectedConditions.urlContains("checkout-step-one"));
          System.out.println("Checkout");

          // Paso 5: Llenar formulario
          wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name"))).sendKeys("Javier");
          driver.findElement(By.id("last-name")).sendKeys("Antunez");
          driver.findElement(By.id("postal-code")).sendKeys("12345");
          driver.findElement(By.id("continue")).click();
          System.out.println("Llena el formulario");

          wait.until(ExpectedConditions.urlContains("checkout-step-two"));

          // Paso 6: Finalizar compra
          wait.until(ExpectedConditions.elementToBeClickable(By.id("finish"))).click();
          wait.until(ExpectedConditions.urlContains("checkout-complete"));
          System.out.println("Finaliza compras");

          // Paso 7: Verificar confirmación
          WebElement mensaje = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("complete-header")));
          Assert.assertEquals(mensaje.getText().trim(), "Thank you for your order!");
          System.out.println("Verifica confirmacion");
		}catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			Assert.fail(e.getMessage());
		}
	}

	@AfterClass
	public void teardown() {
		if (driver != null) {
			driver.quit();
			Reporter.log("Cierra navegador",true);
		}
	}
}
