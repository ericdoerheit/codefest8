package de.ericdoerheit.myapplication;

import org.json.JSONObject;

/**
 * Created by ericdorheit on 07.03.15.
 */
public interface ResponseListener {
    public void onResponse(JSONObject response);
}
