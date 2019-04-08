package com.test.pablofajardo.tecnical_test.moduleAlbums;


import com.test.pablofajardo.tecnical_test.moduleAlbums.models.Album;
import com.test.pablofajardo.tecnical_test.moduleAlbums.useCase.GetAlbumsImp;
import com.test.pablofajardo.tecnical_test.moduleAlbums.useCase.IGetAlbums;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumsPresenter implements IAlbumsContract.Presenter {

    private IAlbumsContract.View IView;
    private IGetAlbums IGetAlbums;
    private List<Album> albumList;

    AlbumsPresenter(IAlbumsContract.View view) {

        IView = view;
        IGetAlbums = new GetAlbumsImp();
        albumList = new ArrayList<>();
    }

    @Override
    public void getAlbums() {

        IGetAlbums.getAlbums(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {

                if (response.isSuccessful()){
                    albumList = response.body();
                    IView.showAlbums(albumList);
                }
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
            }
        });
    }

    public List<Album> getAlbumList() {
        return albumList;
    }

    public void setAlbumList(List<Album> albumList) {
        this.albumList = albumList;
    }
}
