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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BaseView.Navigation {

    private FragmentManager mSupportFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSupportFragmentManager = getSupportFragmentManager();

        setToolbar();

        AlbumsFragment albumsFragment;

        if (savedInstanceState == null)
            albumsFragment = AlbumsFragment.newInstance();
        else
            albumsFragment = AlbumsFragment.newInstance(savedInstanceState);

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
        Fragment fragment = mSupportFragmentManager.findFragmentByTag(AlbumsFragment.ALBUM_TAG);
        if (fragment != null) {
            if (fragment instanceof AlbumsFragment) {
                finish();
            } else {
                mSupportFragmentManager.popBackStackImmediate();
            }
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {

        Fragment fragment = mSupportFragmentManager.findFragmentByTag(AlbumsFragment.ALBUM_TAG);
        if (fragment != null){
            List<Album> albums = ((AlbumsFragment)fragment).getPresenter().getAlbumList();

            outState.putParcelableArrayList(AlbumsFragment.ALBUM_LIST, new ArrayList<Parcelable>(albums));
            outState.putString(AlbumsFragment.ALBUM_SEARCH, ((AlbumsFragment)fragment).getSearch());
            outState.putString(AlbumsFragment.ALBUM_TAG, AlbumsFragment.ALBUM_TAG);
        }
        super.onSaveInstanceState(outState);

    }
}
