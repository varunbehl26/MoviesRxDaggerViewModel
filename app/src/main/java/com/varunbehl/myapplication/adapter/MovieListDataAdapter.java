package com.varunbehl.myapplication.adapter;
/**
 * Created by varunbehl on 07/03/17.
 */


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.varunbehl.myapplication.ImageUtil;
import com.varunbehl.myapplication.R;
import com.varunbehl.myapplication.entity.Picture.Pictures;

import java.util.List;

public class MovieListDataAdapter extends RecyclerView.Adapter<MovieListDataAdapter.ViewHolder> {

    private final int value;
    private final List<Pictures> picturesList;
    private final LayoutInflater inflater;
    private final Context mContext;

    public MovieListDataAdapter(Context context, List<Pictures> objects, int value) {
        this.mContext = context;
        this.picturesList = objects;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.value = value;
    }


    @Override
    public MovieListDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView;
//        if (value == 1) {
//            convertView = inflater.inflate(R.layout.movie_layout, parent, false);
//        } else {
            convertView = inflater.inflate(R.layout.custom_movie_layout, parent, false);
//        }
        return new ViewHolder(convertView);

    }

    @Override
    public void onBindViewHolder(final MovieListDataAdapter.ViewHolder holder, int position) {
        holder.tvMovieTitle.setText(picturesList.get(position).getTitle());
        ImageUtil.loadImage(mContext, holder.draweeView, picturesList.get(position).getPosterPath());

//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mContext, MovieDetailActivity.class)
//                        .putExtra(MovieDetailActivityFragment.DETAIL_TV, picturesList.get(holder.getAdapterPosition()).getId())
//                        .putExtra("ListToOpen", 1);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                Bundle bundle = new Bundle();
//                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, picturesList.get(holder.getAdapterPosition()).getId().toString());
//                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, picturesList.get(holder.getAdapterPosition()).getTitle());
//                bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "movies");
//                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
//                mContext.startActivity(intent);
//            }
//        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (picturesList != null)
            return picturesList.size();
        else {
            return 0;
        }
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView tvMovieTitle;
        final CardView cardView;
        final ImageView draweeView;

        ViewHolder(View itemView) {
            super(itemView);
            tvMovieTitle = itemView.findViewById(R.id.tv_movie_title);
            draweeView = itemView.findViewById(R.id.img_movie_poster);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }
}