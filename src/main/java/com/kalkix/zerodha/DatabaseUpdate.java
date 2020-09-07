package com.kalkix.zerodha;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zerodhatech.models.Tick;
import org.postgresql.util.PGobject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Properties;

public class DatabaseUpdate implements Runnable {
    @Override
    public void run() {
        try {
            updateInDatabase();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void updateInDatabase() throws SQLException {
        ArrayList<Tick> dbTicks = new ArrayList<>();
        String url = "jdbc:postgresql://localhost/tickdb";
        Properties props = new Properties();
        props.setProperty("user","postgres");
        props.setProperty("password","postgres");
        //props.setProperty("ssl","true");

        //Passing json as string
        PGobject jsonObject = new PGobject();
        jsonObject.setType("json");

        //Convert tick data to json with Jackson Library
        ObjectMapper obj = new ObjectMapper();

        //Creating database connection
        Connection conn = DriverManager.getConnection(url, props);
        String query = "Update excel_ticks set info=? where instrument_token=?";

        //Passing query to prepared statement
        PreparedStatement ps = conn.prepareStatement(query);

        while (LocalTime.now().isBefore(LocalTime.of(16,05))) {
            System.out.println("Updating database");
            dbTicks = TickerImpl.ticks;
            if (!dbTicks.isEmpty()) {

                //Passing every tick to db with the help of instrument token
                dbTicks.stream().forEach(t->{
                    try {
                        jsonObject.setValue(obj.writeValueAsString(t));
                        ps.setObject(1,jsonObject);
                        ps.setLong(2,t.getInstrumentToken());
                        ps.executeUpdate();

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                });
            }
        }

        ps.close();
        conn.close();



    }
}
