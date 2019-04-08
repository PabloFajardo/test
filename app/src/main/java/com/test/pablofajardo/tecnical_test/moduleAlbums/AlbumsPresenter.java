package com.test.pablofajardo.tecnical_test.moduleAlbums;


import com.test.pablofajardo.tecnical_test.moduleAlbums.models.Album;
import com.test.pablofajardo.tecnical_test.moduleAlbums.useCase.GetAlbumsImp;
import com.test.pablofajardo.tecnical_test.moduleAlbums.useCase.IGetAlbums;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumsPresenter implements IAlbumsContract.Presenter {

    private IAlbumsContract.View IView;
    private IGetAlbums IGetAlbums;

    AlbumsPresenter(IAlbumsContract.View view) {

        IView = view;
        IGetAlbums = new GetAlbumsImp();
    }

    @Override
    public void getAlbums() {

        IGetAlbums.getAlbums(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {

                if (response.isSuccessful()){
                    IView.showAlbums(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
            }
        });
    }
}
