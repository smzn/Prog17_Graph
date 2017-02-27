package com.example.mizuno.prog17_05;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class PieActivity extends AppCompatActivity {

    private AsyncHttpGetJson getjson;
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie);

        pieChart = (PieChart) findViewById(R.id.pchart);
        getjson = new AsyncHttpGetJson(this, 2);
        getjson.execute();
    }

    public void setPieChart(String[] str) {
        ArrayList<Entry> entries = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<String>();
        int index = 0;
        for(int i = 0; i < str.length; i+=4){
            entries.add(new Entry((float) Double.parseDouble(str[i+1]), index++));
            labels.add(str[i]);
        }
        PieDataSet dataset = new PieDataSet(entries, "Temperature");
        PieData data = new PieData(labels, dataset);
        dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
        pieChart.setDescription("Temperature in Fukuroi");
        pieChart.setData(data);
        pieChart.animateY(5000);
        pieChart.saveToGallery("/sd/mychart.jpg", 85); // 85 is the quality of the image
    }
}
