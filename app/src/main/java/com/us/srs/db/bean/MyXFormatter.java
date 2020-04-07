package com.us.srs.db.bean;


import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import java.util.*;
public class MyXFormatter implements IAxisValueFormatter {

    private List<ChartResponse.ChartBean> mValues;

    public MyXFormatter(List<ChartResponse.ChartBean> mValues) {
        this.mValues = mValues;
    }
    private static final String TAG = "MyXFormatter";

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return mValues.get((int) value % mValues.size()).getDate();
    }
}