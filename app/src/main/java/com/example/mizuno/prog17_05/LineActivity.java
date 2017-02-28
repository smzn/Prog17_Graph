package com.example.mizuno.prog17_05;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;

public class LineActivity extends AppCompatActivity {

    LineChart lineChart;
    private AsyncHttpGetJson getjson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line);

        lineChart = (LineChart) findViewById(R.id.lchart);
        getjson = new AsyncHttpGetJson(this, 1);
        getjson.execute();

    }
    public void setLineChart(String[] str) {
        ArrayList<Entry> entries = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<String>();

        int index = 0;
        for(int i = 0; i < str.length; i+=4){
            entries.add(new Entry((float) Double.parseDouble(str[i+1]), index++));
            labels.add(str[i]);
        }
        LineDataSet dataset = new LineDataSet(entries, "Tempreture");
        LineData data = new LineData(labels, dataset);
        dataset.setColors(new int[]{ColorTemplate.COLORFUL_COLORS[2]}); //
        dataset.setDrawCubic(true);
        dataset.setDrawFilled(true);
        lineChart.setData(data);
        lineChart.animateY(5000);
    }
}
