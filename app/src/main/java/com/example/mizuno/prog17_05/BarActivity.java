package com.example.mizuno.prog17_05;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class BarActivity extends AppCompatActivity {

    private BarChart barChart;
    private AsyncHttpGetJson getjson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);

        barChart = (BarChart) findViewById(R.id.chart);
        getjson = new AsyncHttpGetJson(this, 0);
        getjson.execute();

        /*
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(4f, 0));
        entries.add(new BarEntry(8f, 1));
        entries.add(new BarEntry(6f, 2));
        entries.add(new BarEntry(12f, 3));
        entries.add(new BarEntry(18f, 4));
        entries.add(new BarEntry(9f, 5));

        BarDataSet dataset = new BarDataSet(entries, "# of Calls");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");

        BarData data = new BarData(labels, dataset);
        barChart.setData(data);
        barChart.animateY(5000);
        */
    }

    public void setBarChart(String[] str) {
        ArrayList<BarDataSet> barDataSets = new ArrayList<>();

        ArrayList<BarEntry> entries = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<String>();

        int index = 0;
        for(int i = 0; i < str.length; i+=4){
            entries.add(new BarEntry((float) Double.parseDouble(str[i+1]), index++));
            labels.add(str[i]);
        }
        BarDataSet dataset = new BarDataSet(entries, "Temperature");
        dataset.setColor(ColorTemplate.COLORFUL_COLORS[1]);
        barDataSets.add(dataset);


        ArrayList<BarEntry> entries2 = new ArrayList<>();
        index = 0;
        for(int i = 0; i < str.length; i+=4){
            entries2.add(new BarEntry((float) Double.parseDouble(str[i+2]), index++));
        }
        BarDataSet dataset2 = new BarDataSet(entries2, "Max");
        dataset2.setColor(ColorTemplate.COLORFUL_COLORS[2]);
        barDataSets.add(dataset2);


        ArrayList<BarEntry> entries3 = new ArrayList<>();
        index = 0;
        for(int i = 0; i < str.length; i+=4){
            entries3.add(new BarEntry((float) Double.parseDouble(str[i+3]), index++));
        }
        BarDataSet dataset3 = new BarDataSet(entries3, "Min");
        dataset3.setColor(ColorTemplate.COLORFUL_COLORS[3]);
        barDataSets.add(dataset3);


        BarData data = new BarData(labels, barDataSets);
        barChart.setData(data);
        barChart.animateY(5000);
    }
}
