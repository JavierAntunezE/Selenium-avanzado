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

import com.selenium.javier.paginas.InicioSesionPage;

public class InicioSesionTest {

	InicioSesionPage inicioSesionPage;
	private WebDriver driver;
	
	@Parameters({ "browser", "gridUrl" })
	@BeforeClass
	public void setup(String browser, String gridUrl) {
		try {
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setBrowserName(browser);
			driver = new RemoteWebDriver(new URL(gridUrl), caps);
			
			driver.get("https://www.saucedemo.com/");
			
			inicioSesionPage = new InicioSesionPage(driver);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error setup: " + e.getMessage());
		}

	}

	@Test
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
	
	@AfterClass
	public void teardown() {
		if (driver != null) {
			driver.quit();
			System.out.println("Cierra driver");
		}
	}

}
