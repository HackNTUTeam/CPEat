package com.cpeat.cpeat.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.cpeat.cpeat.Food;
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
    ChooseAdapter mMeatAdapter;
    ChooseAdapter mVegiAdapter;
    ChooseAdapter mSeaAdapter;
    Button mBtnCal;


    List<Food> mMeatFoods = new ArrayList<>();
    List<Food> mVegiFoods = new ArrayList<>();
    List<Food> mSeaFoods = new ArrayList<>();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mListView.setAdapter(mMeatAdapter);
                    mMeatAdapter.notifyDataSetChanged();
                    return true;
                case R.id.navigation_dashboard:
                    mListView.setAdapter(mVegiAdapter);
                    mVegiAdapter.notifyDataSetChanged();
                    return true;
                case R.id.navigation_notifications:
                    mListView.setAdapter(mSeaAdapter);
                    mSeaAdapter.notifyDataSetChanged();
                    return true;
            }
            return false;
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose, container, false);

        mMeatAdapter = new ChooseAdapter(getContext(), R.layout.list_foods, mMeatFoods);
        mVegiAdapter = new ChooseAdapter(getContext(), R.layout.list_foods, mVegiFoods);
        mSeaAdapter = new ChooseAdapter(getContext(), R.layout.list_foods, mSeaFoods);

        mListView = (ListView) view.findViewById(R.id.list_view);
        mListView.setAdapter(mMeatAdapter);

        mNavigation = (BottomNavigationView) view.findViewById(R.id.navigation);
        mNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mBtnCal = (Button) view.findViewById(R.id.btn_calculate);
        mBtnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new ChartFragment();
                Bundle bundle = new Bundle();
                //bundle.putDouble("price", editPrice.text.toString().toDouble())
                newFragment.setArguments(bundle);

                FragmentManager manager = ChooseFragment.this.getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_context, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        fillData();

        return view;
    }

    public void fillData() {
        Food meat = new Food();
        meat.name = "meatFoods";
        mMeatFoods.add(meat);
        Food vegi = new Food();
        vegi.name = "vegiFoods";
        mVegiFoods.add(vegi);
        Food sea = new Food();
        sea.name = "seaFoods";
        mSeaFoods.add(sea);
    }
}
