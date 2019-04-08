package com.test.pablofajardo.tecnical_test.moduleDetails;

import android.support.annotation.NonNull;

import com.test.pablofajardo.tecnical_test.base.BaseView;
import com.test.pablofajardo.tecnical_test.moduleDetails.models.AlbumDetails;

import java.util.List;

public interface IDetailsContract {

    interface View extends BaseView {
        void showDetails(@NonNull List<AlbumDetails> detailsList);
    }

    interface Presenter {
        void getAlbumDetails(int albumId);
    }
}
