package com.kalkix.zerodha;


import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.zerodhatech.kiteconnect.KiteConnect;
import com.zerodhatech.kiteconnect.kitehttp.SessionExpiryHook;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.models.Instrument;
import com.zerodhatech.models.Order;
import com.zerodhatech.models.Tick;
import com.zerodhatech.models.User;
import com.zerodhatech.ticker.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class AccessToken {
    private String apiKey;
    private String apiSecret;
    private String userID;
    private String requestToken;
    private String accessToken;
    private KiteTicker tickerProvider = null;

    public void getAccessToken() {
        Properties prop = null;
        User user = null;
        KiteConnect kiteSdk = null;
        try {
            prop = readPropertiesFile("properties/credentials.properties");

            apiKey = prop.getProperty("apiKey");
            userID = prop.getProperty("userID");
            apiSecret = prop.getProperty("apiSecret");
            requestToken = prop.getProperty("requestToken");
            kiteSdk = new KiteConnect(apiKey);

            // Set userId.
            kiteSdk.setUserId(userID);
            String url = kiteSdk.getLoginURL();

            user =  kiteSdk.generateSession(requestToken, apiSecret);
            System.out.println("Access Token:"+user.accessToken);
            prop.setProperty("accessToken", user.accessToken);
            kiteSdk.setAccessToken(user.accessToken);
            kiteSdk.setPublicToken(user.publicToken);
            kiteSdk.setSessionExpiryHook(new SessionExpiryHook() {
                @Override
                public void sessionExpired() {
                    System.out.println("Session Expired");
                }
            });

            //Now get the token list for ticking
            getTokenList(kiteSdk);


        } catch (KiteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Calling Ticker


    }

    //Reading Properties
    public static Properties readPropertiesFile(String fileName) throws IOException {
        FileInputStream fis = null;
        Properties prop = null;
        try {
            fis = new FileInputStream(fileName);
            prop = new Properties();
            prop.load(fis);
        } catch(FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        } finally {
            fis.close();
        }
        return prop;
    }

    public void getTokenList(KiteConnect listConnect) {
        try {
            List<Instrument> instruments = listConnect.getInstruments("NSE");
            ArrayList<Long> tokens = new ArrayList<>();
            //Read CSV file data
            List<String[]> csvFileData = readAll();
            List<String> symbol500 = new ArrayList<>();

            //Adding nifty 500 symbols from csv file
            for (String s[] : csvFileData) {
                symbol500.add(s[2]);
            }
            FileWriter outputFile = new FileWriter("files/symbolList.csv");
            CSVWriter writer = new CSVWriter(outputFile);
            List<String[]> data = new ArrayList<String[]>();
            data.add(new String[] {"Trading_Symbol","Instrument_Token"});
            //Going through all the instruments of NSE from Zerodha
            for(Instrument i : instruments) {
                if(symbol500.contains(i.tradingsymbol)) {
                    System.out.println("Filetered symbol"+i.tradingsymbol);
                    tokens.add(i.instrument_token);
                    data.add(new String[]{i.tradingsymbol, i.instrument_token+""});
                }
            }
            //Writing data to symbolList for using in Excel
            writer.writeAll(data);
            writer.close();
            outputFile.close();

            tickerProvider = new KiteTicker(listConnect.getAccessToken(), listConnect.getApiKey());
            System.out.println("Token to be passed size of it: "+tokens.size());
            TickerImpl.tickerImplMethod(tickerProvider,tokens);


        }catch (KiteException e) {
            e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }



    }

    public List<String[]> readAll() throws Exception{
        CSVReader csvReader = new CSVReader(new FileReader("files/nifty500.csv"));
        List<String[]> list = new ArrayList<>();
        list = csvReader.readAll();
        csvReader.close();
        return list;
    }

}
