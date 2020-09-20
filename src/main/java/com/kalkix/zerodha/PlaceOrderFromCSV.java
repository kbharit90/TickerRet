package com.kalkix.zerodha;

import com.zerodhatech.kiteconnect.KiteConnect;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.kiteconnect.utils.Constants;
import com.zerodhatech.models.Order;
import com.zerodhatech.models.OrderParams;

import java.io.IOException;

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
        OrderBean orderBean = new OrderBean();
        OrderParams parameters = new OrderParams();
        //TODO : Place order
        //Have to set loop for number of orders in CSV file.
        //Have to populate bean class with values of csv file
        //Get the order id for each order and show in console
        //Change the order constant accordingly in below code
        //At last call this function as another thread



        try {
            parameters.quantity = orderBean.getQuantity();
            parameters.orderType = orderBean.getOrderType();
            parameters.tradingsymbol = orderBean.getTradingSymbol();
            parameters.product = orderBean.getProduct();
            parameters.exchange = orderBean.getExchange();
            parameters.transactionType = orderBean.getTransactionType();
            parameters.validity = orderBean.getValidity();
            parameters.price = orderBean.getPrice();
            parameters.triggerPrice = orderBean.getTriggerPrice();
            parameters.tag = orderBean.getTag();

            Order order = kiteConnect.placeOrder(parameters, Constants.VARIETY_REGULAR);

        } catch (KiteException | IOException exception) {
            exception.printStackTrace();
        }



    }
}
