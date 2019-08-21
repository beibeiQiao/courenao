package com.example.mygit.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.mygit.R;

public class BecomeActivity extends AppCompatActivity implements View.OnClickListener {
public ImageButton back3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_become);
        back3=(ImageButton)findViewById(R.id.back3);
        back3.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        if(v.getId()==R.id.back3)
        {
            BecomeActivity.this.finish();
        }
    }
}
