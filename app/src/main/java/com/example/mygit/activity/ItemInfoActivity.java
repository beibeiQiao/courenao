package com.example.mygit.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.mygit.R;

public class ItemInfoActivity extends AppCompatActivity implements View.OnClickListener {
    public ImageButton infoBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_info);
        infoBack=(ImageButton)findViewById(R.id.Infoback);

        infoBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.Infoback) {
            ItemInfoActivity.this.finish();
        }
    }
}
