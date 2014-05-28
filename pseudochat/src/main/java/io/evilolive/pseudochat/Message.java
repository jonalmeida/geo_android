package io.evilolive.pseudochat;

/**
 * Created by Jonathan Almeida on 07/03/14.
 */

    // TODO:    Create a return if the message was created successfully
    //          This involves figuring out how to define "successful"


public final class Message {

    private String _msg_text;
    private String _nick;
    private double _latitude;
    private double _longitude;
    private long _timestamp;
    private boolean _set;

    // Each message needs to be constructed with these basic requirements
    public Message( String msg_text,
                    String nick,
                    double latitude,
                    double longitude,
                    long timestamp) {
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

    private void setLatitude(double _latitude) {
        this._latitude = _latitude;
    }

    private void setLongitude(double _longitude) {
        this._longitude = _longitude;
    }

    private void setTimestamp(long _timestamp) {
        this._timestamp = _timestamp;
    }

    public String getMsgText() {
        return _msg_text;
    }

    public String getNick() {
        return _nick;
    }

    public double getLatitude() {
        return _latitude;
    }

    public double getLongitude() {
        return _longitude;
    }

    public float getTimestamp() {
        return _timestamp;
    }
}
