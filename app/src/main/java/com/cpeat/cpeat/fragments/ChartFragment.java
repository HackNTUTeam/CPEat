package com.cpeat.cpeat.fragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cpeat.cpeat.R;
import com.cpeat.cpeat.data.ResultData;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CodeDiablos on 2017/7/22.
 */

public class ChartFragment extends Fragment implements OnChartValueSelectedListener {

    private PieChart mChart;
    private Button mBtnReCal;
    private ResultData mResult;

    private TextView mTxtMeat;
    private TextView mTxtVege;
    private TextView mTxtSeafood;
    private TextView mTxtPrice;
    private TextView mTxtYourEat;
    private TextView mTxtRate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart, container, false);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.text_calculate_result);
        mResult = (ResultData) getArguments().getSerializable("result");

        mChart = (PieChart) view.findViewById(R.id.chart_result);
        mChart.setUsePercentValues(true);
        mChart.getDescription().setEnabled(false);
        mChart.setExtraOffsets(5, 10, 5, 5);

        mChart.setDragDecelerationFrictionCoef(0.95f);

        //mChart.setCenterTextTypeface(mTfLight);
        //mChart.setCenterText(generateCenterSpannableText());

        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(Color.WHITE);

        mChart.setTransparentCircleColor(Color.WHITE);
        mChart.setTransparentCircleAlpha(110);

        mChart.setHoleRadius(58f);
        mChart.setTransparentCircleRadius(61f);

        mChart.setDrawCenterText(true);

        mChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        mChart.setRotationEnabled(true);
        mChart.setHighlightPerTapEnabled(true);

        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        // add a selection listener
        mChart.setOnChartValueSelectedListener(this);

        setData();

        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        // mChart.spin(2000, 0, 360);

        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // entry label styling
        mChart.setEntryLabelColor(Color.WHITE);
        //mChart.setEntryLabelTypeface(mTfRegular);
        mChart.setEntryLabelTextSize(12f);

        mTxtMeat = (TextView) view.findViewById(R.id.txt_meat2);
        mTxtMeat.setText(mResult.meat.count + "");
        mTxtVege = (TextView) view.findViewById(R.id.txt_vege2);
        mTxtVege.setText(mResult.vege.count + "");
        mTxtSeafood = (TextView) view.findViewById(R.id.txt_seafood2);
        mTxtSeafood.setText(mResult.seafood.count + "");

        mTxtPrice = (TextView) view.findViewById(R.id.txt_eat_price);
        mTxtPrice.setText(getString(R.string.text_eat_price) + mResult.orgPrice + getString(R.string.text_dollar));
        mTxtYourEat = (TextView) view.findViewById(R.id.txt_your_eat);
        mTxtYourEat.setText(getString(R.string.text_your_eat) + mResult.eatPrice + getString(R.string.text_dollar));
        mTxtRate = (TextView) view.findViewById(R.id.txt_rate);
        mTxtRate.setText(getString(R.string.text_rate) + (mResult.eatPrice / mResult.orgPrice) + "%");

        mBtnReCal = (Button) view.findViewById(R.id.btn_recalculate);
        mBtnReCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = ChartFragment.this.getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_context, new MainActivityFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }

    private SpannableString generateCenterSpannableText() {
        SpannableString s = new SpannableString("MPAndroidChart\ndeveloped by Philipp Jahoda");
        s.setSpan(new RelativeSizeSpan(1.7f), 0, 14, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 14, s.length() - 15, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, s.length() - 15, 0);
        s.setSpan(new RelativeSizeSpan(.8f), 14, s.length() - 15, 0);
        s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 14, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 14, s.length(), 0);
        return s;
    }

    private void setData() {
        List<PieEntry> entries = new ArrayList<>();
        if (mResult.meat.count > 0) {
            entries.add(new PieEntry(mResult.meat.percent, getString(R.string.text_meat), getResources().getDrawable(R.drawable.icon_meet)));
        }
        if (mResult.vege.count > 0) {
            entries.add(new PieEntry(mResult.vege.percent, getString(R.string.text_vegetable), getResources().getDrawable(R.drawable.icon_vege)));
        }
        if (mResult.seafood.count > 0) {
            entries.add(new PieEntry(mResult.seafood.percent, getString(R.string.text_seafood), getResources().getDrawable(R.drawable.icon_seafood)));
        }

        PieDataSet dataSet = new PieDataSet(entries, "");

        dataSet.setDrawIcons(false);
        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        ArrayList<Integer> colors = new ArrayList<>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        //data.setValueTypeface(mTfLight);
        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        mChart.invalidate();
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
