package com.test.pablofajardo.tecnical_test.moduleAlbums;

import android.support.annotation.NonNull;

import com.test.pablofajardo.tecnical_test.base.BaseView;
import com.test.pablofajardo.tecnical_test.moduleAlbums.models.Album;

import java.util.List;

public interface IAlbumsContract {

    interface View extends BaseView {
        void showAlbums(@NonNull List<Album> albumList);
        void onAlbumSelected(@NonNull Album album);
    }

    interface Presenter {
        void getAlbums();
    }
}
