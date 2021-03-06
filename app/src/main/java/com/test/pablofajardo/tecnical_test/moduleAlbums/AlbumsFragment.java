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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

    public static final String ALBUM_TAG = AlbumsFragment.class.getSimpleName();
    public static final String ALBUM_LIST = "ALBUM_LIST";
    public static final String ALBUM_SEARCH = "ALBUM_ID";

    @BindView(R.id.album_recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.progress_album)
    ProgressBar mProgress;
    @BindView(R.id.search)
    SearchView mSearch;

    private View view;

    private AlbumsPresenter mPresenter;
    private BaseView.Navigation navigation;

    public static AlbumsFragment newInstance() {

        return  new AlbumsFragment();
    }

    public static AlbumsFragment newInstance(Bundle bundle) {

        AlbumsFragment fragment = new AlbumsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public AlbumsFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_albums, container, false);
        ButterKnife.bind(this, view);

        mPresenter = new AlbumsPresenter(this);

        initSearchView();
        setHasOptionsMenu(true);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                getActivity().onBackPressed();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initSearchView(){
        mSearch.setQueryHint("Filtrar por titulo");
        mSearch.onActionViewExpanded();
        mSearch.clearFocus();
        mSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                mSearch.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (mRecyclerView.getAdapter() != null) {
                    ((AlbumsAdapter)mRecyclerView.getAdapter()).getFilter().filter(s);
                }
                return true;
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        savedInstanceState = getArguments();
        if (savedInstanceState == null && mPresenter.getAlbumList().size() == 0) {
            mPresenter.getAlbums();

        } else {
            mPresenter.setAlbumList(savedInstanceState.<Album>getParcelableArrayList(ALBUM_LIST));
            showAlbums(mPresenter.getAlbumList());
            mSearch.setQuery(savedInstanceState.getString(ALBUM_SEARCH), false);
        }
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

    public AlbumsPresenter getPresenter() {
        return mPresenter;
    }

    public String getSearch() {
        return mSearch.getQuery().toString();
    }
}
