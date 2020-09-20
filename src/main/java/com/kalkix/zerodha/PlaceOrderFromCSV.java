package com.kalkix.zerodha;

import com.opencsv.bean.CsvToBeanBuilder;
import com.zerodhatech.kiteconnect.KiteConnect;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.kiteconnect.utils.Constants;
import com.zerodhatech.models.Order;
import com.zerodhatech.models.OrderParams;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlaceOrderFromCSV implements Runnable{
    KiteConnect kiteConnect;
    public PlaceOrderFromCSV(KiteConnect kiteConnect) {
        this.kiteConnect = kiteConnect;
    }

    @Override
    public void run() {
        placeOrderCSV();

    }

    void placeOrderCSV() {
        //OrderBean orderBean = new OrderBean();
        OrderParams parameters = new OrderParams();

        while (LocalTime.now().isBefore(LocalTime.of(9,20))) {
            try {
                System.out.println("Waiting for CSV file to be placed before 9:20 AM!!!!");
                Thread.sleep(60000);
            }catch (InterruptedException exception) {
                exception.printStackTrace();
            }


        }
        List<OrderBean> beans = loadBeans();

        if(LocalTime.now().isBefore(LocalTime.of(9,25))) {
            for(OrderBean orderBean:beans) {
                try {
                    parameters.exchange = orderBean.getExchange();
                    parameters.orderType = orderBean.getOrderType();
                    parameters.validity = orderBean.getValidity();
                    parameters.product = orderBean.getProduct();
                    parameters.tag = orderBean.getTag();
                    parameters.tradingsymbol = orderBean.getTradingSymbol();
                    parameters.transactionType = orderBean.getTransactionType();
                    parameters.price = orderBean.getPrice();
                    parameters.squareoff = orderBean.getPrice() + orderBean.getSquareOff();
                    parameters.stoploss = orderBean.getPrice() - orderBean.getStopLoss();
                    parameters.trailingStoploss = orderBean.getTrailingStopLoss();
                    parameters.quantity = orderBean.getQuantity();

                    Order order = kiteConnect.placeOrder(parameters, orderBean.getVariety());
                    System.out.println("Order placed for : "+ orderBean.getTradingSymbol());
                    System.out.println("Order id: "+ order.orderId);


                } catch (KiteException | IOException exception) {
                    exception.printStackTrace();
                }
            }

        } else {
            System.out.println("Time has passed. Cannot place any order after 9:25 PM");
        }
    }

    private List<OrderBean> loadBeans() {
        File folder = new File("files/");
        File [] listOfFiles = folder.listFiles();
        List<OrderBean> beans = new ArrayList();
        try{
            for(File file: listOfFiles) {
                if (file.getName().startsWith("Order")) {
                    beans = new CsvToBeanBuilder(new FileReader(file)).withType(OrderBean.class).build().parse();
                }
            }
        } catch (FileNotFoundException exception) {
            System.out.println("It seems No File is placed for Order at 9:20. No orders placed!!!!!");
        }

        return beans;
    }
}

//DEBUG
// System.out.println("Exchange : " +orderBean.getExchange());;
//         System.out.println("Order Type : "+orderBean.getOrderType());
//         System.out.println("Validity : "+orderBean.getValidity());
//         System.out.println("Product: "+orderBean.getProduct());
//         System.out.println("Tag Used: "+orderBean.getTag());
//         System.out.println("Trading Symbol: " +orderBean.getTradingSymbol());
//         System.out.println("Transaction Type :"+orderBean.getTransactionType());
//         System.out.println("Order Price : "+orderBean.getPrice());
//         System.out.println("Square off/target : "+(orderBean.getPrice() + orderBean.getSquareOff()));
//         System.out.println("Stop-loss :" + (orderBean.getPrice() - orderBean.getStopLoss()));
//         System.out.println("Trailing stop loss: "+orderBean.getTrailingStopLoss());
//         System.out.println("Quantity : "+orderBean.getQuantity());