package com.us.srs.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.us.srs.R;
import com.us.srs.activity.BrowseActivity;
import com.us.srs.db.bean.NewsResponse;
import com.us.srs.utils.Router;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecycNewsAdapter extends RecyclerView.Adapter<RecycNewsAdapter.ViewHolder> {
    private Context context;
    private List<NewsResponse.NewsBean> newsBeanList;

    public RecycNewsAdapter(Context context, List<NewsResponse.NewsBean> newsBeanList) {
        this.context = context;
        this.newsBeanList = newsBeanList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_news_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final NewsResponse.NewsBean newsBean=newsBeanList.get(position);
        holder.tvTime.setText(newsBean.getDatetime());
        holder.tvTitle.setText(newsBean.getHeadline());
        Glide.with(context).load(newsBean.getImage()).placeholder(R.drawable.image_error).into(holder.tvImage);
        holder.rlView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Router.starIntent(context, BrowseActivity.class,"URL",newsBean.getUrl());
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsBeanList == null ? 0 : newsBeanList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_image)
        ImageView tvImage;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.rl_view)
        RelativeLayout rlView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
