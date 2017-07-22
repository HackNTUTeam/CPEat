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

import com.cpeat.cpeat.R;
import com.cpeat.cpeat.Utility;
import com.cpeat.cpeat.data.FoodEntry;

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

    List<FoodEntry> mMeatFoods = new ArrayList<>();
    List<FoodEntry> mVegeFoods = new ArrayList<>();
    List<FoodEntry> mSeaFoods = new ArrayList<>();

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
        mVegiAdapter = new ChooseAdapter(getContext(), R.layout.list_foods, mVegeFoods);
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
        mMeatFoods.clear();
        mVegeFoods.clear();
        mSeaFoods.clear();
        Utility.JSON2Data(getContext(), mMeatFoods, getResources().getString(R.string.text_meat));
        Utility.JSON2Data(getContext(), mVegeFoods, getResources().getString(R.string.text_vegetable));
        Utility.JSON2Data(getContext(), mSeaFoods, getResources().getString(R.string.text_seafood));
    }
}
