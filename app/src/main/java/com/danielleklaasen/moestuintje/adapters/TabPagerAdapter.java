package com.danielleklaasen.moestuintje.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.danielleklaasen.moestuintje.R;
import com.danielleklaasen.moestuintje.fragments.FragmentGarden;
import com.danielleklaasen.moestuintje.fragments.FragmentHarvest;
import com.danielleklaasen.moestuintje.fragments.FragmentTips;

public class TabPagerAdapter extends FragmentPagerAdapter {

    Context mContext;

    public TabPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        int iPosition = position + 1;
        if (position == 0) {
            return FragmentGarden.newInstance(iPosition);
        } else if (position == 1) {
            return FragmentHarvest.newInstance(iPosition);
        } else {
            return FragmentTips.newInstance(iPosition);
        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // getting tab names from string array in resources
        String[] tabTitles = mContext.getResources().getStringArray(R.array.tab_titles);
        return tabTitles[position];
    }

}
