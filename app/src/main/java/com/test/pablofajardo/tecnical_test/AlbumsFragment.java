package com.test.pablofajardo.tecnical_test;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.pablofajardo.tecnical_test.models.Album;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class AlbumsFragment extends Fragment implements IAlbumsContract.View {

    @BindView(R.id.album_recycler)
    RecyclerView mRecyclerView;

    private View view;
    private FloatingActionButton fab;

    private AlbumsPresenter mPresenter;

    public static AlbumsFragment newInstance() {

        Bundle args = new Bundle();

        AlbumsFragment fragment = new AlbumsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public AlbumsFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_albums, container, false);
        ButterKnife.bind(this, view);

        fab = view.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.getAlbums();
            }
        });

        mPresenter = new AlbumsPresenter(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mPresenter.getAlbums();

    }

    @Override
    public void showAlbums(@NonNull List<Album> albumList) {

        AlbumsAdapter adapter = new AlbumsAdapter(albumList, this);
        mRecyclerView.setAdapter(adapter);

    }

    @Override
    public void onAlbumSelected(@NonNull Album album) {

    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }
}
