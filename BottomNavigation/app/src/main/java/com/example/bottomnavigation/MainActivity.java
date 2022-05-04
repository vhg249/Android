package com.example.bottomnavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.bottomnavigation.adapter.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView navigationView;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        navigationView = findViewById(R.id.navigation);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), 4);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        navigationView.getMenu().findItem(R.id.mHome).setChecked(true);
                        break;
                    case 1:
                        navigationView.getMenu().findItem(R.id.mNotice).setChecked(true);
                        break;
                    case 2:
                        navigationView.getMenu().findItem(R.id.mSearch).setChecked(true);
                        break;
                    case 3:
                        navigationView.getMenu().findItem(R.id.mCafe).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setAdapter(adapter);
        navigationView.setOnItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) item -> {
            switch (item.getItemId()){
                case R.id.mHome:
                    viewPager.setCurrentItem(0);
                    Log.d("test","a!");
                    break;
                case R.id.mNotice:
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.mSearch:
                    viewPager.setCurrentItem(2);
                    break;
                case R.id.mCafe:
                    viewPager.setCurrentItem(3);
                    break;
            }
            return true;
        });

    }
}