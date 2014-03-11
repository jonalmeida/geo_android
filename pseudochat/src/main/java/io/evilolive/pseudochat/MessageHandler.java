package io.evilolive.pseudochat;

import android.content.Context;
import android.os.Looper;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by jonathan on 07/03/14.
 */

    // This class is meant to actually send a message
    // You should only need one instance of it
    // TODO: Consider making this a singleton

public class MessageHandler {

    private HttpClient httpclient;
    private HttpPost httppost;
    private HttpResponse httpresponse;
    private Context context;

    public MessageHandler() {
        // Create a new HttpClient and Post Header
        httpclient = new DefaultHttpClient();
        httppost = new HttpPost("http://lemuranon.herokuapp.com/messages");
        httpresponse = null;
    }

    protected JSONObject getJsonObjectFromMessage(Message message) throws JSONException {
        JSONObject res = new JSONObject();
        res.put(MessageAttribute.MSG_TEXT, message.getMsgText());
        res.put(MessageAttribute.NICK, message.getNick());
        res.put(MessageAttribute.LATITUDE, message.getLatitude());
        res.put(MessageAttribute.LONGITUDE, message.getLongitude());
        res.put(MessageAttribute.TIMESTAMP, message.getTimestamp());

        return res;
    }

    public void postData(final Message message) throws IOException {
        final String[] foo = new String[1];

        final Thread t = new Thread() {
            public void run() {
                // Example data
                // {
                //      "msg": "Test message",
                //      "nick": "Foobar",
                //      "location[lat]": 42.3025199,
                //      "location[lon]": -83.0437328,
                //      "timestampLastMsg": 139422699124321
                // }

                Looper.prepare(); //For Preparing Message Pool for the child Thread
                ArrayList<NameValuePair> postParameters;

                postParameters = new ArrayList<NameValuePair>();
                postParameters.add(
                        new BasicNameValuePair(
                                MessageAttribute.MSG_TEXT, message.getMsgText())
                );
                postParameters.add(
                        new BasicNameValuePair(
                                MessageAttribute.NICK, message.getNick())
                );
                postParameters.add(
                        new BasicNameValuePair(
                                MessageAttribute.LATITUDE, Double.toString(message.getLatitude()))
                );
                postParameters.add(
                        new BasicNameValuePair(
                                MessageAttribute.LONGITUDE, Double.toString(message.getLongitude()))
                );
                postParameters.add(
                        new BasicNameValuePair(
                                MessageAttribute.TIMESTAMP, Float.toString(message.getTimestamp()))
                );

                try {
                    httppost.setEntity(new UrlEncodedFormEntity(postParameters));
                    httpresponse = null; // Set to null before making a new post
                    httpresponse = httpclient.execute(httppost);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                /*Checking response */
                if(httpresponse!=null){
                    InputStream in = null;
                    try {
                        // Get the data in the entity
                        // TODO: Send response to a Cursor to be handled
                        BufferedReader reader = new BufferedReader(new InputStreamReader(httpresponse.getEntity().getContent(), "UTF-8"));
                        StringBuilder builder = new StringBuilder();
                        for (String line = null; (line = reader.readLine()) != null;) {
                            builder.append(line).append("\n");
                        }
                        JSONTokener tokener = new JSONTokener(builder.toString());
                        JSONObject finaljson = new JSONObject(tokener);
                        Log.v("JSON OUTPUT: ", finaljson.toString());

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                Looper.loop(); //Loop in the message queue
            }
        };
        t.start();
    }
}
