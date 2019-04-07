package com.test.pablofajardo.tecnical_test.moduleAlbums.useCase;

import com.test.pablofajardo.tecnical_test.moduleAlbums.models.Album;
import com.test.pablofajardo.tecnical_test.services.IServices;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class GetAlbumsImp implements IGetAlbums {

    private IServices iServices;

    public GetAlbumsImp() {
        iServices = IServices.retrofit.create(IServices.class);
    }

    @Override
    public void getAlbums(Callback<List<Album>> callback) {

        Call<List<Album>> loginResponseCall = iServices.getAlbums();
        loginResponseCall.enqueue(callback);

    }
}
