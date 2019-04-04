package com.test.pablofajardo.tecnical_test;

import android.support.annotation.NonNull;

import com.test.pablofajardo.tecnical_test.base.BaseView;
import com.test.pablofajardo.tecnical_test.models.Album;

import java.util.List;

public interface IAlbumsContract {

    interface View extends BaseView {
        void showAlbums(@NonNull List<Album> albumList);
    }

    interface Presenter {
        void getAlbums();
    }
}
