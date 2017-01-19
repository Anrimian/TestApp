package com.testapp.features.contacts;

import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ScrollView;

import com.google.android.gms.maps.OnMapReadyCallback;
import com.testapp.R;
import com.testapp.tools.fragments.ScrollableMapFragment;

/**
 * Created on 19.01.2017.
 */

public class ContactsView {

    private ScrollableMapFragment mapFragment;

    public ContactsView(View view, FragmentManager fm) {
        mapFragment = (ScrollableMapFragment) fm.findFragmentById(R.id.map_container);
        if (mapFragment == null) {
            mapFragment = new ScrollableMapFragment();
            fm.beginTransaction().replace(R.id.map_container, mapFragment).commit();
        }
        ScrollView scrollView = (ScrollView) view.findViewById(R.id.scroll_view);
        mapFragment.setTouchListener(() -> scrollView.requestDisallowInterceptTouchEvent(true));
    }

    public void bindView(OnMapReadyCallback onMapReadyCallback) {
        mapFragment.getMapAsync(onMapReadyCallback);
    }
}
