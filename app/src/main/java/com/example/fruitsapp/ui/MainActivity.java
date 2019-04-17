package com.example.fruitsapp.ui;

import android.os.Bundle;

import com.example.fruitsapp.adapter.DataAdapter;
import com.example.fruitsapp.R;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        DataAdapter productAdapter = new DataAdapter(this, getSupportFragmentManager(), 2);
        viewPager.setAdapter(productAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}
