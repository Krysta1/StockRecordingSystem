package com.us.srs.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.us.srs.R;
import com.us.srs.activity.EditFreeflowActivity;
import com.us.srs.activity.ItemDetailsActivity;
import com.us.srs.db.LiteDbUtils;
import com.us.srs.db.bean.FreeFlowBean;
import com.us.srs.db.bean.TransactionItemBean;
import com.us.srs.db.bean.TransactionItemSellOutBean;
import com.us.srs.utils.Router;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecycFreeflowAdapter extends RecyclerView.Adapter<RecycFreeflowAdapter.ViewHolder> {
    private Context context;
    private List<FreeFlowBean> freeFlowBeanList;

    public RecycFreeflowAdapter(Context context, List<FreeFlowBean> freeFlowBeanList) {
        this.context = context;
        this.freeFlowBeanList = freeFlowBeanList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_free_flow_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final FreeFlowBean flowBean = freeFlowBeanList.get(position);
        holder.tvTitle.setText(flowBean.getTitle());
        holder.tvTime.setText(flowBean.getTime());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  LiteDbUtils.deletTransactionItemBean(stockResponse);
                transactionItemBeans.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,transactionItemBeans.size());*/
            }
        });
        holder.swipeMenuLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("data", flowBean);
                Router.starIntent(context, EditFreeflowActivity.class, intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return freeFlowBeanList == null ? 0 : freeFlowBeanList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.swipe_layout)
        RelativeLayout swipeMenuLayout;
        @BindView(R.id.btnDelete)
        TextView btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
