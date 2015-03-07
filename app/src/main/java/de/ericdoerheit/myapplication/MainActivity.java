package de.ericdoerheit.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button connectToCarButton = (Button) findViewById(R.id.connectToCarButton);
        connectToCarButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.connectToCarButton: {
                Intent intent = new Intent(this, StartActivity.class);
                startActivity(intent);
            }
        }
    }
}
