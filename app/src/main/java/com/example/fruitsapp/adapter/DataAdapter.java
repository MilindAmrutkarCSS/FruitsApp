package com.example.fruitsapp.adapter;

import android.content.Context;

import com.example.fruitsapp.ui.InCartFragment;
import com.example.fruitsapp.ui.FruitsFragment;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class DataAdapter extends FragmentPagerAdapter {

    private Context mContext;
    int totalTabs;

    public DataAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        mContext = context;
        this.totalTabs = totalTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FruitsFragment();

            case 1:
                return new InCartFragment();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Products";

            case 1:
                return "In Cart";

            default:
                return null;
        }

    }
}
