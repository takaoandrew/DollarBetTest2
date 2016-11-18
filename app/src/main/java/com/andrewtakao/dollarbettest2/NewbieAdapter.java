package com.andrewtakao.dollarbettest2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by andrewtakao on 11/16/16.
 */


public class NewbieAdapter extends FragmentStatePagerAdapter {
    private static final int TOTAL = 5;

    public NewbieAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        return NewbieFragment.create(position);
    }

    @Override
    public int getCount() {
        return TOTAL;
    }
}