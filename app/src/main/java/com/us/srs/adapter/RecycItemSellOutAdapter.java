package com.us.srs.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.us.srs.R;
import com.us.srs.activity.ItemDetailsActivity;
import com.us.srs.db.LiteDbUtils;
import com.us.srs.db.bean.TransactionItemBean;
import com.us.srs.db.bean.TransactionItemSellOutBean;
import com.us.srs.utils.Router;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecycItemSellOutAdapter extends RecyclerView.Adapter<RecycItemSellOutAdapter.ViewHolder> {
    private Context context;
    private List<TransactionItemSellOutBean> transactionItemBeans;
    private DecimalFormat df;
    public RecycItemSellOutAdapter(Context context, List<TransactionItemSellOutBean> stockResponseList) {
        this.context = context;
        this.transactionItemBeans = stockResponseList;
        df = new DecimalFormat("0.00");
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_item_list, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final TransactionItemSellOutBean stockResponse = transactionItemBeans.get(position);
        holder.tvSymbol.setText(stockResponse.getSymBol());
        holder.tvPrice.setText(String.valueOf(stockResponse.getAmount()));
        holder.tvHight.setText(String.valueOf(stockResponse.getData()));
        holder.tvLow.setText("$"+String.valueOf(
                Integer.valueOf(stockResponse.getUnitPrice())*Integer.valueOf(stockResponse.getAmount())));
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LiteDbUtils.deletTransactionItemBean(stockResponse);
                transactionItemBeans.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,transactionItemBeans.size());
            }
        });
        holder.swipeMenuLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                TransactionItemBean itemBean=new TransactionItemBean();
                itemBean.setData(stockResponse.getData());
                itemBean.setAmount(stockResponse.getAmount());
                itemBean.setSymBol(stockResponse.getSymBol());
                itemBean.setUnitPrice(stockResponse.getUnitPrice());
                intent.putExtra("DATA",itemBean);
                intent.putExtra("Flag",true);//is sell out
                Router.starIntent(context, ItemDetailsActivity.class,intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return transactionItemBeans == null ? 0 : transactionItemBeans.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_symbol)
        TextView tvSymbol;
        @BindView(R.id.tv_price)
        TextView tvPrice;
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
