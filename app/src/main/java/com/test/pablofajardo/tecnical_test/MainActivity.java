package com.test.pablofajardo.tecnical_test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.test.pablofajardo.tecnical_test.base.BaseView;

public class MainActivity extends AppCompatActivity implements BaseView.Navigation {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AlbumsFragment albumsFragment = AlbumsFragment.newInstance();
        changeFragment(albumsFragment, true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void changeFragment(Fragment fragment, boolean replace) {

        String tag = fragment.getClass().getSimpleName();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        if (replace){
            ft.replace(R.id.main_frame, fragment, tag);
            ft.addToBackStack(null);
        }else{
            ft.add(R.id.main_frame, fragment, tag);
            ft.addToBackStack(tag);
        }

        ft.commit();
    }
}
