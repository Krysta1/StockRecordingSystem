package com.us.srs.db.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;
import java.io.Serializable;
import java.util.Date;
import java.text.SimpleDateFormat;

@Table("Tran")
public class TransactionItemBean implements Parcelable,Comparable<TransactionItemBean>{
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
    private int typeFlag;

    public int getTypeFlag() {
        return typeFlag;
    }

    public void setTypeFlag(int typeFlag) {
        this.typeFlag = typeFlag;
    }

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
    public int compareTo(@NonNull TransactionItemBean o) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.Id);
        dest.writeString(this.symBol);
        dest.writeString(this.amount);
        dest.writeString(this.unitPrice);
        dest.writeString(this.Data);
        dest.writeInt(this.typeFlag);
    }

    public TransactionItemBean() {
    }

    protected TransactionItemBean(Parcel in) {
        this.Id = in.readInt();
        this.symBol = in.readString();
        this.amount = in.readString();
        this.unitPrice = in.readString();
        this.Data = in.readString();
        this.typeFlag = in.readInt();
    }

    public static final Creator<TransactionItemBean> CREATOR = new Creator<TransactionItemBean>() {
        @Override
        public TransactionItemBean createFromParcel(Parcel source) {
            return new TransactionItemBean(source);
        }

        @Override
        public TransactionItemBean[] newArray(int size) {
            return new TransactionItemBean[size];
        }
    };
}
