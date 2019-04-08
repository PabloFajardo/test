package com.test.pablofajardo.tecnical_test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.test.pablofajardo.tecnical_test.base.BaseView;
import com.test.pablofajardo.tecnical_test.moduleAlbums.AlbumsFragment;

public class MainActivity extends AppCompatActivity implements BaseView.Navigation {

    private FragmentManager mSupportFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSupportFragmentManager = getSupportFragmentManager();

        setToolbar();

        AlbumsFragment albumsFragment = AlbumsFragment.newInstance();
        changeFragment(albumsFragment, true);
    }

    @Override
    public void changeFragment(Fragment fragment, boolean replace) {

        String tag = fragment.getClass().getSimpleName();
        FragmentTransaction ft = mSupportFragmentManager.beginTransaction();

        if (replace){
            ft.replace(R.id.main_frame, fragment, tag);
            ft.addToBackStack(null);
        }else{
            ft.add(R.id.main_frame, fragment, tag);
            ft.addToBackStack(tag);
        }

        ft.commit();

        updateNavIcon();
    }

    private void setToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        updateNavIcon();
    }

    private void updateNavIcon() {

        int backStackEntryCount = mSupportFragmentManager.getBackStackEntryCount();

        boolean setIndicator = (backStackEntryCount > 0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(setIndicator);
        getSupportActionBar().setDisplayShowHomeEnabled(setIndicator);
        getSupportActionBar().setHomeButtonEnabled(setIndicator);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

    }

    @Override
    public void onBackPressed() {

        if (mSupportFragmentManager.getBackStackEntryCount() == 1) {
            finish();
        } else {
            mSupportFragmentManager.popBackStack();
        }
    }
}
