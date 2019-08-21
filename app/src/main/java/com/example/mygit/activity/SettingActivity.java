package com.example.mygit.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.mygit.R;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {
public ImageButton back2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        back2=(ImageButton)findViewById(R.id.back2 );
        back2.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        if(v.getId()==R.id.back2)
        {
            SettingActivity.this.finish();
        }
    }
}
