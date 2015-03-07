package de.ericdoerheit.myapplication;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by ericdorheit on 07.03.15.
 */
public class MainApplication extends Application{

    private Utils utils;
    private ArrayList<Track> tracks;

    public MainApplication(){
        super();
    }

    @Override
    public void onCreate(){
        utils = new Utils(this.getApplicationContext());

        tracks = new ArrayList<>();

        // load all tracks from filesystem
        for(int i = 0; i < utils.readNumberOfFiles(); i++){
            Track track = utils.read(i);
            if(track != null)
                tracks.add(utils.read(i));
        }
    }

    public Utils getUtils() {
        return utils;
    }

    public void setUtils(Utils utils) {
        this.utils = utils;
    }

    public ArrayList<Track> getTracks() {
        return tracks;
    }

    public void setTracks(ArrayList<Track> tracks) {
        this.tracks = tracks;
    }
}
