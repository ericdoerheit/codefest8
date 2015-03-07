package de.ericdoerheit.myapplication;

import android.content.Context;
import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.URI;

public class Utils {

    final public String SERVER_URL = "http://localhost";

    public Context context;

    private HttpClient httpClient;

    private ResponseListener responseListener;

    private Track currentTrack;

    public Utils(Context context) {
        this.context = context;
        httpClient = new DefaultHttpClient();
    }

    public void serialize(Track track) {
        try {
            FileOutputStream fos = context.openFileOutput("track" + readNumberOfFiles() + ".dat", Context.MODE_APPEND);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(track);
            os.close();
            fos.close();
        } catch (NotSerializableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Track unserialize(int number) {
        try {
            if(number <= readNumberOfFiles()) {
                FileInputStream fis = context.openFileInput("track" + number + ".dat");
                ObjectInputStream is = new ObjectInputStream(fis);
                Track track = (Track) is.readObject();
                is.close();
                fis.close();
                return track;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void save(Track track){
        try {
            FileOutputStream fos = context.openFileOutput("track" + readNumberOfFiles() + ".json", Context.MODE_PRIVATE);
            fos.write(getJSONfromTrack(track).toString().getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Track read(int number){
        try {
            if(number <= readNumberOfFiles()) {
                FileInputStream fis = context.openFileInput("track" + number + ".json");
                BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
                String line;
                String data = "";
                while ((line = reader.readLine()) != null) {
                   data += line;
                }

                JSONObject jsonTrack = new JSONObject(data);
                Track track = getTrackfromJSON(jsonTrack);
                fis.close();
                return track;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int readNumberOfFiles() {
        return this.context.fileList().length;
    }

    public void getResultFromDrivingData(){
        new Request().execute(SERVER_URL);
    }

    public Track getTrackfromJSON (JSONObject trackJSON) {
        Track track = new Track();
        try {
            track.setCarModell(trackJSON.getString("carModell"));
            track.setMileAge(trackJSON.getInt("mileAge"));
            track.setDistance(trackJSON.getInt("distance"));
            track.setAvgSpeed(trackJSON.getInt("avgSpeed"));
            track.setRotationSpeed(trackJSON.getInt("rotationSpeed"));
            track.setFuel(trackJSON.getInt("fuel"));
            track.setDrivingTime(trackJSON.getInt("drivingTime"));
            track.setCurrentSpeed(trackJSON.getInt("currentSpeed"));
            track.setGear(trackJSON.getInt("gear"));
            track.setThrottlePressure(Float.parseFloat(trackJSON.getString("throttlePressure")));
            track.setBreakPressure(Float.parseFloat(trackJSON.getString("breakPressure")));
            track.setEmission(Float.parseFloat(trackJSON.getString("emission")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return track;
    }

    public JSONObject getJSONfromTrack (Track track) {
        JSONObject trackJSON = new JSONObject();
        try {
            trackJSON.put("carModell", track.getCarModell());
            trackJSON.put("mileAge", track.getMileAge());
            trackJSON.put("distance", track.getDistance());
            trackJSON.put("avgSpeed", track.getAvgSpeed());
            trackJSON.put("rotationSpeed", track.getRotationSpeed());
            trackJSON.put("fuel", track.getFuel());
            trackJSON.put("drivingTime", track.getDrivingTime());
            trackJSON.put("currentSpeed", track.getCurrentSpeed());
            trackJSON.put("gear", track.getGear());
            trackJSON.put("throttlePressure", track.getThrottlePressure());
            trackJSON.put("breakPressure", track.getBreakPressure());
            trackJSON.put("emission", track.getEmission());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return trackJSON;
    }

    private class Request extends AsyncTask<String, String, String>{
        @Override
        protected String doInBackground(String... params) {
            HttpResponse response;
            String responseString = null;

            try {
                response = httpClient.execute(new HttpGet(params[0]));
                StatusLine statusLine = response.getStatusLine();

                if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    response.getEntity().writeTo(out);
                    responseString = out.toString();
                    out.close();
                } else{
                    //Closes the connection.
                    response.getEntity().getContent().close();
                    throw new IOException(statusLine.getReasonPhrase());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return responseString;
        }

        @Override
        protected void onPostExecute(String result){
            JSONObject jsonResult = null;
            try {
                jsonResult = new JSONObject(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            responseListener.onResponse(jsonResult);
        }
    }

    public Track getCurrentTrack() {
        return currentTrack;
    }

    public void setCurrentTrack(Track currentTrack) {
        this.currentTrack = currentTrack;
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }
}