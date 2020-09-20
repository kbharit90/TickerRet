package com.kalkix.Excel;

import com.kalkix.Controller.RestServices;
import com.kalkix.zerodha.AccessToken;
import com.kalkix.zerodha.DatabaseUpdate;
import com.kalkix.zerodha.PlaceOrderFromCSV;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = RestServices.class)
public class GiveExcelTicks {

    public static void main(String[] args) {
        AccessToken access = new AccessToken();
        access.getAccessToken();

        DatabaseUpdate update = new DatabaseUpdate();
        Thread updateThread = new Thread(update);
        updateThread.start();
        SpringApplication.run(GiveExcelTicks.class, args);
    }

}
