package com.manle.saitamall.order.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.manle.saitamall.R;
import com.manle.saitamall.community.adapter.CommunityViewPagerAdapter;
import com.manle.saitamall.order.adapter.AllOrderViewPagerAdapter;

/**
 * Created by Administrator on 2018/4/6.
 */

public class AllOrderActivity extends FragmentActivity {
    private TabLayout tablayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_order);
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);

        FragmentManager fragmentManager =getSupportFragmentManager();
        AllOrderViewPagerAdapter adapter = new AllOrderViewPagerAdapter(fragmentManager);
        viewPager.setAdapter(adapter);
        tablayout.setVisibility(View.VISIBLE);
        tablayout.setupWithViewPager(viewPager);
    }
}
