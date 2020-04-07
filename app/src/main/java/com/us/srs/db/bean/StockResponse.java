package com.us.srs.db.bean;

import java.io.Serializable;

public class StockResponse implements Serializable {
    /**
     * symbol : WINS
     * companyName : Wins Finance Holdings Inc.
     * primaryExchange : NASDAQ Capital Market
     * sector : Financial Services
     * calculationPrice : close
     * open : 24.8
     * openTime : 1550845801042
     * close : 25.73
     * closeTime : 1550869200372
     * high : 26.94
     * low : 23.5
     * latestPrice : 25.73
     * latestSource : Close
     * latestTime : February 22, 2019
     * latestUpdate : 1550869200372
     * latestVolume : 8752
     * iexRealtimePrice : null
     * iexRealtimeSize : null
     * iexLastUpdated : null
     * delayedPrice : 25.99
     * delayedPriceTime : 1550869200372
     * extendedPrice : 23.35
     * extendedChange : -2.38
     * extendedChangePercent : -0.0925
     * extendedPriceTime : 1550699968660
     * previousClose : 24.7999
     * change : 0.9301
     * changePercent : 0.0375
     * iexMarketPercent : null
     * iexVolume : null
     * avgTotalVolume : 4256
     * iexBidPrice : null
     * iexBidSize : null
     * iexAskPrice : null
     * iexAskSize : null
     * marketCap : 510422529
     * peRatio : null
     * week52High : 165
     * week52Low : 20.98
     * ytdChange : 0.20545279164775304
     */

    private String symbol;
    private String companyName;
    private String primaryExchange;
    private String sector;
    private String calculationPrice;
    private double open;
    private long openTime;
    private double close;
    private long closeTime;
    private double high;
    private double low;
    private double latestPrice;
    private String latestSource;
    private String latestTime;
    private long latestUpdate;
    private int latestVolume;
    private Object iexRealtimePrice;
    private Object iexRealtimeSize;
    private Object iexLastUpdated;
    private double delayedPrice;
    private long delayedPriceTime;
    private double extendedPrice;
    private double extendedChange;
    private double extendedChangePercent;
    private long extendedPriceTime;
    private double previousClose;
    private double change;
    private double changePercent;
    private Object iexMarketPercent;
    private Object iexVolume;
    private int avgTotalVolume;
    private Object iexBidPrice;
    private Object iexBidSize;
    private Object iexAskPrice;
    private Object iexAskSize;
    private String marketCap;
    private Object peRatio;
    private String week52High;
    private double week52Low;
    private double ytdChange;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPrimaryExchange() {
        return primaryExchange;
    }

    public void setPrimaryExchange(String primaryExchange) {
        this.primaryExchange = primaryExchange;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getCalculationPrice() {
        return calculationPrice;
    }

    public void setCalculationPrice(String calculationPrice) {
        this.calculationPrice = calculationPrice;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public long getOpenTime() {
        return openTime;
    }

    public void setOpenTime(long openTime) {
        this.openTime = openTime;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public long getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(long closeTime) {
        this.closeTime = closeTime;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getLatestPrice() {
        return latestPrice;
    }

    public void setLatestPrice(double latestPrice) {
        this.latestPrice = latestPrice;
    }

    public String getLatestSource() {
        return latestSource;
    }

    public void setLatestSource(String latestSource) {
        this.latestSource = latestSource;
    }

    public String getLatestTime() {
        return latestTime;
    }

    public void setLatestTime(String latestTime) {
        this.latestTime = latestTime;
    }

    public long getLatestUpdate() {
        return latestUpdate;
    }

    public void setLatestUpdate(long latestUpdate) {
        this.latestUpdate = latestUpdate;
    }

    public int getLatestVolume() {
        return latestVolume;
    }

    public void setLatestVolume(int latestVolume) {
        this.latestVolume = latestVolume;
    }

    public Object getIexRealtimePrice() {
        return iexRealtimePrice;
    }

    public void setIexRealtimePrice(Object iexRealtimePrice) {
        this.iexRealtimePrice = iexRealtimePrice;
    }

    public Object getIexRealtimeSize() {
        return iexRealtimeSize;
    }

    public void setIexRealtimeSize(Object iexRealtimeSize) {
        this.iexRealtimeSize = iexRealtimeSize;
    }

    public Object getIexLastUpdated() {
        return iexLastUpdated;
    }

    public void setIexLastUpdated(Object iexLastUpdated) {
        this.iexLastUpdated = iexLastUpdated;
    }

    public double getDelayedPrice() {
        return delayedPrice;
    }

    public void setDelayedPrice(double delayedPrice) {
        this.delayedPrice = delayedPrice;
    }

    public long getDelayedPriceTime() {
        return delayedPriceTime;
    }

    public void setDelayedPriceTime(long delayedPriceTime) {
        this.delayedPriceTime = delayedPriceTime;
    }

    public double getExtendedPrice() {
        return extendedPrice;
    }

    public void setExtendedPrice(double extendedPrice) {
        this.extendedPrice = extendedPrice;
    }

    public double getExtendedChange() {
        return extendedChange;
    }

    public void setExtendedChange(double extendedChange) {
        this.extendedChange = extendedChange;
    }

    public double getExtendedChangePercent() {
        return extendedChangePercent;
    }

    public void setExtendedChangePercent(double extendedChangePercent) {
        this.extendedChangePercent = extendedChangePercent;
    }

    public long getExtendedPriceTime() {
        return extendedPriceTime;
    }

    public void setExtendedPriceTime(long extendedPriceTime) {
        this.extendedPriceTime = extendedPriceTime;
    }

    public double getPreviousClose() {
        return previousClose;
    }

    public void setPreviousClose(double previousClose) {
        this.previousClose = previousClose;
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public double getChangePercent() {
        return changePercent;
    }

    public void setChangePercent(double changePercent) {
        this.changePercent = changePercent;
    }

    public Object getIexMarketPercent() {
        return iexMarketPercent;
    }

    public void setIexMarketPercent(Object iexMarketPercent) {
        this.iexMarketPercent = iexMarketPercent;
    }

    public Object getIexVolume() {
        return iexVolume;
    }

    public void setIexVolume(Object iexVolume) {
        this.iexVolume = iexVolume;
    }

    public int getAvgTotalVolume() {
        return avgTotalVolume;
    }

    public void setAvgTotalVolume(int avgTotalVolume) {
        this.avgTotalVolume = avgTotalVolume;
    }

    public Object getIexBidPrice() {
        return iexBidPrice;
    }

    public void setIexBidPrice(Object iexBidPrice) {
        this.iexBidPrice = iexBidPrice;
    }

    public Object getIexBidSize() {
        return iexBidSize;
    }

    public void setIexBidSize(Object iexBidSize) {
        this.iexBidSize = iexBidSize;
    }

    public Object getIexAskPrice() {
        return iexAskPrice;
    }

    public void setIexAskPrice(Object iexAskPrice) {
        this.iexAskPrice = iexAskPrice;
    }

    public Object getIexAskSize() {
        return iexAskSize;
    }

    public void setIexAskSize(Object iexAskSize) {
        this.iexAskSize = iexAskSize;
    }

    public String getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(String marketCap) {
        this.marketCap = marketCap;
    }

    public Object getPeRatio() {
        return peRatio;
    }

    public void setPeRatio(Object peRatio) {
        this.peRatio = peRatio;
    }

    public String getWeek52High() {
        return week52High;
    }

    public void setWeek52High(String week52High) {
        this.week52High = week52High;
    }

    public double getWeek52Low() {
        return week52Low;
    }

    public void setWeek52Low(double week52Low) {
        this.week52Low = week52Low;
    }

    public double getYtdChange() {
        return ytdChange;
    }

    public void setYtdChange(double ytdChange) {
        this.ytdChange = ytdChange;
    }
}
