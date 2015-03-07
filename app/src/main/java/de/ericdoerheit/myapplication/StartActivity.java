package de.ericdoerheit.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by ericdorheit on 07.03.15.
 */
public class StartActivity extends Activity implements View.OnClickListener{

    Button startTrackingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button startTrackingButton = (Button) findViewById(R.id.startTrackingButton);
        startTrackingButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startTrackingButton: {
                Intent intent = new Intent(this, RealtimeActivity.class);
                startActivity(intent);
            }
        }
    }
}
