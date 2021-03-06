package com.us.srs.db.bean;

import android.support.annotation.NonNull;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;
import java.io.Serializable;
import java.util.Date;
import java.text.SimpleDateFormat;

@Table("TranSell")
public class TransactionItemSellOutBean implements Serializable,Comparable<TransactionItemSellOutBean>{
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int Id;
    @NotNull
    @Column("symbol")
    private String symBol;
    @NotNull
    @Column("amount")
    private String amount;
    @NotNull
    @Column("unitprice")
    private String unitPrice;
    @NotNull
    @Column("data")
    private String Data;
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getSymBol() {
        return symBol;
    }

    public void setSymBol(String symBol) {
        this.symBol = symBol;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }


    @Override
    public int compareTo(@NonNull TransactionItemSellOutBean o) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt1= format.parse(Data);
            Date dt2=format.parse(o.getData());
            if (dt1.getTime() > dt2.getTime()) {
                return -1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
