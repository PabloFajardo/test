package com.test.pablofajardo.tecnical_test.moduleDetails;

import android.util.Log;

import com.test.pablofajardo.tecnical_test.moduleDetails.models.AlbumDetails;
import com.test.pablofajardo.tecnical_test.moduleDetails.useCase.GetDetailsImp;
import com.test.pablofajardo.tecnical_test.moduleDetails.useCase.IGetDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class DetailsPresenter implements IDetailsContract.Presenter {

    private IDetailsContract.View IView;
    private IGetDetails IGetAlbums;

    DetailsPresenter(IDetailsContract.View view) {
        IView = view;
        IGetAlbums = new GetDetailsImp();
    }

    @Override
    public void getAlbumDetails(int albumId) {
        IGetAlbums.getDetails(albumId, new Callback<List<AlbumDetails>>() {
            @Override
            public void onResponse(Call<List<AlbumDetails>> call, Response<List<AlbumDetails>> response) {

                if (response.isSuccessful()){
                    IView.showDetails(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<AlbumDetails>> call, Throwable t) {
                t.fillInStackTrace();
                IView.showMessage(t.getMessage());
            }
        });
    }
}