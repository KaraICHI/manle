package com.manle.saitamall.order.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.manle.saitamall.R;
import com.manle.saitamall.base.BaseFragment;
import com.manle.saitamall.community.adapter.HotPostListViewAdapter;
import com.manle.saitamall.community.bean.HotPostBean;
import com.manle.saitamall.community.fragment.HotPostFragment;
import com.manle.saitamall.home.uitls.SpaceItemDecoration;
import com.manle.saitamall.order.adapter.OrderItemAdapter;
import com.manle.saitamall.order.bean.OrderMasterBean;
import com.manle.saitamall.utils.CacheUtils;
import com.manle.saitamall.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by Administrator on 2018/4/6.
 */

public class OrderWaitToSendFragment extends BaseFragment{
    RecyclerView recyclerView;
    private List<OrderMasterBean.ResultBean> result;
    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_order, null);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        return view;

    }

    @Override
    public void initData() {
        super.initData();
        getDataFromNet();
    }

    private void getDataFromNet() {
        OkHttpUtils
                .get()
                .url(Constants.HOT_POST_URL)
                .id(100)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onBefore(Request request, int id) {
                        super.onBefore(request, id);
                    }

                    @Override
                    public void onAfter(int id) {
                        super.onAfter(id);
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        processData(CacheUtils.getString(mContext,"orderMasterJson"));
                    }

                    @Override
                    public void onResponse(String response, int id) {

                        switch (id) {
                            case 100:

                                if (response != null) {
                                    processData(response);
                                }
                                break;

                            case 101:
                                Toast.makeText(mContext, "https", Toast.LENGTH_SHORT).show();
                                break;
                        }

                    }
                });
    }

    private void processData(String response) {
        Gson gson =new Gson();
        OrderMasterBean orderMasterBean = gson.fromJson(response,OrderMasterBean.class);
        result = orderMasterBean.getResult();
        CacheUtils.putString(mContext,"orderMasterJson",response);

        OrderItemAdapter adapter = new OrderItemAdapter(mContext, result);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new SpaceItemDecoration(3));
    }


}
