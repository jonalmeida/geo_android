package io.evilolive.pseudochat;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Jonathan Almeida on 2014-05-19.
 */
public class ResponseHandler {
    private static ResponseHandler ourInstance = new ResponseHandler();
    private ArrayList mainList = new ArrayList<Message>();
    public static ResponseHandler getInstance() {
        return ourInstance;
    }

    private ResponseHandler() {}

    public void parseResponse(JSONObject resp) throws JSONException {
        ArrayList<JSONObject> tmp = new ArrayList<JSONObject>();

        JSONArray someArray = new JSONArray(resp.getJSONArray("posts").toString());
        JSONObject location = new JSONObject(resp.getJSONObject("location").toString());
        for (int i = 0; i < someArray.length(); i++) {
            JSONObject jsonObject = someArray.getJSONObject(i);
            tmp.add(jsonObject);
            Log.v("item: ", jsonObject.toString());
            Message message = new Message(
                    jsonObject.getString(MessageAttribute.MSG_TEXT),
                    jsonObject.getString(MessageAttribute.NICK),
                    location.getDouble("lat"),
                    location.getDouble("lon"),
                    jsonObject.getLong("timestamp")
            );
            addToList(message);
        }
        Log.v("parseResponse OUTPUT: ", tmp.toString());

    }

    private void addToList(Message message) {
        mainList.add(message);
    }
}
