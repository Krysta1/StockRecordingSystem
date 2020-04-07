package com.us.srs.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.us.srs.R;
import com.us.srs.adapter.RecycNewsAdapter;
import com.us.srs.adapter.RecycStockAdapter;
import com.us.srs.base.BaseActivity;
import com.us.srs.db.bean.ChartResponse;
import com.us.srs.db.bean.MyXFormatter;
import com.us.srs.db.bean.NewsResponse;
import com.us.srs.db.bean.StockResponse;
import com.us.srs.impl.SubscriberOnNextListener;
import com.us.srs.net.http.HttpEngine;
import com.us.srs.net.http.HttpWebServer;
import com.us.srs.net.observer.ProgressObserver;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import java.util.*;

public class NewsActivity extends BaseActivity {
    @BindView(R.id.tv_current)
    TextView tvCurrent;
    @BindView(R.id.tv_hight)
    TextView tvHight;
    @BindView(R.id.tv_low)
    TextView tvLow;
    @BindView(R.id.tv_open)
    TextView tvOpen;
    @BindView(R.id.tv_close)
    TextView tvClose;
    @BindView(R.id.lin_chart)
    LineChart lineChart;
    @BindView(R.id.tv_symbol)
    TextView tvSymbol;
    @BindView(R.id.recyc_content)
    RecyclerView recycContent;
    private String SYS;
    private StockResponse stockResponse;
    private NewsResponse newsResponse=new NewsResponse();
    private ChartResponse chartResponse=new ChartResponse();
    private XAxis xAxis;                //X轴
    private YAxis leftYAxis;            //左侧Y轴
    private YAxis rightYaxis;           //右侧Y轴
    private Legend legend;              //图例
    private RecycNewsAdapter recycNewsAdapter;
    @Override
    protected int setLayoutViewById() {
        return R.layout.fragment_news;
    }
    @Override
    protected void initView() {
        SYS = getIntent().getStringExtra("SYS");
        recycContent.setLayoutManager(new LinearLayoutManager(this));
        recycContent.setNestedScrollingEnabled(false);
        getNews();
        initChart(lineChart);
    }
    @Override
    protected void initListenter() {

    }
    private void initDataView() {
        if (stockResponse != null) {
            tvSymbol.setText(stockResponse.getSymbol());
            tvCurrent.setText(String.valueOf(stockResponse.getLatestPrice()));
            tvHight.setText(String.valueOf(stockResponse.getHigh()));
            if(stockResponse.getHigh()>stockResponse.getOpen()){
                tvHight.setTextColor(ContextCompat.getColor(this,R.color.color_sys));
            }else {
                tvHight.setTextColor(ContextCompat.getColor(this,R.color.color_blue));
            }
            tvLow.setText(String.valueOf(stockResponse.getLow()));
            if(stockResponse.getLow()>stockResponse.getOpen()){
                tvLow.setTextColor(ContextCompat.getColor(this,R.color.color_sys));
            }else {
                tvLow.setTextColor(ContextCompat.getColor(this,R.color.color_blue));
            }
            tvOpen.setText(String.valueOf(stockResponse.getOpen()));
            tvClose.setText(String.valueOf(stockResponse.getClose()));
            if(stockResponse.getClose()>stockResponse.getOpen()){
                tvClose.setTextColor(ContextCompat.getColor(this,R.color.color_sys));
            }else {
                tvClose.setTextColor(ContextCompat.getColor(this,R.color.color_blue));
            }
        }
        if(chartResponse!=null&&chartResponse.getChart()!=null){
            showLineChart(chartResponse.getChart(), "History value", Color.CYAN);
        }
        if(newsResponse!=null&&newsResponse.getNews()!=null){
            recycNewsAdapter = new RecycNewsAdapter(this, newsResponse.getNews());
            recycContent.setAdapter(recycNewsAdapter);
        }
    }
    public void getNews() {
        HttpWebServer.toSubscribe(HttpEngine.getApiServer().getNews(SYS, "quote,news,chart", "1m", "30"),
                new ProgressObserver<JsonObject>(this,
                        new SubscriberOnNextListener<JsonObject>() {
                            @Override
                            public void OnSuccess(JsonObject loginResponse) {
                                JsonObject jsonArray = loginResponse;
                                if (jsonArray != null) {
                                    Gson gson = new Gson();
                                    String s = jsonArray.toString();
                                    try {
                                        JSONObject jsonObject = new JSONObject(s);
                                        JSONObject object = jsonObject.getJSONObject(SYS.toUpperCase());
                                        stockResponse = gson.fromJson(object.getString("quote").toString(), StockResponse.class);
                                        JsonArray jsonnews=jsonArray.get(SYS.toUpperCase()).getAsJsonObject().get("news").getAsJsonArray();
                                        JsonArray jsonChart=jsonArray.get(SYS.toUpperCase()).getAsJsonObject().get("chart").getAsJsonArray();

                                        List<NewsResponse.NewsBean> newsBeanList=new ArrayList<>();
                                        List<ChartResponse.ChartBean> chartBeanList=new ArrayList<>();
                                        for (int i = 0; i < jsonnews.size(); ++i) {
                                            JsonElement objectnews = jsonnews.get(i);
                                            NewsResponse.NewsBean newsBean = gson.fromJson(objectnews, NewsResponse.NewsBean.class);
                                            newsBeanList.add(newsBean);
                                        }
                                        newsResponse.setNews(newsBeanList);

                                        for (int i = 0; i < jsonChart.size(); ++i) {
                                            JsonElement objectchart = jsonChart.get(i);
                                            ChartResponse.ChartBean chartBean = gson.fromJson(objectchart, ChartResponse.ChartBean.class);
                                            chartBeanList.add(chartBean);
                                        }
                                        chartResponse.setChart(chartBeanList);
                                        initDataView();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                            @Override
                            public void OnFailure(int code, String msg) {

                            }
                        }));
    }

    /**
     * 初始化图表
     */
    private void initChart(LineChart lineChart) {
        /***图表设置***/
        //是否展示网格线
        lineChart.setDrawGridBackground(false);
        //是否显示边界
        lineChart.setDrawBorders(false);
        //是否可以拖动
        lineChart.setDragEnabled(true);
        //是否有触摸事件
        lineChart.setTouchEnabled(true);
        //设置XY轴动画效果
        lineChart.animateY(2500);
        lineChart.animateX(1500);
        lineChart.getViewPortHandler().getMatrixTouch().postScale(4f, 1f);
        Description description=new Description();
        description.setText("");
        lineChart.setDescription(description);

        /***XY轴的设置***/
        xAxis = lineChart.getXAxis();
        leftYAxis = lineChart.getAxisLeft();
        rightYaxis = lineChart.getAxisRight();
        //X轴设置显示位置在底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        //保证Y轴从0开始，不然会上移一点
        leftYAxis.setAxisMinimum(0f);
        rightYaxis.setAxisMinimum(0f);
        /***折线图例 标签 设置***/
        legend = lineChart.getLegend();
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextSize(12f);
        //显示位置 左下方
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        //是否绘制在图表里面
        legend.setDrawInside(false);

    }
    /**
     * 曲线初始化设置 一个LineDataSet 代表一条曲线
     * @param lineDataSet 线条
     * @param color       线条颜色
     * @param mode
     */
    private void initLineDataSet(LineDataSet lineDataSet, int color, LineDataSet.Mode mode) {
        lineDataSet.setColor(color);
        lineDataSet.setCircleColor(color);
        lineDataSet.setLineWidth(1f);
        lineDataSet.setCircleRadius(3f);
        //设置曲线值的圆点是实心还是空心
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setValueTextSize(10f);
        //设置折线图填充
        lineDataSet.setDrawFilled(true);
        lineDataSet.setFormLineWidth(1f);
        lineDataSet.setFormSize(15.f);
        if (mode == null) {
            //设置曲线展示为圆滑曲线（如果不设置则默认折线）
            lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        } else {
            lineDataSet.setMode(mode);
        }
    }
    /**
     * 展示曲线
     *
     * @param dataList 数据集合
     * @param name     曲线名称
     * @param color    曲线颜色
     */
    public void showLineChart(List<ChartResponse.ChartBean> dataList, String name, int color) {
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            ChartResponse.ChartBean data = dataList.get(i);
            /**
             * 在此可查看 Entry构造方法，可发现 可传入数值 Entry(float x, float y)
             * 也可传入Drawable， Entry(float x, float y, Drawable icon) 可在XY轴交点 设置Drawable图像展示
             */
            Entry entry = new Entry(i, (float) data.getClose());
            entries.add(entry);
        }

        xAxis.setValueFormatter(new MyXFormatter(dataList));
        LineDataSet lineDataSet = new LineDataSet(entries, name);
        initLineDataSet(lineDataSet, color, LineDataSet.Mode.LINEAR);
        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
    }
}


