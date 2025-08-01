package com.selenium.javier.paginas;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InicioSesionPage {

	WebDriver driver_ = null;
	private WebDriverWait wait;

	public InicioSesionPage(WebDriver driver) {
		this.driver_ = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		System.out.println("Se construye objeto");
	}
	
	
	public void AsignarUsername(String username) {
		if(username == "")
			System.out.println("Username viene vacio");
			
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name"))).sendKeys(username);
		System.out.println("Username: "+ username);
	}
	
	public void AsignarPassword(String password) {
		//Cumpla con los requitos de la contraseña
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys(password);
		System.out.println("Password: "+ password);
	}
	
	public Boolean IniciarSesion() {
		//Validar que no esten vacioes usuario ycontraseña
		
		driver_.findElement(By.id("login-button")).click();
		System.out.println("Iniciando sesion...: ");
		return true;
	}
	
	public boolean RedirigirACode() {
		//Buscar elemento
		//Validar que sea clickeable
		//Dar clic
		return true;
	}
	

}
