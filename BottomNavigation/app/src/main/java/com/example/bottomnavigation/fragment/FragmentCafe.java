package com.example.bottomnavigation.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.bottomnavigation.R;
import com.example.bottomnavigation.cafe.CafeViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class FragmentCafe extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cafe, container, false);
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = viewPager.findViewById(R.id.viewPager);
        CafeViewPagerAdapter adapter = new CafeViewPagerAdapter(getChildFragmentManager(), 3);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}
