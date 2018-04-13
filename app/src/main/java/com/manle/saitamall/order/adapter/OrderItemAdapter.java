package com.manle.saitamall.order.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.manle.saitamall.R;
import com.manle.saitamall.order.bean.OrderMasterBean;
import com.manle.saitamall.utils.Constants;

import java.util.List;

/**
 * Created by Administrator on 2018/4/11.
 */

public class OrderItemAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private  List<OrderMasterBean.ResultBean> result;

    public OrderItemAdapter(Context mContext, List<OrderMasterBean.ResultBean> result) {
        this.mContext = mContext;
        this.result = result;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext,R.layout.item_order,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        OrderItemAdapter.ViewHolder viewHolder = (OrderItemAdapter.ViewHolder) holder;
        viewHolder.setData(result.get(position));

    }

    @Override
    public int getItemCount() {
        return result.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivProductFigure;
        TextView tvOrderNumber;
        TextView tvOrderQuanity;
        TextView tvOrderPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            ivProductFigure = (ImageView) itemView.findViewById(R.id.iv_product_figure);
            tvOrderNumber = (TextView) itemView.findViewById(R.id.tv_order_number);
            tvOrderQuanity = (TextView) itemView.findViewById(R.id.tv_order_quanity);
            tvOrderPrice = (TextView) itemView.findViewById(R.id.tv_order_price);
        }

        public void setData(OrderMasterBean.ResultBean data) {
            Glide.with(mContext).load(Constants.BASE_URl_IMAGE +data.getOrderBeans().get(0).getFigure()).into(ivProductFigure);
            tvOrderNumber.setText(data.getOrder_number());
            tvOrderQuanity.setText(data.getTotal_quanity());
            tvOrderPrice.setText(data.getTotal_price());

        }
    }
}
