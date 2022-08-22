package com.activity.alertobulakenyo;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {

    private int tabNum;

    public PagerAdapter (@NonNull FragmentManager fm, int behavior, int tabs) {
        super(fm, behavior);
        this.tabNum = tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new Home_Frag();

            case 1:
                return new Profile_Frag();

            case 2:
                return new Settings_Frag();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 0;
    }
}
