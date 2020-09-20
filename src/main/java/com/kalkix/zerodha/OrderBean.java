package com.kalkix.zerodha;

import com.opencsv.bean.CsvBindByName;

import java.util.Objects;

public class OrderBean {
    /**
     * java.lang.Integer	disclosedQuantity
     * Disclosed quantity
     * java.lang.String	exchange
     * Exchange in which instrument is listed (NSE, BSE, NFO, BFO, CDS, MCX).
     * java.lang.String	orderType
     * Order type (LIMIT, SL, SL-M, MARKET).
     * java.lang.String	parentOrderId
     * Parent order id is used to send order modify request.
     * java.lang.Double	price
     * Order Price
     * java.lang.String	product
     * Product code (NRML, MIS, CNC).
     * java.lang.Integer	quantity
     * Order quantity
     * java.lang.Double	squareoff
     * Square off value (only for bracket orders)
     * java.lang.Double	stoploss
     * Stoploss value (only for bracket orders)
     * java.lang.String	tag
     * Tag: field for users to tag orders.
     * java.lang.String	tradingsymbol
     * Tradingsymbol of the instrument (ex.
     * java.lang.Double	trailingStoploss
     * Trailing stoploss value (only for bracket orders)
     * java.lang.String	transactionType
     * Transaction type (BUY or SELL).
     * java.lang.Double	triggerPrice
     * Trigger price
     * java.lang.String	validity
     * Order validity (DAY, IOC).
     */
    @CsvBindByName(column = "&exchange")
    private String exchange;

    @CsvBindByName(column = "&order_type")
    private String orderType;

//    @CsvBindByName()
//    private String parentOrderId;
    @CsvBindByName(column = "&variety")
    private String variety;

    @CsvBindByName(column = "&validity")
    private String validity;

    @CsvBindByName(column = "&product")
    private String product;

    @CsvBindByName(column = "&tag")
    private String tag;

    @CsvBindByName(column = "&tradingsymbol")
    private String tradingSymbol;

    @CsvBindByName(column = "&transaction_type")
    private String transactionType;

    @CsvBindByName(column = "&price")
    private double price;

    @CsvBindByName(column = "&squareoff")
    private double squareOff;

    @CsvBindByName(column = "&stoploss")
    private double stopLoss;

    @CsvBindByName(column = "&trailing_stoploss")
    private double trailingStopLoss;

    //private double triggerPrice;

    @CsvBindByName(column = "&quantity")
    private int quantity;

    private int disclosedQuantity;

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

//    public String getParentOrderId() {
//        return parentOrderId;
//    }
//
//    public void setParentOrderId(String parentOrderId) {
//        this.parentOrderId = parentOrderId;
//    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTradingSymbol() {
        return tradingSymbol;
    }

    public void setTradingSymbol(String tradingSymbol) {
        this.tradingSymbol = tradingSymbol;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSquareOff() {
        return squareOff;
    }

    public void setSquareOff(double squareOff) {
        this.squareOff = squareOff;
    }

    public double getStopLoss() {
        return stopLoss;
    }

    public void setStopLoss(double stopLoss) {
        this.stopLoss = stopLoss;
    }

    public double getTrailingStopLoss() {
        return trailingStopLoss;
    }

    public void setTrailingStopLoss(double trailingStopLoss) {
        this.trailingStopLoss = trailingStopLoss;
    }

//    public double getTriggerPrice() {
//        return triggerPrice;
//    }
//
//    public void setTriggerPrice(double triggerPrice) {
//        this.triggerPrice = triggerPrice;
//    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getDisclosedQuantity() {
        return disclosedQuantity;
    }

    public void setDisclosedQuantity(int disclosedQuantity) {
        this.disclosedQuantity = disclosedQuantity;
    }


    @Override
    public String toString() {
        return "OrderBean{" +
                "exchange='" + exchange + '\'' +
                ", orderType='" + orderType + '\'' +
                ", variety='" + variety + '\'' +
                ", validity='" + validity + '\'' +
                ", product='" + product + '\'' +
                ", tag='" + tag + '\'' +
                ", tradingSymbol='" + tradingSymbol + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", price=" + price +
                ", squareOff=" + squareOff +
                ", stopLoss=" + stopLoss +
                ", trailingStopLoss=" + trailingStopLoss +
                ", quantity=" + quantity +
                ", disclosedQuantity=" + disclosedQuantity +
                '}';
    }
}
