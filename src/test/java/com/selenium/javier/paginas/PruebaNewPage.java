package com.selenium.javier.paginas;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PruebaNewPage {
	WebDriver driver_ = null; //inicializar variables
	private WebDriverWait wait; //inicializar variable para esperas 
	
	//se contruye el constructor
	public PruebaNewPage(WebDriver driver) {
		this.driver_ = driver; //se le asigna a driver el valor de la webdriver
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		System.out.println("Se construye objeto");
	}
	
	//se crea el metodo para ingresar usuario, se le pasa la variable
	public void AsignarUsername(String username) {
		if(username == "")
			System.out.println("Username viene vacio");
		
		//esperar hasta que el elemento sea visible en el DOM, se identifica el elemento y se envia la variable que se le pasa en otra clase el valor
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name"))).sendKeys(username);
		System.out.println("Username: "+ username);
	}
	
	//se crea el metodo para ingresar contraseña, se le pasa la variable
	public void AsignarPassword(String password) {
		//Cumpla con los requitos de la contraseña
		
		//esperar hasta que el elemento sea visible en el DOM, se identifica el elemento y se envia la variable que se le pasa en otra clase el valor
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys(password);
		System.out.println("Password: "+ password);
	}
	
	//metodo para dar click en el boton, retorna true porque este si hace una acción que podemos validar cuando se cumpla
	public Boolean IniciarSesion() {
		//Validar que no esten vacioes usuario ycontraseña
		
		driver_.findElement(By.id("login-button")).click();
		System.out.println("Iniciando sesion...: ");
		return true;
	}
	
	
	//metodo para redirigir a pagina
	public boolean RedirigirACode() {
		//Buscar elemento
		//Validar que sea clickeable
		//Dar clic
		return true;
	}
	
}
