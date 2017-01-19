package com.testapp.tools.fragments;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.testapp.features.DrawerFragment;
import com.testapp.features.MainActivity;

/**
 * Created on 18.01.2017.
 */

public class BackStackFragment extends Fragment {

    @Override
    public void onResume() {
        super.onResume();
        DrawerFragment drawerFragment = getDrawerFragment();
        if (drawerFragment != null) {
            drawerFragment.setDrawerIndicatorEnabled(false);
        }
    }

    @Nullable
    private DrawerFragment getDrawerFragment() {
        Activity activity = getActivity();
        if (activity instanceof MainActivity) {
            return ((MainActivity) activity).getDrawerFragment();
        }
        return null;
    }
}
