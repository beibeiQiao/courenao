package com.example.mygit.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mygit.R;
import com.example.mygit.activity.BecomeActivity;
import com.example.mygit.activity.EditActivity;
import com.example.mygit.activity.SettingActivity;

public class BlankFragment3 extends Fragment implements View.OnClickListener{
    View mView;
    Button btn1;
    Button btn2;
    Button btn3;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       mView= inflater.inflate(R.layout.fragment_blank_fragment3, container, false);
        btn1=(Button)mView.findViewById(R.id.edit);
        btn1.setOnClickListener(this);
        btn2=(Button)mView.findViewById(R.id.become);
        btn2.setOnClickListener(this);
        btn3=(Button)mView.findViewById(R.id.shezhi);
        btn3.setOnClickListener(this);
        // Inflate the layout for this fragment
        return mView;
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.edit) {
            Intent intent = new Intent(getActivity(), EditActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.become) {
            Intent intent = new Intent(getActivity(), BecomeActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.shezhi) {
            Intent intent = new Intent(getActivity(), SettingActivity.class);
            startActivity(intent);

        }
    }
}
