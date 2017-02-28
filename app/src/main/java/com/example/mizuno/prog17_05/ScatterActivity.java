package com.example.mizuno.prog17_05;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;

public class ScatterActivity extends AppCompatActivity {
    ScatterChart scatterChart;
    private AsyncHttpGetJson getjson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scatter);

        scatterChart = (ScatterChart) findViewById(R.id.schart);
        getjson = new AsyncHttpGetJson(this, 3);
        getjson.execute();

    }

    public void setScatterChart(String[] str) {
        ArrayList<Entry> entries = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<String>();
        int index = 0;
        for(int i = 0; i < str.length; i+=4){
            entries.add(new Entry((float) Double.parseDouble(str[i+1]), index++));
            labels.add(str[i]);
        }
        ScatterDataSet dataset = new ScatterDataSet(entries, "Temperature");
        ScatterData data = new ScatterData(labels, dataset);
        scatterChart.setData(data);
        scatterChart.setDescription("Description");
        dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
        dataset.setScatterShapeSize(10);
        dataset.setScatterShape(ScatterChart.ScatterShape.CIRCLE);
        scatterChart.animateY(5000);
    }
}
