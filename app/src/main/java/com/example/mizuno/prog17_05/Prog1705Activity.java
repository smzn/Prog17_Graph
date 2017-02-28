package com.example.mizuno.prog17_05;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Prog1705Activity extends AppCompatActivity {

    private Button barbutton, linebutton, piebutton, sbutton, rbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prog1705);

        barbutton = (Button) findViewById(R.id.barbutton);
        linebutton = (Button) findViewById(R.id.linebutton);
        piebutton = (Button) findViewById(R.id.piebutton);
        sbutton = (Button)findViewById(R.id.sbutton);
        rbutton = (Button)findViewById(R.id.radarbutton);

        barbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Prog1705Activity.this, BarActivity.class);
                startActivity(intent);
            }
        });
        linebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Prog1705Activity.this, LineActivity.class);
                startActivity(intent);
            }
        });
        piebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Prog1705Activity.this, PieActivity.class);
                startActivity(intent);
            }
        });
        sbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Prog1705Activity.this, ScatterActivity.class);
                startActivity(intent);
            }
        });
        rbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Prog1705Activity.this, RadarActivity.class);
                startActivity(intent);
            }
        });
    }
}
