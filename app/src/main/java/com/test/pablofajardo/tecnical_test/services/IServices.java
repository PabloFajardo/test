package com.test.pablofajardo.tecnical_test.services;

import com.test.pablofajardo.tecnical_test.moduleAlbums.models.Album;
import com.test.pablofajardo.tecnical_test.moduleDetails.models.AlbumDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IServices {

    String BASE_URL = "https://jsonplaceholder.typicode.com/";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(IServices.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @GET("albums")
    Call<List<Album>> getAlbums();

    @GET("photos")
    Call<List<AlbumDetails>> getDetails(@Query("albumId") int albumsId);

}
