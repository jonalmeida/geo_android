package io.evilolive.pseudochat;

/**
 * Created by jonathan on 07/03/14.
 */

    // TODO:    Create a return if the message was created successfully
    //          This involves figuring out how to define "successful"


public final class Message {

    private String _msg_text;
    private String _nick;
    private float _latitude;
    private float _longitude;
    private float _timestamp;
    private boolean _set;

    // Each message needs to be constructed with these basic requirements
    public Message( String msg_text,
                    String nick,
                    float  latitude,
                    float  longitude,
                    float  timestamp) {
        if(!_set) {
            setMsgText(msg_text);
            setNick(nick);
            setLatitude(latitude);
            setLongitude(longitude);
            setTimestamp(timestamp);

            _set = true;
        }
    }

    private void setMsgText(String _msg_text) {
        this._msg_text = _msg_text;
    }

    private void setNick(String _nick) {
        this._nick = _nick;
    }

    private void setLatitude(float _latitude) {
        this._latitude = _latitude;
    }

    private void setLongitude(float _longitude) {
        this._longitude = _longitude;
    }

    private void setTimestamp(float _timestamp) {
        this._timestamp = _timestamp;
    }

    public String getMsgText() {
        return _msg_text;
    }

    public String getNick() {
        return _nick;
    }

    public float getLatitude() {
        return _latitude;
    }

    public float getLongitude() {
        return _longitude;
    }

    public float getTimestamp() {
        return _timestamp;
    }
}
