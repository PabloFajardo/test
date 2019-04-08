package com.test.pablofajardo.tecnical_test.moduleDetails;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.test.pablofajardo.tecnical_test.R;
import com.test.pablofajardo.tecnical_test.moduleDetails.adapters.DetailsAdapter;
import com.test.pablofajardo.tecnical_test.moduleDetails.models.AlbumDetails;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsFragment extends Fragment implements IDetailsContract.View {

    private static final String ALBUM_ID = "ALBUM_ID";

    @BindView(R.id.details_recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.progress_detail)
    ProgressBar mProgress;

    private List<AlbumDetails> mDetailsList;

    private View view;

    private DetailsPresenter mPresenter;

    public static DetailsFragment newInstance(int albumId) {

        Bundle bundle = new Bundle();
        bundle.putInt(ALBUM_ID, albumId);

        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    public DetailsFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_details, container, false);
        ButterKnife.bind(this, view);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mDetailsList = new ArrayList<>();
        DetailsAdapter adapter = new DetailsAdapter(mDetailsList);
        mRecyclerView.setAdapter(adapter);

        mPresenter = new DetailsPresenter(this);
        setHasOptionsMenu(true);

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        int albumId = 0;

        if (bundle != null)
            albumId = bundle.getInt(ALBUM_ID);

        mPresenter.getAlbumDetails(albumId);
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

    @Override
    public void showDetails(@NonNull List<AlbumDetails> detailsList) {
        mProgress.setVisibility(View.GONE);

        mDetailsList.addAll(detailsList);
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }
}
