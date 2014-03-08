package io.evilolive.pseudochat;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by jonathan on 07/03/14.
 */

    // This class is meant to actually send a message
    // You should only need one instance of it
    // TODO: Consider making this a singleton

public class MessageHandler {

    private HttpClient httpclient;
    private HttpPost httppost;

    public MessageHandler() {
        // Create a new HttpClient and Post Header
        httpclient = new DefaultHttpClient();
        httppost = new HttpPost("http://lemuranon.herokuapp.com/messages");
    }

    protected JSONObject getJsonObjectFromMessage(Message message) throws JSONException {
        JSONObject res = new JSONObject();
        res.put(MessageAttribute.MSG_TEXT, message.getMsgText());
        res.put(MessageAttribute.NICK, message.getNick());
        res.put(MessageAttribute.LATITUDE, message.getLatitude());
        res.put(MessageAttribute.LONGITUDE, message.getLongitude());
//        res.put(MessageAttribute.TIMESTAMP, message.getTimestamp());

        return res;
    }

    public HttpResponse postData(Message message) throws IOException {

        HttpResponse resp = null;

        try {
            // Example JSON data
            // {
            //      "msg": "Test2",
            //      "nick": "jonathan",
            //      "location[lat]": 42.30752570000001,
            //      "location[lon]": -83.0682726,
            //      "timestampLastMsg": 139422699124321
            // }
            JSONObject holder = getJsonObjectFromMessage(message);

            // Passes the results to a string builder/entity
            StringEntity se = new StringEntity(holder.toString());

            // Sets the post request as the resulting string
            this.httppost.setEntity(se);
            //sets a request header so the page receiving the request
            //will know what to do with it
            this.httppost.setHeader("Accept", "application/json");
            this.httppost.setHeader("Content-type", "application/json");
            // Execute HTTP Post Request
            resp = this.httpclient.execute(httppost);
        } catch (ClientProtocolException e) { // TODO: Add proper exception handling instead of only printing trace
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return resp;
    }

}
