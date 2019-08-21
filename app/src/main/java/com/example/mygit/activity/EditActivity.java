package com.example.mygit.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.mygit.R;
import com.example.mygit.fragment.BlankFragment3;

public class EditActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton back1;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        back1 = (ImageButton) findViewById(R.id.back1);
        back1.setOnClickListener(this);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.sex);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radbtn = (RadioButton) findViewById(checkedId);
                Toast.makeText(getApplicationContext(), "按钮组值发生改变，你选了" + radbtn.getText(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.back1) {
        Log.d("hhhh","jjjjj");
          EditActivity.this.finish();
        }
    }
}
