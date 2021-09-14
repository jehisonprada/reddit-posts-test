package com.rappi.test.redditpostsviewer.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rappi.test.redditpostsviewer.R;
import com.rappi.test.redditpostsviewer.model.entities.Child;
import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by Jehison on 27/12/2016.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RowListHolder> {

    private List<Child> redditChildren;
    private Context context;

    public RecyclerViewAdapter(List<Child> redditChildren, Context context) {
        this.redditChildren = redditChildren;
        this.context = context;
    }

    @Override
    public RowListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowListLayout = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row, parent, false);

        return new RowListHolder(rowListLayout);
    }

    @Override
    public void onBindViewHolder(RowListHolder holder, int position) {
        final Child redditChild = redditChildren.get(position);
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("child", redditChild);
                Intent detailIntent = new Intent(context, DetailActivity.class);
                detailIntent.putExtras(bundle);
                detailIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NO_ANIMATION);
                context.startActivity(detailIntent);
                ((Activity)context).overridePendingTransition(R.anim.rotate_anim, R.anim.rotate_anim_against);
            }
        });
        holder.thumbnailText.setText(redditChild.getData().getTitle());
        if(StringUtils.isNotBlank(redditChild.getData().getIconImg())){
            Picasso.with(context).load(redditChild.getData().getIconImg()).into(holder.thumbnailImage);
        } else if(StringUtils.isNotBlank(redditChild.getData().getHeaderImg())){
            Picasso.with(context).load(redditChild.getData().getHeaderImg()).into(holder.thumbnailImage);
        } else{
            holder.thumbnailImage.setImageResource(R.drawable.image_not_found);
        }
    }

    @Override
    public int getItemCount() {
        return redditChildren.size();
    }

    public class RowListHolder extends RecyclerView.ViewHolder {

        public RelativeLayout relativeLayout;
        public ImageView thumbnailImage;
        public TextView thumbnailText;

        public RowListHolder(View view) {
            super(view);
            relativeLayout = (RelativeLayout) view.findViewById(R.id.rl_list_row);
            thumbnailImage = (ImageView) view.findViewById(R.id.thumbnail_image);
            thumbnailText = (TextView) view.findViewById(R.id.thumbnail_tittle);
        }
    }

}
