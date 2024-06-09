package org.google;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.InputStream;
import java.util.Properties;

public class Login {
    private static final String PROPERTIES_FILE = "config.properties";
    private static final Properties properties;

    static {
        properties = new Properties();
        try (InputStream input = Login.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            properties.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void InicioSesion(WebDriver driver) throws InterruptedException {
        driver.get(properties.getProperty("url-login-mc1"));
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement inputElementDomain = wait.until(ExpectedConditions.elementToBeClickable(By.id("Domain")));
        Thread.sleep(1000);

        // Obtiene la contraseña desde el archivo de propiedades
        String password = properties.getProperty("password-login-mc1");

        // Ingresa la contraseña en el campo
        inputElementDomain.sendKeys(password);
        inputElementDomain.sendKeys(Keys.ENTER);
    }
}
