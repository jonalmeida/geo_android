package io.evilolive.pseudochat;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by jonathan on 2014-05-19.
 */
public class ResponseHandler implements Map {
    private static ResponseHandler ourInstance = new ResponseHandler();

    public static ResponseHandler getInstance() {
        return ourInstance;
    }

    private ResponseHandler() {
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public Object get(Object o) {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Set keySet() {
        return null;
    }

    @Override
    public Object put(Object o, Object o2) {
        return null;
    }

    @Override
    public void putAll(Map map) {

    }

    @Override
    public Object remove(Object o) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Collection values() {
        return null;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean containsKey(Object o) {
        return false;
    }

    @Override
    public boolean containsValue(Object o) {
        return false;
    }

    @Override
    public Set<Entry> entrySet() {
        return null;
    }
}
