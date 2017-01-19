package com.testapp.features;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.testapp.R;
import com.testapp.features.categories.CategoriesFragment;

/**
 * Created on 17.01.2017.
 */

public class MainActivity extends AppCompatActivity {

    private DrawerFragment drawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            Fragment fragment = new CategoriesFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, fragment)
                    .commit();
        }
        initDrawer();
    }

    private void initDrawer() {
        drawerFragment = (DrawerFragment) getSupportFragmentManager()
                .findFragmentById(R.id.left_drawer);
        drawerFragment.setUp(R.id.left_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (isRootFragment() && !drawerFragment.isDrawerOpen()) {
                    drawerFragment.openDrawer();
                } else {
                    onBackPressed();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerFragment.isDrawerOpen()) {
            drawerFragment.closeDrawer();
        } else {
            if (isRootFragment()) {
                super.onBackPressed();
            } else {
                getSupportFragmentManager().popBackStack();
            }
        }
    }

    private boolean isRootFragment() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        return count == 0;
    }

    public DrawerFragment getDrawerFragment() {
        return drawerFragment;
    }
}
