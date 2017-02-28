package com.example.mizuno.prog17_05;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;

import java.util.ArrayList;

public class RadarActivity extends AppCompatActivity {

    RadarChart rchart;
    private AsyncHttpGetJson getjson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar);

        rchart = (RadarChart) findViewById(R.id.rchart);
        getjson = new AsyncHttpGetJson(this, 4);
        getjson.execute();

    }

    public void setRadarChart(String[] str) {
        ArrayList<Entry> entries = new ArrayList<>();
        ArrayList<Entry> entries2 = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<String>();
        int index = 0;
        for(int i = 0; i < str.length; i+=4){
            entries.add(new Entry((float) Double.parseDouble(str[i+1]), index++));
            entries2.add(new Entry((float) Double.parseDouble(str[i+2]), index++));
            labels.add(str[i]);
        }
        RadarDataSet dataset_comp1 = new RadarDataSet(entries, "Temperature");
        RadarDataSet dataset_comp2 = new RadarDataSet(entries2, "Max");
        dataset_comp1.setColor(Color.CYAN);
        dataset_comp1.setDrawFilled(true);

        dataset_comp2.setColor(Color.RED);
        dataset_comp2.setDrawFilled(true);

        ArrayList<RadarDataSet> dataSets = new ArrayList<RadarDataSet>();
        dataSets.add(dataset_comp1);
        dataSets.add(dataset_comp2);

        RadarData data = new RadarData(labels, dataSets);
        rchart.setData(data);
        String description = "Temperature in Fukuroi";
        rchart.setDescription(description);
        rchart.setWebLineWidthInner(0.5f);
        rchart.setDescriptionColor(Color.RED);

        rchart.invalidate();
        rchart.animate();
    }
}
