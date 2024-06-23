package org.methods;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.google.Login;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.InputStream;
import java.util.Properties;

public class methodsFunctionalityBankery {
    private static final agencyMapping AGENCY_MAPPING = new agencyMapping();

    private static final String PROPERTIES_FILE = "config.properties";
    private static final Properties properties;

    static String agenciaAnterior = "";
    static String rutaAnterior = "";

    static {
        properties = new Properties();
        try (InputStream input = Login.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            properties.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ClientAssignment(XSSFRow row, WebDriver driver) throws InterruptedException {

        // Insertando valores de Agencia, Código, Ruta Preventa, Status
        boolean estadoActivo = false;
        XSSFCell Agencia = row.getCell(1);
        XSSFCell Codigo = row.getCell(2);
        XSSFCell RutaPreventa = row.getCell(3);
        XSSFCell EstadoAnterior = row.createCell(4);
        XSSFCell EstadoActual = row.createCell(5);

        // Ingreso del Código de Cliente
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement modalTrigger = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(properties.getProperty("xpath-modalTrigger-mc1"))));
        clickElementWithJS(driver, modalTrigger);

        WebElement inputElementCodigoCliente = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtcIDCustomerFilter")));
        inputElementCodigoCliente.clear();
        inputElementCodigoCliente.sendKeys(Codigo.getStringCellValue());
        Thread.sleep(500);

        WebElement buttonFiltrar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(properties.getProperty("xpath-buttonFiltrar-mc1"))));
        buttonFiltrar.click();
        Thread.sleep(500);

        // Listado de las Agencias

        if (!agenciaAnterior.equals(Agencia.getStringCellValue())) {
            WebElement comboBoxAgencia = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(properties.getProperty("xpath-comboBoxAgencia-mc1"))));
            comboBoxAgencia.click();
            wait.until(ExpectedConditions.attributeContains(comboBoxAgencia, "class", "active"));

            WebElement optionElement = wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//li/span[text()='" + AGENCY_MAPPING.getSelectedAgency(Agencia.getStringCellValue()) + "']"))));
            Thread.sleep(500);
            optionElement.click();
            Thread.sleep(500);
        }
        agenciaAnterior = Agencia.getStringCellValue();

        // Clic al cliente
        boolean clienteClickeado = false;
        try {
            WebElement ClientClickable = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(properties.getProperty("xpath-ClientClickable-mc1"))));
            ClientClickable.click();
            Thread.sleep(1000);
            clienteClickeado = true;
        } catch (TimeoutException e) {
            EstadoAnterior.setCellValue("Ruta no encontrada");
            EstadoActual.setCellValue("Ruta no encontrada");

        }
        if (clienteClickeado) {
            //Agregar Ruta
            if (!rutaAnterior.equals(RutaPreventa.getStringCellValue())) {
                WebElement inputElementRuta = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(properties.getProperty("xpath-inputElementRuta-mc1"))));

                try {
                    WebElement RutaBuscada = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(properties.getProperty("xpath-RutaBuscada-mc1"))));
                    RutaBuscada.click();
                } catch (TimeoutException e) {
                    System.out.println("No se encontró la ruta en el tiempo especificado.");
                }

                inputElementRuta.clear();
                inputElementRuta.sendKeys(RutaPreventa.getStringCellValue());
                inputElementRuta.sendKeys(Keys.ENTER);
                Thread.sleep(500);
            }
            rutaAnterior = RutaPreventa.getStringCellValue();

            //Estado de Ruta
            WebElement interruptor = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(properties.getProperty("xpath-interruptor-mc1"))));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String leftValue = (String) js.executeScript("return window.getComputedStyle(arguments[0], ':after').left;", interruptor);
            if ("24px".equals(leftValue)) {
                EstadoAnterior.setCellValue("Ruta Asignada");
                EstadoActual.setCellValue("Ruta Asignada");
                estadoActivo = true;
                System.out.println("La ruta se encuentra Asignada");
            } else {
                EstadoAnterior.setCellValue("Ruta Desasignada");
            }

            //Agregar Ruta al Cliente
            if (!estadoActivo) {
                WebElement buttonEditar = driver.findElement(By.id("btn_edit_detail"));
                buttonEditar.click();
                Thread.sleep(500);
                WebElement modificarRuta = driver.findElement(By.xpath(properties.getProperty("xpath-modificarRuta-mc1")));
                modificarRuta.click();
                Thread.sleep(500);
                WebElement buttonGuardar = driver.findElement(By.id("btnSaveCustomer"));
                buttonGuardar.click();
                Thread.sleep(4000);
                EstadoActual.setCellValue("Ruta Asignada");
                System.out.println("La ruta ha sido Asignada");
            }
        } else {
            System.out.println("Se encontró un error al hacer clic en el cliente");
        }
    }

    public void clientUnassignment(XSSFRow row, WebDriver driver) throws InterruptedException {

        // Insertando valores de Agencia, Código, Ruta Preventa, Status
        boolean estadoDesactivo = false;
        XSSFCell Agencia = row.getCell(1);
        XSSFCell Codigo = row.getCell(2);
        XSSFCell RutaPreventa = row.getCell(3);
        XSSFCell EstadoAnterior = row.createCell(4);
        XSSFCell EstadoActual = row.createCell(5);

        // Ingreso del Código de Cliente
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement modalTrigger = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(properties.getProperty("xpath-modalTrigger-mc1"))));
        clickElementWithJS(driver, modalTrigger);

        WebElement inputElementCodigoCliente = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtcIDCustomerFilter")));
        inputElementCodigoCliente.clear();
        inputElementCodigoCliente.sendKeys(Codigo.getStringCellValue());
        Thread.sleep(500);

        WebElement buttonFiltrar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(properties.getProperty("xpath-buttonFiltrar-mc1"))));
        buttonFiltrar.click();
        Thread.sleep(500);

        // Listado de las Agencias

        if (!agenciaAnterior.equals(Agencia.getStringCellValue())) {
            WebElement comboBoxAgencia = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(properties.getProperty("xpath-comboBoxAgencia-mc1"))));
            comboBoxAgencia.click();
            wait.until(ExpectedConditions.attributeContains(comboBoxAgencia, "class", "active"));

            WebElement optionElement = wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//li/span[text()='" + AGENCY_MAPPING.getSelectedAgency(Agencia.getStringCellValue()) + "']"))));
            Thread.sleep(500);
            optionElement.click();
            Thread.sleep(500);
        }
        agenciaAnterior = Agencia.getStringCellValue();

        // Clic al cliente
        boolean clienteClickeado = false;
        try {
            WebElement ClientClickable = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(properties.getProperty("xpath-ClientClickable-mc1"))));
            ClientClickable.click();
            Thread.sleep(1000);
            clienteClickeado = true;
        } catch (TimeoutException e) {
            EstadoAnterior.setCellValue("Ruta no encontrada");
            EstadoActual.setCellValue("Ruta no encontrada");

        }
        if (clienteClickeado) {
            //Retirar Ruta
            if (!rutaAnterior.equals(RutaPreventa.getStringCellValue())) {
                WebElement inputElementRuta = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(properties.getProperty("xpath-inputElementRuta-mc1"))));

                try {
                    WebElement RutaBuscada = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(properties.getProperty("xpath-RutaBuscada-mc1"))));
                    RutaBuscada.click();
                } catch (TimeoutException e) {
                    System.out.println("No se encontró la ruta en el tiempo especificado.");
                }

                inputElementRuta.clear();
                inputElementRuta.sendKeys(RutaPreventa.getStringCellValue());
                inputElementRuta.sendKeys(Keys.ENTER);
                Thread.sleep(500);
            }
            rutaAnterior = RutaPreventa.getStringCellValue();

            //Estado de Ruta
            WebElement interruptor = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(properties.getProperty("xpath-interruptor-mc1"))));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String leftValue = (String) js.executeScript("return window.getComputedStyle(arguments[0], ':after').left;", interruptor);
            if (!"24px".equals(leftValue)) {
                EstadoAnterior.setCellValue("Ruta Desasignada");
                EstadoActual.setCellValue("Ruta Desasignada");
                estadoDesactivo = true;
                System.out.println("La ruta se encuentra Desasignada");
            } else {
                EstadoAnterior.setCellValue("Ruta Asignada");
            }

            //Retirar de Ruta
            if (!estadoDesactivo) {
                WebElement buttonEditar = driver.findElement(By.id("btn_edit_detail"));
                buttonEditar.click();
                Thread.sleep(500);
                WebElement modificarRuta = driver.findElement(By.xpath(properties.getProperty("xpath-modificarRuta-mc1")));
                modificarRuta.click();
                Thread.sleep(500);
                WebElement buttonGuardar = driver.findElement(By.id("btnSaveCustomer"));
                buttonGuardar.click();
                Thread.sleep(4000);
                EstadoActual.setCellValue("Ruta Desasignada");
                System.out.println("La ruta ha sido Desasignada");
            }
        } else {
            System.out.println("Se encontró un error al hacer clic en el cliente");
        }
    }

    private void clickElementWithJS(WebDriver driver, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }
}