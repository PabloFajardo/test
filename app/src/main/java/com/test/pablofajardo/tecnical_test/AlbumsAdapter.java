package com.test.pablofajardo.tecnical_test;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.test.pablofajardo.tecnical_test.models.Album;

import java.util.ArrayList;
import java.util.List;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.AlbumViewHolder> implements Filterable {

    private List<Album> mAlbumList, mResultList;
    private IAlbumsContract.View mListener;

    AlbumsAdapter(List<Album> mAlbumList, IAlbumsContract.View listener ) {
        this.mAlbumList = mAlbumList;
        this.mResultList = new ArrayList<>(mAlbumList);
        this.mListener = listener;
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = (LayoutInflater) viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        assert layoutInflater != null;
        View convertView = layoutInflater.inflate(R.layout.item_album, viewGroup, false);

        return new AlbumViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder albumViewHolder, int i) {
            albumViewHolder.bindView(mAlbumList.get(i));
    }

    @Override
    public int getItemCount() {

        if (mAlbumList != null && mAlbumList.size() > 0)
            return mAlbumList.size();
        else
            return 0;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults results = new FilterResults();
                String search = constraint.toString().toLowerCase();
                List<Album> albumList = new ArrayList<>();

                for (Album album : mAlbumList) {
                    if (album.getTitle().toLowerCase().contains(search)) {
                        albumList.add(album);
                    }
                }

                results.values = albumList;
                results.count = albumList.size();
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count > 0) {
                    // The API returned at least one result, update the data.
                    mResultList = (List<Album>) results.values;
                    notifyDataSetChanged();
                }
            }
        };
    }

    final class AlbumViewHolder extends RecyclerView.ViewHolder {

        private View parentLayout;
        private TextView id;
        private TextView title;
        private Album album;

        AlbumViewHolder(View itemView) {
            super(itemView);

            parentLayout = itemView;
            id = itemView.findViewById(R.id.album_id);
            title = itemView.findViewById(R.id.album_title);
        }

        void bindView(Album _album) {

            if (_album == null) return;

            album = _album;
            id.setText(String.valueOf(album.getId()));
            title.setText(album.getTitle());

            parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.showMessage("holis " + album.getTitle());
                    mListener.onAlbumSelected(album);
                }
            });

        }
    }
}
