package org.google;

import org.openqa.selenium.WebDriver;

public class RegistroCliente {
    public void IngresoCentrodeVentas(WebDriver driver) throws InterruptedException {
        Thread.sleep(2000);
        driver.get("https://prodweb-bimbo-las.mc1.com.br/WTM_Client/Form/Run/Custom_BB_BackOffice_CustomerMasterData?mode=view");
    }
}
