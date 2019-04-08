package com.test.pablofajardo.tecnical_test;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.test.pablofajardo.tecnical_test.base.BaseView;
import com.test.pablofajardo.tecnical_test.moduleAlbums.AlbumsFragment;
import com.test.pablofajardo.tecnical_test.moduleAlbums.models.Album;
import com.test.pablofajardo.tecnical_test.moduleDetails.DetailsFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BaseView.Navigation {

    private static final String CURRENT_FRAGMENT = "CURRENT_FRAGMENT";
    private FragmentManager mSupportFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSupportFragmentManager = getSupportFragmentManager();

        setToolbar();

        Fragment fragment = AlbumsFragment.newInstance();

        if (savedInstanceState == null) //primary work flow
            fragment = AlbumsFragment.newInstance();
        else {
            switch (savedInstanceState.getString(CURRENT_FRAGMENT)) {
                case AlbumsFragment.ALBUM_LIST:
                    fragment = AlbumsFragment.newInstance(savedInstanceState);
                    break;

                case DetailsFragment.ALBUM_ID:
                    fragment = DetailsFragment.newInstance(savedInstanceState);
                    break;

            }
        }
        changeFragment(fragment, false);
    }

    @Override
    public void changeFragment(Fragment fragment, boolean replace) {

        String tag = fragment.getClass().getSimpleName();
        FragmentTransaction ft = mSupportFragmentManager.beginTransaction();

        if (replace){
            ft.replace(R.id.main_frame, fragment, tag);
        }else{
            ft.add(R.id.main_frame, fragment, tag);
        }
        ft.addToBackStack(tag);
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
        Fragment fragment = mSupportFragmentManager.findFragmentByTag(DetailsFragment.DETAIL_TAG);
        if (fragment != null) {
            mSupportFragmentManager.popBackStackImmediate(AlbumsFragment.ALBUM_TAG, 0);
        }else{
            fragment = mSupportFragmentManager.findFragmentByTag(AlbumsFragment.ALBUM_TAG);
            if (fragment != null && fragment instanceof AlbumsFragment) {
                finish();
            }
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {

        Fragment fragment = mSupportFragmentManager.findFragmentByTag(DetailsFragment.DETAIL_TAG);
        if (fragment != null) {
            outState.putInt(DetailsFragment.ALBUM_ID, ((DetailsFragment) fragment).getAlbumId());
            outState.putString(CURRENT_FRAGMENT, DetailsFragment.ALBUM_ID);
        } else {
            fragment = mSupportFragmentManager.findFragmentByTag(AlbumsFragment.ALBUM_TAG);
            List<Album> albums = ((AlbumsFragment) fragment).getPresenter().getAlbumList();

            outState.putParcelableArrayList(AlbumsFragment.ALBUM_LIST, new ArrayList<Parcelable>(albums));
            outState.putString(AlbumsFragment.ALBUM_SEARCH, ((AlbumsFragment) fragment).getSearch());
            outState.putString(CURRENT_FRAGMENT, AlbumsFragment.ALBUM_LIST);
        }
        super.onSaveInstanceState(outState);
    }
}
