package com.example.mygit.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.example.mygit.R;
import com.example.mygit.fragment.BlankFragment1;
import com.example.mygit.fragment.BlankFragment2;
import com.example.mygit.fragment.BlankFragment3;

public class MainActivity extends AppCompatActivity {
    private BlankFragment1 fragment1;
    private BlankFragment2 fragment2;
    private BlankFragment3 fragment3;
    private  Fragment[] fragments;
    private int lastfragment;
    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragment1=new BlankFragment1();
        fragment2=new BlankFragment2();
        fragment3=new BlankFragment3();
        fragments=new Fragment[]{fragment1,fragment2,fragment3};
        lastfragment=0;
        getSupportFragmentManager().beginTransaction().replace(R.id.mainView,fragment1).show(fragment1).commit();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bnv);
        navigation.setOnNavigationItemSelectedListener(changeFragment);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener changeFragment
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                {
                    if(lastfragment!=0){
                        //  mTextMessage.setText(R.string.title_home);
                        switchFragment(lastfragment,0);
                        lastfragment=0;
                    }
                    return true;
                }
                case R.id.navigation_dashboard:
                {
                    if(lastfragment!=1) {
                        //   mTextMessage.setText(R.string.title_dashboard);
                        switchFragment(lastfragment, 1);
                        lastfragment = 1;
                    }
                    return true;
                }
                case R.id.navigation_notifications:
                {
                    if(lastfragment!=2) {
                        //  mTextMessage.setText(R.string.title_notifications);
                        switchFragment(lastfragment, 2);
                        lastfragment = 2;
                    }
                    return true;
                }
            }
            return false;
        }
    };
    private void switchFragment(int lastfragment,int index){
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[lastfragment]);
        if(fragments[index].isAdded()==false){
            transaction.add(R.id.mainView,fragments[index]);
        }
        transaction.show(fragments[index]).commitAllowingStateLoss();
    }

}
