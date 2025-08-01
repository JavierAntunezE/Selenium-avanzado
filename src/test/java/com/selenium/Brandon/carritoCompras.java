package com.selenium.Brandon;

import java.time.Duration;
import java.net.URL;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class carritoCompras {
	private WebDriver driver;
	private WebDriverWait wait;
	private final String BASE_URL = "https://www.saucedemo.com/";

	@BeforeMethod(alwaysRun = true)
	public void setUp() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get(BASE_URL);

	}

	@Test(priority = 1, groups = { "testNewTab", "all" })
	public void testNewTab() {
		  // Paso 1: Login

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name"))).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
		
        try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        // Verificar que estamos en la página de inventario
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_list")));
        if (driver.getCurrentUrl().contains("inventory")) {
            System.out.println("Login exitoso y página de inventario cargada.");
        } else {
            System.out.println("Error al iniciar sesión.");
        }

        // Paso 2: Agregar productos
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();

        // Verificar el contador del carrito
        WebElement contador = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_badge")));
        if (contador.getText().equals("2")) {
            System.out.println("2 productos agregados al carrito.");
        } else {
            System.out.println("Error: Contador del carrito muestra " + contador.getText());
        }

        // Paso 3: Ir al carrito
        driver.findElement(By.className("shopping_cart_link")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cart_item")));
        System.out.println("Entramos al carrito.");

        // Paso 4: Checkout
        driver.findElement(By.id("checkout")).click();
        System.out.println("Checkout iniciado.");

        // Paso 5: Llenar formulario
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name"))).sendKeys("Juan");
        driver.findElement(By.id("last-name")).sendKeys("Pérez");
        driver.findElement(By.id("postal-code")).sendKeys("10101");
        driver.findElement(By.id("continue")).click();
        System.out.println("Formulario de enviado correctamente.");

        // Paso 6: Finalizar compra
        wait.until(ExpectedConditions.elementToBeClickable(By.id("finish"))).click();
        System.out.println("Compra finalizada.");

        // Paso 7: Verficar confirmación
        WebElement confirmacion = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("complete-header")));
      

		
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {

		driver.quit();
	}

}

