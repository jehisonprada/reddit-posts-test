package com.rappi.test.redditpostsviewer.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.rappi.test.redditpostsviewer.R;
import com.rappi.test.redditpostsviewer.model.entities.Child;
import com.rappi.test.redditpostsviewer.model.entities.Reddit;
import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.StringUtils;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle bundle = getIntent().getExtras();
        Child child = (Child) bundle.getSerializable("child");

        TextView tittleTextView = (TextView) findViewById(R.id.tw_detail_tittle);
        ImageView imageView = (ImageView) findViewById(R.id.iv_icon_image);
        TextView descriptionTextView = (TextView) findViewById(R.id.tw_detail_description);

        tittleTextView.setText(child.getData().getTitle());

        if(StringUtils.isNotBlank(child.getData().getIconImg())){
            Picasso.with(this).load(child.getData().getIconImg()).error(R.drawable.image_not_found).into(imageView);
        } else if(StringUtils.isNotBlank(child.getData().getHeaderImg())){
            Picasso.with(this).load(child.getData().getHeaderImg()).error(R.drawable.image_not_found).into(imageView);
        } else{
            imageView.setImageResource(R.drawable.image_not_found);
        }

        descriptionTextView.setText(child.getData().getPublicDescription().toString());
    }

}
