package com.us.srs.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.us.srs.R;
import com.us.srs.activity.NewsActivity;
import com.us.srs.db.bean.StockResponse;
import com.us.srs.utils.Router;
import java.text.DecimalFormat;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RecycStockAdapter extends RecyclerView.Adapter<RecycStockAdapter.ViewHolder> {
    private Context context;
    private List<StockResponse> stockResponseList;
    private DecimalFormat df;
    public RecycStockAdapter(Context context, List<StockResponse> stockResponseList) {
        this.context = context;
        this.stockResponseList = stockResponseList;
        df = new DecimalFormat("0.00");
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_stock_list, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final StockResponse stockResponse = stockResponseList.get(position);
        holder.tvSymbol.setText(stockResponse.getSymbol());
        holder.tvPrice.setText(String.valueOf(stockResponse.getLatestPrice()));
        if (stockResponse.getChangePercent() > 0) {
            holder.tvGain.setTextColor(ContextCompat.getColor(context, R.color.color_sys));
            holder.tvGain.setText(df.format(stockResponse.getChangePercent() * 100) + "%");
        } else {
            holder.tvGain.setTextColor(ContextCompat.getColor(context, R.color.color_blue));
            holder.tvGain.setText( df.format(stockResponse.getChangePercent() * 100) + "%");
        }
        holder.tvHight.setText(String.valueOf(stockResponse.getHigh()));
        holder.tvLow.setText(String.valueOf(stockResponse.getLow()));
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stockResponseList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,stockResponseList.size());
            }
        });
        holder.swipeMenuLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Router.starIntent(context, NewsActivity.class,"SYS",stockResponse.getSymbol());
            }
        });
    }
    @Override
    public int getItemCount() {
        return stockResponseList == null ? 0 : stockResponseList.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_symbol)
        TextView tvSymbol;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_gain)
        TextView tvGain;
        @BindView(R.id.tv_hight)
        TextView tvHight;
        @BindView(R.id.tv_low)
        TextView tvLow;
        @BindView(R.id.swipe_layout)
        LinearLayout swipeMenuLayout;
        @BindView(R.id.btnDelete)
        TextView btnDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
