package com.test.pablofajardo.tecnical_test.moduleAlbums.useCase;

import com.test.pablofajardo.tecnical_test.moduleAlbums.models.Album;

import java.util.List;

import retrofit2.Callback;

public interface IGetAlbums {

    void getAlbums(Callback<List<Album>> callback);
}
