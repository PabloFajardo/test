package com.test.pablofajardo.tecnical_test.base;

import android.support.v4.app.Fragment;

public interface BaseView {

    void showMessage(String message);

    interface Navigation{

        void changeFragment(Fragment fragment, boolean replace);
    }
}
