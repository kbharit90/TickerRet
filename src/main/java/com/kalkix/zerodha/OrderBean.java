package com.kalkix.zerodha;

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
    private String exchange;
    private String orderType;
    private String parentOrderId;
    private String validity;
    private String product;
    private String tag;
    private String tradingSymbol;
    private String transactionType;
    private double price;
    private double squareOff;
    private double stopLoss;
    private double trailingStopLoss;
    private double triggerPrice;
    private int quantity;
    private int disclosedQuantity;

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

    public String getParentOrderId() {
        return parentOrderId;
    }

    public void setParentOrderId(String parentOrderId) {
        this.parentOrderId = parentOrderId;
    }

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

    public double getTriggerPrice() {
        return triggerPrice;
    }

    public void setTriggerPrice(double triggerPrice) {
        this.triggerPrice = triggerPrice;
    }

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
                ", parentOrderId='" + parentOrderId + '\'' +
                ", validity='" + validity + '\'' +
                ", product='" + product + '\'' +
                ", tag='" + tag + '\'' +
                ", tradingSymbol='" + tradingSymbol + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", price=" + price +
                ", squareOff=" + squareOff +
                ", stopLoss=" + stopLoss +
                ", trailingStopLoss=" + trailingStopLoss +
                ", triggerPrice=" + triggerPrice +
                ", quantity=" + quantity +
                ", disclosedQuantity=" + disclosedQuantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderBean orderBean = (OrderBean) o;
        return Double.compare(orderBean.price, price) == 0 &&
                Double.compare(orderBean.squareOff, squareOff) == 0 &&
                Double.compare(orderBean.stopLoss, stopLoss) == 0 &&
                Double.compare(orderBean.trailingStopLoss, trailingStopLoss) == 0 &&
                Double.compare(orderBean.triggerPrice, triggerPrice) == 0 &&
                quantity == orderBean.quantity &&
                disclosedQuantity == orderBean.disclosedQuantity &&
                Objects.equals(exchange, orderBean.exchange) &&
                Objects.equals(orderType, orderBean.orderType) &&
                Objects.equals(parentOrderId, orderBean.parentOrderId) &&
                Objects.equals(validity, orderBean.validity) &&
                Objects.equals(product, orderBean.product) &&
                Objects.equals(tag, orderBean.tag) &&
                Objects.equals(tradingSymbol, orderBean.tradingSymbol) &&
                Objects.equals(transactionType, orderBean.transactionType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exchange, orderType, parentOrderId, validity, product, tag, tradingSymbol, transactionType, price, squareOff, stopLoss, trailingStopLoss, triggerPrice, quantity, disclosedQuantity);
    }
}
