package org.bimbo;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.excel.AgenciaMapping;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AsignarCliente {
    private static final AgenciaMapping agenciaMapping = new AgenciaMapping();

    public void AsignacionCliente(XSSFRow row, WebDriver driver) throws InterruptedException {
        // Insertando valores de Agencia, Codigo, Ruta Preventa, Status
        boolean estadoActivo = false;
        XSSFCell Agencia = row.getCell(1);
        XSSFCell Codigo = row.getCell(2);
        XSSFCell RutaPreventa = row.getCell(3);
        XSSFCell EstadoAnterior = row.createCell(4);
        XSSFCell EstadoActual = row.createCell(5);

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
        Thread.sleep(1500);

        // Centro de Ventas
        WebElement comboBox = driver.findElement(By.xpath("//*[@id=\"_row2\"]/div/div/div[5]/div/div/input"));
        comboBox.click();
        wait.until(ExpectedConditions.attributeContains(comboBox, "class", "active"));

        WebElement optionElement = driver.findElement(By.xpath("//li/span[text()='" + agenciaMapping.obtenerAgenciaSeleccionada(Agencia.getStringCellValue()) + "']"));
        Thread.sleep(1000);
        optionElement.click();
        Thread.sleep(2000);

        // Clic al cliente
        WebElement divElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[data-bind='visible: matched, click: $parent.rowClick, css: { active: selected() }']")));
        divElement.click();
        Thread.sleep(4000);

        //Agregar Ruta
        WebElement inputElementRuta = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/section/div[1]/div/form/div/div/div[2]/div/div/div/div/div[66]/div/div/div/div/div[5]/div/div/div[2]/div/div/input")));

        try {
            WebElement Ruta_buscada = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/section/div[1]/div/form/div/div/div[2]/div/div/div/div/div[66]/div/div/div/div/div[5]/div/div/div[2]/div/div/div/i")));
            Ruta_buscada.click();
        } catch (TimeoutException e) {
            System.out.println("No se encontró la ruta en el tiempo especificado.");
        }

        inputElementRuta.clear();
        inputElementRuta.sendKeys(RutaPreventa.getStringCellValue());
        inputElementRuta.sendKeys(Keys.ENTER);
        Thread.sleep(1000);

        //Estado de Ruta
        WebElement interruptor = driver.findElement(By.xpath("/html/body/div[1]/div/section/div[1]/div/form/div/div/div[2]/div/div/div/div/div[66]/div/div/div/div/div[5]/div/div/div[5]/div/div/div[6]/div/div/label/span"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String leftValue = (String) js.executeScript("return window.getComputedStyle(arguments[0], ':after').left;", interruptor);
        if ("24px".equals(leftValue)) {
            EstadoAnterior.setCellValue("Ruta Activa");
            EstadoActual.setCellValue("Ruta Activa");
            estadoActivo = true;
        } else {
            EstadoAnterior.setCellValue("Ruta No Activa");
            System.out.println("Ruta No Activa");
        }
        //Agregar de Ruta
        if (!estadoActivo) {
            WebElement buttonEditar = driver.findElement(By.id("btn_edit_detail"));
            buttonEditar.click();
            Thread.sleep(1000);
            WebElement modificarRuta = driver.findElement(By.xpath("/html/body/div[1]/div/section/div[1]/div/form/div/div/div[2]/div/div/div/div/div[66]/div/div/div/div/div[5]/div/div/div[5]/div/div/div[6]/div/div/label"));
            modificarRuta.click();
            Thread.sleep(1000);
            WebElement buttonGuardar = driver.findElement(By.id("btnSaveCustomer"));
            buttonGuardar.click();
            Thread.sleep(5000);
            EstadoActual.setCellValue("Ruta Agregada");
        }
    }

    private void clickElementWithJS(WebDriver driver, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }
}
