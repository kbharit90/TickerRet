package com.kalkix.zerodha;

import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.models.Order;
import com.zerodhatech.models.Tick;
import com.zerodhatech.ticker.*;


import java.util.*;


public class TickerImpl {
    public static ArrayList<Tick> ticks = new ArrayList<>();


    public static void tickerImplMethod(KiteTicker tickerProvider, ArrayList<Long> tokens) throws KiteException {
        tickerProvider.connect();
        tickerProvider.setTryReconnection(true);
        tickerProvider.setMaximumRetries(25);
        tickerProvider.setMaximumRetryInterval(10);
        boolean isConnected = tickerProvider.isConnectionOpen();
        System.out.println("Connection Established: "+isConnected);
        tickerProvider.setMode(tokens, KiteTicker.modeFull);

        tickerProvider.setOnConnectedListener(new OnConnect() {
            @Override
            public void onConnected() {
                /** Subscribe ticks for token.
                 * By default, all tokens are subscribed for modeQuote.
                 * */
                System.out.println("Inside OnConnected method");
                tickerProvider.subscribe(tokens);

            }
        });

        tickerProvider.setOnDisconnectedListener(new OnDisconnect() {
            @Override
            public void onDisconnected() {
                tickerProvider.connect();
                // your code goes here
            }
        });

        /** Set listener to get order updates.*/
        tickerProvider.setOnOrderUpdateListener(new OnOrderUpdate() {
            @Override
            public void onOrderUpdate(Order order) {
                System.out.println("order update "+order.orderId);
            }
        });

        tickerProvider.setOnTickerArrivalListener(new OnTicks() {
            @Override
            public void onTicks(ArrayList<Tick> ticks) {
                System.out.println("ticks size "+ticks.size());
                if(ticks.size() > 0) {
                    TickerImpl.ticks=ticks;

                }
            }
        });

    }


}
