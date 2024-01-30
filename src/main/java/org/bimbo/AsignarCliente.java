package org.bimbo;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.excel.AgenciaMapping;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AsignarCliente {
    private static final AgenciaMapping agenciaMapping = new AgenciaMapping();
    public void AsignacionCliente(XSSFRow row, WebDriver driver) throws InterruptedException {
        // Insertando valores de Agencia, Codigo, Ruta Preventa, Status
        XSSFCell Agencia = row.getCell(1);
        XSSFCell Codigo = row.getCell(2);
        XSSFCell RutaPreventa = row.getCell(4);

        // Funcionalidad del método
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement modalTrigger = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='collection_Customers']/div[2]/div[1]/a/i")));
        clickElementWithJS(driver, modalTrigger);

        WebElement inputElementCodigoCliente = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtcIDCustomerFilter")));
        inputElementCodigoCliente.clear();
        inputElementCodigoCliente.sendKeys(Codigo.getStringCellValue());
        Thread.sleep(2000);

        WebElement buttonFiltrar = driver.findElement(By.xpath("//*[@id=\"collection_Customers-modal\"]/div[2]/button[1]"));
        buttonFiltrar.click();
        Thread.sleep(2000);

        // Centro de Ventas
        WebElement comboBox = driver.findElement(By.xpath("//*[@id=\"_row2\"]/div/div/div[5]/div/div/input"));
        comboBox.click();
        wait.until(ExpectedConditions.attributeContains(comboBox, "class", "active"));

        WebElement optionElement = driver.findElement(By.xpath("//li/span[text()='" + agenciaMapping.obtenerAgenciaSeleccionada(Agencia.getStringCellValue()) + "']"));
        optionElement.click();

        // Clic al cliente
        WebElement divElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[data-bind='visible: matched, click: $parent.rowClick, css: { active: selected() }']")));
        divElement.click();
        Thread.sleep(3000);
    }

    private void clickElementWithJS(WebDriver driver, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }
}
