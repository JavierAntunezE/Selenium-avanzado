package com.selenium.javier.paginas;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AgregarProductosCarrito {
	WebDriver driver_ = null;
	private WebDriverWait wait;

	public AgregarProductosCarrito(WebDriver driver) {
		this.driver_ = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		System.out.println("Se construye constructor");
	}
	
	public Boolean AgregarProducto() {	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='inventory_container']/div/div/div[2]/div[2]/button']"))).click();
		System.out.println("Se agrega producto");
		return true;
		
	}
	
	public Boolean IrCarrito() {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-to-cart-sauce-labs-backpack"))).click();
		System.out.println("ingresa al carrito");
		return true;
	}
	
	public Boolean ValidarPagina() {
		WebElement icono = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"header_container\"]/div[2]/span")));
		Assert.assertEquals(icono.getText(), "Your Cart");  //extrae el texto y compara con el que le mando en codigo
		System.out.println("Iniciando sesion...: ");
		return true;
	}
}
