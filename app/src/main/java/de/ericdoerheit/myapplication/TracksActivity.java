package de.ericdoerheit.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ericdorheit on 07.03.15.
 */
public class TracksActivity extends Activity {

    MainApplication application;
    final Activity self = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracks);

        List<String> trackStrings = new ArrayList<>();

        application = (MainApplication) this.getApplication();

        final ListAdapter adapter = new ArrayAdapter<Track>(getApplicationContext(), android.R.layout.simple_list_item_1, application.getTracks());
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final Track track = (Track) parent.getItemAtPosition(position);
                application.getUtils().setCurrentTrack(track);

                Intent intent = new Intent(self, TrackDetailsActivity.class);
                startActivity(intent);
            }
        });
    }
}
