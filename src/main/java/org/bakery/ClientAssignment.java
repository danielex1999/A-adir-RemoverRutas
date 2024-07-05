package org.bakery;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.excel.fieldGenerationInExcel;
import org.excel.saveWorkbook;
import org.google.clientCategory;
import org.google.loginToSite;
import org.methods.getCurrentTime;
import org.methods.methodsFunctionalityBakery;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class ClientAssignment {
    private static final String PROPERTIES_FILE = "config.properties";
    private static final Properties properties;
    private static final Logger LOGGER = Logger.getLogger(ClientAssignment.class.getName());

    static {
        properties = new Properties();
        try (InputStream input = loginToSite.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            properties.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String filePath = properties.getProperty("excel-path-file") + properties.getProperty("excel-file") + ".xlsx";
        int clienteInicial = Integer.parseInt(properties.getProperty("clienteInicial"));
        int clienteFinal = Integer.parseInt(properties.getProperty("clienteFinal"));

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--user-data-dir=" + properties.getProperty("chrome-options"));
        System.setProperty("webdriver.chrome.driver", properties.getProperty("chromedriver-path"));
        WebDriver driver = new ChromeDriver(options);

        try (FileInputStream fis = new FileInputStream(filePath)) {
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            fieldGenerationInExcel fieldGenerationInExcel = new fieldGenerationInExcel();
            methodsFunctionalityBakery methodsFunctionalityBakery = new methodsFunctionalityBakery();
            saveWorkbook saveWorkbook = new saveWorkbook();
            getCurrentTime getCurrentTime = new getCurrentTime();
            loginToSite loginToSite = new loginToSite();
            clientCategory clientCategory = new clientCategory();

            XSSFSheet sheet = workbook.getSheetAt(0);
            fieldGenerationInExcel.createStatusCells(workbook, sheet);
            loginToSite.LoginToMC1(driver);
            clientCategory.toSalesCenter(driver);

            for (int i = clienteInicial; i <= clienteFinal; i++) {
                XSSFRow row = sheet.getRow(i - 1);
                getCurrentTime.setTime(i);
                methodsFunctionalityBakery.ClientAssignment(workbook, row, driver);
                LOGGER.info("---------------------------------");
                saveWorkbook.saveExcelPerClient(workbook, filePath);
            }

            workbook.close();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        } catch (ThreadDeath td) {
            throw td;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
