package com.cpeat.cpeat.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.cpeat.cpeat.R;
import com.cpeat.cpeat.data.FoodEntry;

import java.util.List;

/**
 * Created by CodeDiablos on 2017/7/22.
 */

public class ChooseAdapter extends ArrayAdapter<FoodEntry>
{
    private static final String TAG = "ChooseAdapter";
    private final List<FoodEntry> mItems;
    private final Context mContext;
    private final int mResourceId;
    private final LayoutInflater mInflater;

    public ChooseAdapter (Context context, int resourceId, List<FoodEntry> items)
    {
        super(context, resourceId, items);

        mContext = context;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mResourceId = resourceId;
        mItems = items;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(mResourceId, null);
            holder = new ViewHolder();
            holder.txtName = (TextView) convertView.findViewById(R.id.txt_food_name);
            holder.chkFood = (CheckBox) convertView.findViewById(R.id.chk_food);
            holder.chkFood.setChecked(mItems.get(position).checked);
            holder.chkFood.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
                {
                    mItems.get(position).checked = isChecked;

                }
            });

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        FoodEntry item = getItem(position);
        if (item != null) {
            holder.txtName.setText(item.food.name);
        }

        return convertView;
    }

    @Override
    public int getCount()
    {
        return mItems.size();
    }

    @Override
    public FoodEntry getItem(int position)
    {
        return mItems.get(position);
    }

    public class ViewHolder
    {
        CheckBox chkFood;
        TextView txtName;
    }
}