package com.us.srs.db.bean;

import java.io.Serializable;
import java.util.List;

public class NewsResponse implements Serializable {

    private List<NewsBean> news;

    public List<NewsBean> getNews() {
        return news;
    }

    public void setNews(List<NewsBean> news) {
        this.news = news;
    }

    public static class NewsBean {
        /**
         * datetime : 2019-03-10T20:30:51-04:00
         * headline : Tracking Chase Coleman's Tiger Global Portfolio - Q4 2018 Update
         * source : SeekingAlpha
         * url : https://api.iextrading.com/1.0/stock/aapl/article/6148181983325560
         * summary :    This article is part of a series that provides an ongoing analysis of the changes made to Chase Colemans Tiger Global Management 13F stock portfolio on a quarterly basis. It is based on Tiger Globals regulatory  13F Form  filed on 02/14/2019. Please visit our  Tracking Chase Co…
         * related : AAPL,ADBE,ADSK,AMZN,APO,AVLR,BABA,BCS,BILI,BKNG,CDAY,CLDR,Computer Hardware,CON31167138,COUP,CRM,DBX,DESP,DOCU,DPZ,EB,EDU,ESTC,FB,FCAU,FEYE,FIT,FLT,FPI,FTCH,HUYA,INTHPINK,IQ,JCP,JD,LC,MA,MELI,MSFT,NASDAQ01,NETS,NEW,NFLX,NOW,OKTA,ONE,PDD,PSTG,PVTL,QSR,RDFN,REDU,RUN,SE,SFTBY,SHOP,SOGO,SPOT,STG,STNE,SVMK,SWCH,SWI,TAL,TDG,TEAM,Computing and Information Technology,TENB,TME,TWTR,UXIN,V,ZEN,ZTO
         * image : https://api.iextrading.com/1.0/stock/aapl/news-image/6148181983325560
         */
        private String datetime;
        private String headline;
        private String source;
        private String url;
        private String summary;
        private String related;
        private String image;

        public String getDatetime() {
            return datetime;
        }

        public void setDatetime(String datetime) {
            this.datetime = datetime;
        }

        public String getHeadline() {
            return headline;
        }

        public void setHeadline(String headline) {
            this.headline = headline;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getRelated() {
            return related;
        }

        public void setRelated(String related) {
            this.related = related;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
