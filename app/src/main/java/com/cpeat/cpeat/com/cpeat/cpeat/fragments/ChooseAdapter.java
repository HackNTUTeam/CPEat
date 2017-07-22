package com.cpeat.cpeat.com.cpeat.cpeat.fragments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cpeat.cpeat.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CodeDiablos on 2017/7/22.
 */

public class ChooseAdapter extends ArrayAdapter<Food>
{
    private static final String TAG = "ChooseAdapter";
    private final List<Food> mItems;
    private final Context mContext;
    private final int mResourceId;
    private final LayoutInflater mInflater;

    public ChooseAdapter (Context context, int resourceId, List<Food> items)
    {
        super(context, resourceId, items);

        mContext = context;
        mResourceId = resourceId;
        mItems = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(mResourceId, null);
            holder = new ViewHolder();
            holder.txtName = (TextView) convertView.findViewById(R.id.food_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        Food item = getItem(position);
        if (item != null) {
            holder.txtName.setText(item.name);
        }

        return convertView;
    }

    @Override
    public int getCount()
    {
        return mItems.size();
    }

    @Override
    public Food getItem(int position)
    {
        return mItems.get(position);
    }

    public class ViewHolder
    {
        TextView txtName;
    }
}