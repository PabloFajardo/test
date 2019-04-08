package com.test.pablofajardo.tecnical_test.moduleAlbums;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.test.pablofajardo.tecnical_test.R;
import com.test.pablofajardo.tecnical_test.base.BaseView;
import com.test.pablofajardo.tecnical_test.moduleAlbums.adapters.AlbumsAdapter;
import com.test.pablofajardo.tecnical_test.moduleAlbums.models.Album;
import com.test.pablofajardo.tecnical_test.moduleDetails.DetailsFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class AlbumsFragment extends Fragment implements IAlbumsContract.View {

    @BindView(R.id.album_recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.progress_album)
    ProgressBar mProgress;

    private View view;

    private AlbumsPresenter mPresenter;
    private BaseView.Navigation navigation;

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

        initSearchView();

        mPresenter = new AlbumsPresenter(this);

        return view;
    }

    private void initSearchView(){
        final SearchView search = view.findViewById(R.id.search);
        search.setQueryHint("Filtrar por titulo");
        search.onActionViewExpanded();
        search.clearFocus();
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                search.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ((AlbumsAdapter)mRecyclerView.getAdapter()).getFilter().filter(s);
                return true;
            }
        });
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
        mProgress.setVisibility(View.GONE);

        AlbumsAdapter adapter = new AlbumsAdapter(albumList, this);
        mRecyclerView.setAdapter(adapter);

    }

    @Override
    public void onAlbumSelected(@NonNull Album album) {

        //Go to details fragment
        navigation.changeFragment(DetailsFragment.newInstance(album.getId()), false);
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof BaseView.Navigation){
            navigation = (BaseView.Navigation) context;
        }
    }
}
