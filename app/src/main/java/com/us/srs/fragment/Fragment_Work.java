package com.us.srs.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.us.srs.MainActivity;
import com.us.srs.R;
import com.us.srs.adapter.RecycSearchStockAdapter;
import com.us.srs.adapter.RecycStockAdapter;
import com.us.srs.base.BaseFragment;
import com.us.srs.db.bean.StockResponse;
import com.us.srs.impl.SubscriberOnNextListener;
import com.us.srs.net.http.HttpEngine;
import com.us.srs.net.http.HttpWebServer;
import com.us.srs.net.observer.ProgressObserver;
import com.us.srs.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class Fragment_Work extends BaseFragment {
    @BindView(R.id.recyc_content)
    RecyclerView recycContent;
    @BindView(R.id.recyc_search_content)
    RecyclerView recycSearchContent;
    @BindView(R.id.swipe_ly)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.edit_search)
    EditText editSearch;
    private List<StockResponse> stockResponseList = new ArrayList<>();
    private List<StockResponse> stockSearchResponseList = new ArrayList<>();
    private RecycStockAdapter recycStockAdapter;
    private RecycSearchStockAdapter recycSearchStockAdapter;

    public static Fragment_Work newInstance() {
        Fragment_Work contentFragment = new Fragment_Work();
        return contentFragment;
    }

    @Override
    protected int setLayoutViewById() {
        return R.layout.fragment_work;
    }

    @Override
    protected void initView() {
        recycContent.setLayoutManager(new LinearLayoutManager(activity));
        recycStockAdapter = new RecycStockAdapter(activity, stockResponseList);
        recycContent.setAdapter(recycStockAdapter);
        recycSearchContent.setLayoutManager(new LinearLayoutManager(activity));
        recycSearchStockAdapter = new RecycSearchStockAdapter(activity, stockSearchResponseList,stockResponseList);
        recycSearchContent.setAdapter(recycSearchStockAdapter);

    }

    @Override
    protected void initListenter() {
        ((MainActivity)getActivity()).setOnBackPressedLinstenter(new MainActivity.onBackPressedLinstenter() {
            @Override
            public boolean onBackPressed() {
                if(recycSearchContent.getVisibility()==View.VISIBLE){
                    recycContent.setVisibility(View.VISIBLE);
                    recycSearchContent.setVisibility(View.GONE);
                    stockSearchResponseList.clear();
                    return true;
                }else {
                    return false;
                }

            }
        });
        editSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if ((actionId == 0 || actionId == 3) && event != null) {
                    getSearch(editSearch.getText().toString());
                }
                return false;
            }
        });
        recycSearchStockAdapter.setAddCheckListenter(new RecycSearchStockAdapter.AddCheckListenter() {
            @Override
            public void onClick(StockResponse stockResponse) {
                stockResponseList.add(stockResponse);
                recycStockAdapter.notifyDataSetChanged();
                recycContent.setVisibility(View.VISIBLE);
                recycSearchContent.setVisibility(View.GONE);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },2000);
            }
        });
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(editSearch.getText().toString().trim())) {
                    recycContent.setVisibility(View.VISIBLE);
                    recycSearchContent.setVisibility(View.GONE);
                    stockSearchResponseList.clear();
                }
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getMostactiveList();
    }

    @OnClick({R.id.tv_search})
    public void OnClickView(View view) {
        switch (view.getId()) {
            case R.id.tv_search:
                String key = editSearch.getText().toString().trim();
                if (!TextUtils.isEmpty(key)) {
                    getSearch(key);
                } else {
                    ToastUtils.showShortToast("Please enter symbol", activity);
                }
                break;
        }
    }

    public void getMostactiveList() {
        HttpWebServer.toSubscribe(HttpEngine.getApiServer().getMostactiveList(), new ProgressObserver<JsonArray>(getActivity(),
                new SubscriberOnNextListener<JsonArray>() {
                    @Override
                    public void OnSuccess(JsonArray loginResponse) {
                        JsonArray jsonArray = loginResponse;
                        if (jsonArray != null) {
                            Gson gson = new Gson();
                            stockResponseList.clear();
                            for (int i = 0; i < jsonArray.size(); ++i) {
                                JsonElement object = jsonArray.get(i);
                                StockResponse stockResponse = gson.fromJson(object, StockResponse.class);
                                stockResponseList.add(stockResponse);
                            }
                            recycStockAdapter.notifyDataSetChanged();
                            recycSearchStockAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void OnFailure(int code, String msg) {

                    }
                }));
    }

    public void getSearch(final String key) {
        HttpWebServer.toSubscribe(HttpEngine.getApiServer().getSearChBySymbol(key, "quote"), new ProgressObserver<JsonObject>(getActivity(),
                new SubscriberOnNextListener<JsonObject>() {
                    @Override
                    public void OnSuccess(JsonObject loginResponse) {
                        JsonObject jsonArray = loginResponse;
                        if (jsonArray != null) {
                            Gson gson = new Gson();
                            String s = jsonArray.toString();
                            stockSearchResponseList.clear();
                            try {
                                JSONObject jsonObject = new JSONObject(s);
                                JSONObject object = jsonObject.getJSONObject(key.toUpperCase());
                                StockResponse stockResponse = gson.fromJson(object.getString("quote").toString(), StockResponse.class);
                                stockSearchResponseList.add(stockResponse);
                                recycContent.setVisibility(View.GONE);
                                recycSearchContent.setVisibility(View.VISIBLE);
                                recycSearchStockAdapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                e.printStackTrace();
                                ToastUtils.showShortToast("No result", activity);
                            }
                        } else {
                            ToastUtils.showShortToast("No result", activity);
                        }
                    }

                    @Override
                    public void OnFailure(int code, String msg) {

                    }
                }));
    }

}
