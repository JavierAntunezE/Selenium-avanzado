package com.selenium.javier;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.selenium.javier.paginas.AgregarProductosCarrito;
import com.selenium.javier.paginas.InicioSesionPage;

public class InicioSesionTest {

	InicioSesionPage inicioSesionPage;
	AgregarProductosCarrito agregarProductosCarrito;
	private WebDriver driver;
	
	@Parameters({ "browser", "gridUrl" })
	@BeforeClass
	public void setup(String browser, String gridUrl) {
		try {
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setBrowserName(browser);
			driver = new RemoteWebDriver(new URL(gridUrl), caps);
			
			driver.get("https://www.saucedemo.com/");
			
			//instancias
			inicioSesionPage = new InicioSesionPage(driver);
			agregarProductosCarrito = new AgregarProductosCarrito(driver);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error setup: " + e.getMessage());
		}

	}

	@Test (priority = 1)
	public void loginTest() {
		try {
			//Logica
			inicioSesionPage.AsignarUsername("standard_user");
			inicioSesionPage.AsignarPassword("secret_sauce");
			
			boolean esExitoso = inicioSesionPage.IniciarSesion();
			
			Assert.assertEquals(esExitoso, true);
			System.out.print("Correcto");

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	
	@Test(priority = 2)
	public void agregarCarrito() {
		try {	
						
			agregarProductosCarrito.AgregarProducto();
			agregarProductosCarrito.IrCarrito();
			boolean esExitoso = agregarProductosCarrito.ValidarPagina();
			
			Assert.assertEquals(esExitoso, true);
			Thread.sleep(2000);
			System.out.print("Correcto aca ya agrego al carrito");

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	
	@AfterClass
	public void teardown() {
		if (driver != null) {
			driver.quit();
			System.out.println("Cierra driver");
		}
	}

}
