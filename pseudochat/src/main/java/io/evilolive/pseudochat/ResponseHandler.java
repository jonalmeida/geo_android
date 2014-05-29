package io.evilolive.pseudochat;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Jonathan Almeida on 2014-05-19.
 */
public class ResponseHandler {
    private static ResponseHandler ourInstance = new ResponseHandler();
    private LinkedList mainList = new LinkedList();
    private int insertPosition = 0;
    private long lastTimestamp = 0;
    private ResponseHandler() {}

    public static ResponseHandler getInstance() {
        return ourInstance;
    }
    public void parseResponse(JSONObject resp) throws JSONException {
        ArrayList<JSONObject> tmp = new ArrayList<JSONObject>();

        JSONArray someArray = new JSONArray(resp.getJSONArray(ResponseAttribute.POSTS).toString());
        JSONObject location = new JSONObject(resp.getJSONObject(ResponseAttribute.LOCATION).toString());
        for (int i = 0; i < someArray.length(); i++) {
            JSONObject jsonObject = someArray.getJSONObject(i);
            tmp.add(jsonObject);
            Log.v("item: ", jsonObject.toString());
            Message message = new Message(
                    jsonObject.getString(MessageAttribute.MSG_TEXT),
                    jsonObject.getString(MessageAttribute.NICK),
                    location.getDouble(MessageAttribute.LAT),
                    location.getDouble(MessageAttribute.LON),
                    jsonObject.getLong(MessageAttribute.TIMESTAMP)
            );
            if(i == 0)
                setLastTimestamp(jsonObject.getLong(MessageAttribute.TIMESTAMP));
            addToList(getInsertPosition(), message);
        }
        // The end of the list is where the insertion of the reverse iteration should go
        setInsertPosition(mainList.size());
        Log.v("parseResponse OUTPUT: ", tmp.toString());

    }

    private void addToList(int position, Message message) {
        mainList.add(position, message);
    }

    private void setLastTimestamp(long timestamp) { this.lastTimestamp = timestamp; }

    public long getLastTimestamp() { return this.lastTimestamp; }

    private void setInsertPosition(int insertPosition) { this.insertPosition = insertPosition; }

    private int getInsertPosition() { return this.insertPosition; }

}
