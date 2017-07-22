package com.cpeat.cpeat.com.cpeat.cpeat.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.cpeat.cpeat.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CodeDiablos on 2017/7/22.
 */

public class ChooseFragment extends Fragment {

    private static final String TAG = "ChooseFragment";

    BottomNavigationView mNavigation;
    ListView mListView;
    ChooseAdapter mAdapter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    //mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    //mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose, container, false);

        List<Food> foods = new ArrayList<>();
        Food f = new Food();
        f.name = "Beef";
        foods.add(f);
        mAdapter = new ChooseAdapter(getContext(), R.layout.list_foods, foods);
        mListView = (ListView) view.findViewById(R.id.list_view);
        mListView.setAdapter(mAdapter);

        mNavigation = (BottomNavigationView) view.findViewById(R.id.navigation);
        mNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        return view;
    }
}
