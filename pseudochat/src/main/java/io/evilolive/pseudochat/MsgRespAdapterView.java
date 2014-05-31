package io.evilolive.pseudochat;

import android.content.Context;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

/**
 * Created by Jonathan Almeida on 11/03/14.
 */
public class MsgRespAdapterView extends AdapterView {
    public MsgRespAdapterView(Context context) {
        super(context);
//        ArrayAdapter<Message> = new ArrayAdapter<Message>(context, )
    }

    @Override
    public Adapter getAdapter() {
        return null;
    }

    @Override
    public void setAdapter(Adapter adapter) {

    }

    @Override
    public View getSelectedView() {
        return null;
    }

    @Override
    public void setSelection(int i) {

    }
}
