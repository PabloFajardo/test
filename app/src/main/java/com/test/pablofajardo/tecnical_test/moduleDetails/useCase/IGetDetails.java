package com.test.pablofajardo.tecnical_test.moduleDetails.useCase;

import com.test.pablofajardo.tecnical_test.moduleDetails.models.AlbumDetails;

import java.util.List;

import retrofit2.Callback;

public interface IGetDetails {

    void getDetails(int albumId, Callback<List<AlbumDetails>> callback);

}
