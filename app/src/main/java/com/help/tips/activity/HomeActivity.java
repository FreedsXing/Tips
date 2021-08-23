package com.help.tips.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.help.tips.FifthFragment;
import com.help.tips.FourthFragment;
import com.help.tips.HomeFragment;
import com.help.tips.R;
import com.help.tips.SecondFragment;
import com.help.tips.ThirdFragment;
import com.help.tips.base.BaseActivity;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class HomeActivity extends BaseActivity {

    BottomNavigationView bottomNavigationView;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        frameLayout = findViewById(R.id.framelayout);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.framelayout, new HomeFragment());
        transaction.commitAllowingStateLoss();

       // BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch (item.getItemId()){
                    case R.id.first:
                        transaction.replace(R.id.framelayout, new HomeFragment());
                        break;
                    case R.id.second:
                        transaction.replace(R.id.framelayout, new SecondFragment());
                        break;
                    case R.id.third:
                        transaction.replace(R.id.framelayout, new ThirdFragment());
                        break;
                    case R.id.four:
                        transaction.replace(R.id.framelayout, new FourthFragment());
                        break;
                    case R.id.five:
                        transaction.replace(R.id.framelayout, new FifthFragment());
                        break;
                    default:
                        break;
                }
                transaction.commitAllowingStateLoss();
                return true;
            }
        });
    }
}