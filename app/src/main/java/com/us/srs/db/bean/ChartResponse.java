package com.us.srs.db.bean;

import java.io.Serializable;
import java.util.List;

public class ChartResponse implements Serializable {


    private List<ChartBean> chart;

    public List<ChartBean> getChart() {
        return chart;
    }

    public void setChart(List<ChartBean> chart) {
        this.chart = chart;
    }

    public static class ChartBean {
        /**
         * date : 2019-02-11
         * open : 171.05
         * high : 171.21
         * low : 169.25
         * close : 169.43
         * volume : 20993425
         * unadjustedVolume : 20993425
         * change : -0.98
         * changePercent : -0.575
         * vwap : 169.9464
         * label : Feb 11
         * changeOverTime : 0
         */

        private String date;
        private double open;
        private double high;
        private double low;
        private double close;
        private int volume;
        private int unadjustedVolume;
        private double change;
        private double changePercent;
        private double vwap;
        private String label;
        private int changeOverTime;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public double getOpen() {
            return open;
        }

        public void setOpen(double open) {
            this.open = open;
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

        public double getClose() {
            return close;
        }

        public void setClose(double close) {
            this.close = close;
        }

        public int getVolume() {
            return volume;
        }

        public void setVolume(int volume) {
            this.volume = volume;
        }

        public int getUnadjustedVolume() {
            return unadjustedVolume;
        }

        public void setUnadjustedVolume(int unadjustedVolume) {
            this.unadjustedVolume = unadjustedVolume;
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

        public double getVwap() {
            return vwap;
        }

        public void setVwap(double vwap) {
            this.vwap = vwap;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public int getChangeOverTime() {
            return changeOverTime;
        }

        public void setChangeOverTime(int changeOverTime) {
            this.changeOverTime = changeOverTime;
        }
    }
}
