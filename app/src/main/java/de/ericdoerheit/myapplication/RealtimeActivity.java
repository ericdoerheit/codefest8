package de.ericdoerheit.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ericdorheit on 07.03.15.
 */
public class RealtimeActivity extends Activity implements View.OnClickListener {

    final long TIMER_DELAY = 1000; // Timer delay in ms
    final long CHART_MAX = 10; // Timer delay in ms

    TextView rpmTextView;
    TextView fuelConsumptionTextView;
    TextView velocityTextView;
    LineChart chart;

    Button stopTrackingButton;

    final Handler guiHandler = new Handler();

    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realtime);


        rpmTextView = (TextView) findViewById(R.id.rpmTextView);
        fuelConsumptionTextView = (TextView) findViewById(R.id.fuelConsumptionTextView);
        velocityTextView = (TextView) findViewById(R.id.velocityTextView);
        chart = (LineChart) findViewById(R.id.chart);

        chart.setData(new LineData());
        chart.getLineData().addDataSet(new LineDataSet(new ArrayList<Entry>(), "chartDataSet"));

        stopTrackingButton = (Button) findViewById(R.id.stopTrackingButton);
        stopTrackingButton.setOnClickListener(this);

        /*
        Utils.getThresholds();
         */

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                // TODO Generator fetching data from the virtual car
                final Track track = new Track((int)(120*Math.random())) {
                };
                /*
                Utils.getCurrentRecord().add(Track);
                 */
                updateTrack(track);
            }
        };

        timer = new Timer("carDataTimer");
        timer.scheduleAtFixedRate(timerTask, TIMER_DELAY, TIMER_DELAY);
    }

    public void updateTrack(final Track track){
        guiHandler.post(new Runnable() {
            @Override
            public void run() {
                rpmTextView.setText("Test1");
                fuelConsumptionTextView.setText("Test2");
                velocityTextView.setText("Test3");

                LineData lineData = chart.getLineData();

                lineData.addXValue(String.valueOf(track.getTimestamp()));
                lineData.addEntry(new Entry(track.getSpeed(), lineData.getXValCount()-1),0);

                if(lineData.getXValCount() > CHART_MAX){
                    //lineData.removeEntry(0,0);
                    //lineData.removeXValue(0);
                }

                chart.setData(lineData);
                chart.invalidate();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.stopTrackingButton: {
                timer.cancel();
                Intent intent = new Intent(this, TracksActivity.class);
                startActivity(intent);
            }
        }
    }
}
