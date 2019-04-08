package com.test.pablofajardo.tecnical_test.moduleDetails.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.test.pablofajardo.tecnical_test.R;
import com.test.pablofajardo.tecnical_test.moduleDetails.models.AlbumDetails;

import java.util.List;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.DetailViewHolder> {

    private final List<AlbumDetails> mDetailsList;

    public DetailsAdapter(List<AlbumDetails> mDetailsList) {
        this.mDetailsList = mDetailsList;
    }

    @NonNull
    @Override
    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = (LayoutInflater) viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        assert layoutInflater != null;
        View convertView = layoutInflater.inflate(R.layout.item_detail, viewGroup, false);

        return new DetailViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsAdapter.DetailViewHolder detailViewHolder, int i) {
        detailViewHolder.bindView(mDetailsList.get(i));
    }

    @Override
    public int getItemCount() {

        if (mDetailsList.size() > 0)
            return mDetailsList.size();
        else
            return 0;
    }

    static class DetailViewHolder extends RecyclerView.ViewHolder {

        private TextView id;
        private TextView title;
        private ImageView image;

        DetailViewHolder(View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.album_id);
            title = itemView.findViewById(R.id.album_title);
            image = itemView.findViewById(R.id.album_image);
        }

        void bindView(AlbumDetails _details) {

            if (_details == null) return;

            id.setText(String.valueOf(_details.getId()));
            title.setText(_details.getTitle());

            Picasso.get().load(_details.getUrl()).into(image);
        }
    }
}
