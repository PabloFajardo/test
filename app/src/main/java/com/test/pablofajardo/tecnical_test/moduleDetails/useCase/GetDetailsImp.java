package com.test.pablofajardo.tecnical_test.moduleDetails.useCase;

import com.test.pablofajardo.tecnical_test.moduleDetails.models.AlbumDetails;
import com.test.pablofajardo.tecnical_test.services.IServices;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class GetDetailsImp implements IGetDetails {

    private IServices iServices;

    public GetDetailsImp() {
        iServices = IServices.retrofit.create(IServices.class);
    }

    @Override
    public void getDetails(int albumId, Callback<List<AlbumDetails>> callback) {
        Call<List<AlbumDetails>> loginResponseCall = iServices.getDetails(albumId);
        loginResponseCall.enqueue(callback);
    }
}